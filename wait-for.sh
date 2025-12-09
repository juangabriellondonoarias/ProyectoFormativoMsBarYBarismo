#!/usr/bin/env bash
set -e

hostport="$1"
shift

host="${hostport%%:*}"
port="${hostport##*:}"

echo "Waiting for $host:$port ..."

while ! (echo > /dev/tcp/"$host"/"$port") >/dev/null 2>&1; do
  echo "Still waiting for $host:$port..."
  sleep 1
done

echo "$host:$port is up. Starting app..."
exec "$@"
