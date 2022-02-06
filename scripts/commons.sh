#!/bin/bash -e

ABS_PATH=$(realpath "$0")
SCRIPTS_DIR=$(dirname "$ABS_PATH")
ROOT_DIR=$(dirname "$SCRIPTS_DIR")

export SIGNING_KEY_PATH="$ROOT_DIR/signing.gpg"
export PUBLISHING_PROPERTIES_PATH="$ROOT_DIR/publishing.properties"

export ENCRYPTED_SIGNING_KEY_PATH="$SCRIPTS_DIR/signing_encrypted.gpg"
export ENCRYPTED_PUBLISHING_PROPERTIES_PATH="$SCRIPTS_DIR/publishing.properties.gpg"
