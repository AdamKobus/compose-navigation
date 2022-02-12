import argparse
import os
import re
import shutil
import sys
import subprocess

from colorama import init as colorama_init
colorama_init()
from colorama import Fore, Style

def findRootDir():
    absPath = os.path.abspath(__file__)
    while absPath:
        absPath = os.path.dirname(absPath)
        if os.path.exists(os.path.join(absPath, '.git')):
            return absPath

ROOT_DIR = findRootDir()
GENERATED_TAG = '<!-- GENERATED SECTION - DON\'T ADD ANY TEXT BELOW THIS TAG -->'

VERBOSE = False
COMMIT = False

CLASS_NAME_PATTERN = re.compile(r'\[([A-Za-z]+)\]\(index.md\)')
DOKKA_DIR = "docs/kdoc"
DOCS_DIR = "docs"

CODE_BLOCK = '```'

README_HEADER = '<!-- GENERATED -->'

class Reference(object):
    def __init__(self, name, path):
        self.name = name
        self.path = path
        self.pattern = re.compile(r'(?![^\[\]]*\])([#\s:]+?|^)(`?)({}(\.[a-zA-Z0-9_\(\)]+)?)(`?)(s?)([\s]+|$)'.format(self.name))
        self.existing_entry_pattern = re.compile(r'(\[{}(\.[a-zA-Z0-9_\(\)]+)?\])(?!\()'.format(self.name))

    def __str__(self):
        return self.name

class DokkaDir(object):
    def __init__(self, modulename, path):
        self.modulename = modulename
        self.source_path = path
        self.target_path = os.path.join(DOKKA_DIR, modulename)

class DocFile(object):
    def __init__(self, path, level):
        self.path = path
        self.doc_prefix = '../' * level
        self.refs = {}

    def __str__(self):
        return "DocFile {}".format(self.path)

    def update(self, references):
        print("\n{}".format(self))
        with open(self.path, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        updated_lines = []
        is_in_code_block = False
        generated_lines = []
        generated_lines_expected = []
        is_in_generated = False
        for line in lines:
            line_is_quote = line.strip().startswith('>')
            if line.strip().startswith(GENERATED_TAG):
                is_in_generated = True
            if is_in_generated:
                generated_lines.append(line)
            elif line.startswith(CODE_BLOCK):
                is_in_code_block = not is_in_code_block
                updated_lines.append(line)
            elif not is_in_code_block and not line_is_quote:
                updated = self.process_line(line, references)
                if line != updated:
                    log_commit(line, updated)
                updated_lines.append(updated)
            else:
                updated_lines.append(line)
        if len(updated_lines) == 0 or updated_lines[-1].strip() != '':
            log_commit('', '\n')
            updated_lines.append('\n')
        generated_lines_expected.append(GENERATED_TAG + '\n')
        generated_lines_expected.append('\n')
        for entry in self.refs.keys():
            log('Reference {}: {}'.format(entry, self.refs[entry]))
            generated_lines_expected.append('{}: {}\n'.format(entry, self.refs[entry]))
        log_generated(generated_lines_expected, generated_lines)
        if COMMIT:
            with open(self.path, 'w+', encoding='utf-8') as f:
                for line in updated_lines:
                    f.write(line)
                for line in generated_lines_expected:
                    f.write(line)

    def process_line(self, line, references):
        ret = line
        for entry in references:
            ret = entry.pattern.sub(r'\1[\3]\6\7', ret)
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

def log_generated(expected, actual):
    if is_generated_content_different(expected, actual):
        for line in actual:
            print('{}-- {}{}'.format(Fore.RED, line.strip(), Style.RESET_ALL))
        for line in expected:
            print('{}++ {}{}'.format(Fore.GREEN, line.strip(), Style.RESET_ALL))

def is_generated_content_different(expected, actual):
    if len(expected) != len(actual):
        return True
    for i in range(0, len(expected)):
        if expected[i] != actual[i]:
            return True
    return False

def build_reference_index(dokka_dirs):
    references = {}
    for entry in dokka_dirs:
        build_reference_index_for_dir(entry, references)
    ret = []
    for key in references.keys():
        ret.append(Reference(key, references[key]))
    return ret

def build_reference_index_for_dir(dokka_dir, references):
    log('\nBuilding references index for module {}\n'.format(dokka_dir.modulename))
    index = []
    for root, dir, files in os.walk(dokka_dir.target_path):
        index.extend(process_files(root, files))
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

def process_files(root_dir, files):
    ret = []
    for f in files:
        if f == 'index.md':
            full_path = os.path.join(root_dir, f)
            parent_dir = os.path.basename(os.path.dirname(full_path))
            top_dir = os.path.basename(os.path.dirname(os.path.dirname(full_path)))
            if parent_dir.startswith('-') and '.' in top_dir:
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
    return path.replace('\\', '/').replace(ROOT_DIR.replace('\\', '/'), '')

def find_dokka_docs():
    ret = []
    for entry in os.listdir(ROOT_DIR):
        dokka_dir = os.path.join(ROOT_DIR, entry, 'build', 'dokka', 'gfm')
        if os.path.isdir(dokka_dir):
            log('Found dokka documentation at {}'.format(dokka_dir))
            ret.append(DokkaDir(entry, dokka_dir))
    if not ret:
        raise ValueError('No build/dokka/gfm directory found. Please run :dokkaGfm gradle task first')
    return ret

def get_all_docs_files():
    log("\nSearching for documentation files\n")
    return get_docs_files(DOCS_DIR, get_docs_dir_initial_level())

def get_docs_files(dir, level):
    ret = []
    paths = [os.path.join(dir, entry) for entry in os.listdir(dir)]
    for entry in paths:
        if os.path.isdir(entry):
            if is_valid_docs_dir(entry):
                ret.extend(get_docs_files(entry, level + 1))
        elif entry.lower().endswith('.md'):
            doc_file = DocFile(entry, level)
            ret.append(doc_file)
            log(str(doc_file))
    return ret

def is_valid_docs_dir(directory):
    is_dokka_dir = directory.replace('\\', '/').startswith(DOKKA_DIR.replace('\\', '/'))
    return is_dokka_dir == False

def generate_readme(docs):
    readme_path = os.path.join(DOKKA_DIR, 'README.md')
    generate = True
    if os.path.exists(readme_path):
        with open(readme_path, 'r', encoding='utf-8') as f:
            content = f.read()
        if content.startswith(README_HEADER):
            log('Removing readme file {}'.format(readme_path))
            os.remove(readme_path)
        else:
            generate = False
    if not generate:
        log('Cannot generate {} because it already exists and it was modified manually'.format(readme_path))
        return
    with open(readme_path, 'w+', encoding='utf-8') as f:
        log('Generating readme file {}'.format(readme_path))
        f.write(README_HEADER + '\n# Modules\n')
        for i in range(0, len(docs)):
            f.write('\n{}. [{}]({}/index.md)'.format(i+1, docs[i].modulename, docs[i].modulename))

def get_docs_dir_initial_level():
    parts = DOCS_DIR.replace('\\', '/')[len(ROOT_DIR):].split('/')
    initial_level = len([entry for entry in parts if entry])
    return initial_level

def main(args):
    global VERBOSE
    global COMMIT
    global DOCS_DIR
    global DOKKA_DIR
    VERBOSE = args.verbose
    COMMIT = args.commit
    DOCS_DIR = os.path.join(ROOT_DIR, args.docs)
    DOKKA_DIR = os.path.join(ROOT_DIR, args.kdoc)

    dokka_docs = find_dokka_docs()
    for entry in dokka_docs:
        if os.path.exists(entry.target_path):
            log('Removing {}'.format(entry.target_path))
            shutil.rmtree(entry.target_path)

        log('Copying {} to {}'.format(entry.source_path, entry.target_path))
        shutil.copytree(entry.source_path, entry.target_path)
    generate_readme(dokka_docs)
    reference_index = build_reference_index(dokka_docs)
    docs_files = get_all_docs_files()
    log("\nUpdating references in docs files")
    for entry in docs_files:
        entry.update(reference_index)

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Copies documentation generated with :dokkaGfm "
                                                 "gradle task to '--kdoc' directory and adds references to components "
                                                 "in the rest of the documentation present in '--docs' folder")
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
    parser.add_argument('--docs',
                        '-d',
                        dest='docs',
                        help="The name of the directory with documentation in your project. By default it's set to {}".format(DOCS_DIR),
                        default=DOCS_DIR)
    parser.add_argument('--kdoc',
                        '-k',
                        dest='kdoc',
                        help="The name of the directory to which the Dokka documentation copied from gradle modules will be copied to. "
                            "By default it's set to {}".format(DOKKA_DIR),
                        default=DOKKA_DIR)

    args = parser.parse_args()
    main(args)
