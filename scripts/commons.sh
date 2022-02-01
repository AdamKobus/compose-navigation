#!/bin/bash -e

ROOT_DIR=$(dirname $(dirname $(realpath "$0")))

SIGNING_KEY_PATH="$ROOT_DIR/composenav/signing.gpg"
GRADLE_PROPERTIES_PATH="$ROOT_DIR/gradle.properties"
PUBLISHING_PROPERTIES_PATH="$ROOT_DIR/publishing.properties"

SCRIPTS_DIR="$ROOT_DIR/scripts"

ENCRYPTED_SIGNING_KEY_PATH="$SCRIPTS_DIR/signing.gpg"
ENCRYPTED_GRADLE_PROPERTIES_PATH="$SCRIPTS_DIR/gradle.properties.gpg"
ENCRYPTED_PUBLISHING_PROPERTIES_PATH="$SCRIPTS_DIR/publishing.properties.gpg"
