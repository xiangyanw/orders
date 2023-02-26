#!/bin/bash

SCRIPT_DIR=$(dirname "$0")

export GROUP="600413481647.dkr.ecr.us-west-2.amazonaws.com/microservices-demo"

dt=`date +%Y%m%d-%H%M%S`
export COMMIT="lattice-${dt}"

bash ${SCRIPT_DIR}/build.sh

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 600413481647.dkr.ecr.us-west-2.amazonaws.com