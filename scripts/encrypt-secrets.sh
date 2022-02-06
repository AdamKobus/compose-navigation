#!/bin/bash -e

ABS_PATH=$(realpath "$0")
SCRIPTS_DIR=$(dirname "$ABS_PATH")
ROOT_DIR=$(dirname "$SCRIPTS_DIR")
source "$ROOT_DIR/scripts/commons.sh"

PASSPHRASE=$1

if [ -z "$PASSPHRASE" ]; then
  echo "Must provide passphrase as first param"
  exit 1
fi

encryptSecret() {
  IN_PATH="$1"
  OUT_PATH="$2"

  if [ -f "$IN_PATH" ]; then
    gpg --batch --yes --passphrase="$PASSPHRASE" --cipher-algo AES256 --symmetric --output "$OUT_PATH" "$IN_PATH"
    echo "Encrypted $IN_PATH and stored it at $OUT_PATH"
  else
    echo "Didn't encrypt $IN_PATH, file is missing"
  fi
}

encryptSecret "$SIGNING_KEY_PATH" "$ENCRYPTED_SIGNING_KEY_PATH"
encryptSecret "$PUBLISHING_PROPERTIES_PATH" "$ENCRYPTED_PUBLISHING_PROPERTIES_PATH"
