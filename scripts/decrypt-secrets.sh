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

decryptSecret() {
  IN_PATH="$1"
  OUT_PATH="$2"
  gpg --quiet --batch --yes --decrypt --passphrase="$PASSPHRASE" --output "$OUT_PATH" "$IN_PATH"
  echo "Decrypted $IN_PATH and stored it at $OUT_PATH"
}

decryptSecret "$ENCRYPTED_SIGNING_KEY_PATH" "$SIGNING_KEY_PATH"
decryptSecret "$ENCRYPTED_PUBLISHING_PROPERTIES_PATH" "$PUBLISHING_PROPERTIES_PATH"
