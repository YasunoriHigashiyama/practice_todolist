#!/bin/sh

TARGET=prod
if [ $# -eq 1 ]; then
	TARGET=${1}
fi

SRCDIR="$( cd "$(dirname "$0")" ; pwd -P )"
CONFDIR="$( cd "$(dirname "$0")" ; pwd -P )"/../config/${TARGET}/ui

#cp ${CONFDIR}/* ${SRCDIR}/src/main/resources/

mvn clean compile assembly:single package spring-boot:repackage -e
