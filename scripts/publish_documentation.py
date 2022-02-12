import argparse
import os
import re
import shutil
import sys
import subprocess

from colorama import init as colorama_init
colorama_init()
from colorama import Fore, Style

ROOT_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DOKKA_DIR = os.path.join(ROOT_DIR, 'composenav', 'build', 'dokka', 'gfm')
DOCS_ROOT = os.path.join(ROOT_DIR, 'docs')
COMPONENTS_DIR = os.path.join(DOCS_ROOT, 'components')
GENERATED_TAG = '<!-- GENERATED SECTION - DON\'T ADD ANY TEXT BELOW THIS TAG -->'

VERBOSE = False
COMMIT = False

CLASS_NAME_PATTERN = re.compile(r'\[([A-Za-z]+)\]\(index.md\)')

CODE_BLOCK = '```'

class Reference(object):
    def __init__(self, name, path):
        self.name = name
        self.path = path
        self.pattern = re.compile(r'(?![^\[\]]*\])(`?)({}(\.[a-zA-Z0-9_\(\)]+)?)(`?)(?=[\[\s\.])'.format(self.name))
        self.existing_entry_pattern = re.compile(r'(\[{}(\.[a-zA-Z0-9_\(\)]+)?\])'.format(self.name))

    def __str__(self):
        return self.name

class DocFile(object):
    def __init__(self, path, level):
        self.path = path
        self.doc_prefix = '../' * level
        self.refs = {}

    def __str__(self):
        return "DocFile[{}]".format(self.path)

    def update(self, references):
        log("\n{}".format(self))
        with open(self.path, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        updated_lines = []
        is_in_code_block = False
        for line in lines:
            if line.strip().startswith(GENERATED_TAG):
                break
            if line.startswith(CODE_BLOCK):
                is_in_code_block = not is_in_code_block
                updated_lines.append(line)
            elif not is_in_code_block:
                updated = self.process_line(line, references)
                if line != updated:
                    log_commit(line, updated)
                updated_lines.append(updated)
            else:
                updated_lines.append(line)
        if len(updated_lines) == 0 or updated_lines[-1].strip() != '':
            updated_lines.append('\n')
        updated_lines.append('{}\n\n'.format(GENERATED_TAG))
        for entry in self.refs.keys():
            log('Reference {}: {}'.format(entry, self.refs[entry]))
            updated_lines.append('{}: {}\n'.format(entry, self.refs[entry]))
        if COMMIT:
            with open(self.path, 'w+', encoding='utf-8') as f:
                for line in updated_lines:
                    f.write(line)

    def process_line(self, line, references):
        ret = line
        for entry in references:
            ret = entry.pattern.sub(r'[\2]', ret)
            search_result = entry.existing_entry_pattern.search(ret)
            if search_result:
                entry_path = entry.path
                if entry_path.startswith('/'):
                    entry_path = entry_path[1:]
                self.refs[str(search_result.groups(1)[0])] = self.doc_prefix + entry_path
        return ret

def log(message):
    if VERBOSE:
        print(message)

def log_commit(original_line, changed_line):
    print('{}-- {}\n{}++ {}\n{}'.format(Fore.RED,
                                        original_line.strip(),
                                        Fore.GREEN,
                                        changed_line.strip(),
                                        Style.RESET_ALL))

def build_reference_index():
    log('\nBuilding references index\n')
    index = []
    for root, dir, files in os.walk(os.path.join(COMPONENTS_DIR, 'composenav')):
        index.extend(process_files(root, files))
    references = {}
    log('\nMapping references\n')
    for entry in index:
        ref_name = get_reference_name(entry)
        if ref_name and ref_name in references:
            print('Duplicate reference name: {}'.format(ref_name))
            sys.exit(1)
        if ref_name:
            cleaned_path = clean_path(entry)
            log('Mapped {} to {}'.format(ref_name, cleaned_path))
            references[ref_name] = clean_path(entry)
    ret = []
    for key in references.keys():
        ret.append(Reference(key, references[key]))
    return ret

def process_files(root_dir, files):
    ret = []
    for f in files:
        if f == 'index.md':
            full_path = os.path.join(root_dir, f)
            parent_dir = os.path.basename(os.path.dirname(full_path))
            top_dir = os.path.basename(os.path.dirname(os.path.dirname(full_path)))
            if parent_dir.startswith('-') and top_dir.startswith('com.adamkobus.compose.navigation'):
                log("Found {}".format(full_path))
                ret.append(full_path)
    return ret


def get_reference_name(index_path):
    with open(index_path, 'r', encoding='utf-8') as f:
        content = f.read()
    class_name_result = CLASS_NAME_PATTERN.search(content)
    if class_name_result:
        return class_name_result.group(1)
    else:
        log('{} Discarded, class name not recognized'.format(index_path))
        return None

def clean_path(path):
    return path.replace(ROOT_DIR, '').replace('\\', '/')

def get_all_docs_files():
    log("\nSearching for documentation files\n")
    ret = []
    dirs = [os.path.join(DOCS_ROOT, 'tutorial'), os.path.join(DOCS_ROOT, 'usecases')]
    for dir in dirs:
        ret.extend(get_docs_files(dir, 2))
    return ret

def get_docs_files(dir, level):
    ret = []
    paths = os.listdir(dir)
    for entry in paths:
        full_path = os.path.join(dir, entry)
        if os.path.isdir(full_path):
            ret.extend(get_docs_files(full_path, level + 1))
        elif entry.lower().endswith('.md'):
            doc_file = DocFile(full_path, level)
            ret.append(doc_file)
            log(str(doc_file))
    return ret

def main(args):
    global VERBOSE
    global COMMIT
    VERBOSE = args.verbose
    COMMIT = args.commit
    if not os.path.exists(DOKKA_DIR):
        log('{} does not exist'.format(DOKKA_DIR))
        sys.exit(1)
    if os.path.exists(COMPONENTS_DIR):
        log('Removing {}'.format(COMPONENTS_DIR))
        shutil.rmtree(COMPONENTS_DIR)
    log('Copying {} to {}'.format(DOKKA_DIR, COMPONENTS_DIR))
    shutil.copytree(DOKKA_DIR, COMPONENTS_DIR)
    reference_index = build_reference_index()
    docs_files = get_all_docs_files()
    log("\nUpdating references in docs files")
    for entry in docs_files:
        entry.update(reference_index)

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Copies documentation generated with :composenav:createDocumentation "
                                                 "gradle task to 'docs' directory and adds references to components "
                                                 "in the rest of the documentation")
    parser.add_argument('--verbose',
                        '-v',
                        dest='verbose',
                        help="Command will print what it's doing on every step",
                        action='store_true')
    parser.add_argument('--commit',
                        '-c',
                        dest='commit',
                        help="When set, then documentation changes will be commited",
                        action='store_true')
    args = parser.parse_args()
    main(args)
