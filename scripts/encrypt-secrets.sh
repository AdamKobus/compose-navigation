#!/bin/bash -e

ROOT_DIR=$(dirname $(dirname $(realpath "$0")))
source "$ROOT_DIR/scripts/commons.sh"


PASSPHRASE=$1

if [ -z "$PASSPHRASE" ]; then
  echo "Must provide passphrase as first param"
  exit -1
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
encryptSecret "$GRADLE_PROPERTIES_PATH" "$ENCRYPTED_GRADLE_PROPERTIES_PATH"
encryptSecret "$PUBLISHING_PROPERTIES_PATH" "$ENCRYPTED_PUBLISHING_PROPERTIES_PATH"
