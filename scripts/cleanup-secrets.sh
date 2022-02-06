#!/bin/bash -e

ABS_PATH=$(realpath "$0")
SCRIPTS_DIR=$(dirname "$ABS_PATH")
ROOT_DIR=$(dirname "$SCRIPTS_DIR")
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
removeSecret "$PUBLISHING_PROPERTIES_PATH"
