#!/bin/bash

 podman build -t fuse-db-mirror . && podman run --pod dbz --rm fuse-db-mirror