#!/bin/bash

 mvn clean package -Dmaven.test.skip=true && podman build -t fuse-db-mirror . && podman run --pod dbz --rm fuse-db-mirror