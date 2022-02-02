#!/bin/bash -e

ROOT_DIR=$(dirname $(dirname $(realpath "$0")))
source "$ROOT_DIR/scripts/commons.sh"

removeSecret() {
  FILE_PATH="$1"
  if [ -f "$FILE_PATH" ]; then
    rm "$FILE_PATH"
    echo "Removed $FILE_PATH"
  fi
  TEMPLATE_PATH="$FILE_PATH.template"
  if [ -f "$TEMPLATE_PATH" ]; then
    cp "$TEMPLATE_PATH" "$FILE_PATH"
  fi
}

removeSecret "$SIGNING_KEY_PATH"
removeSecret "$GRADLE_PROPERTIES_PATH"
removeSecret "$PUBLISHING_PROPERTIES_PATH"
