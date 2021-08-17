#!/bin/sh

DIR="$( cd "$(dirname "$0")" ; pwd -P )"

cd ${DIR}

if [ $# -eq 1 ]; then
	if [ $1 = "stop" ]; then
		if [ -f ${DIR}/app.pid ]; then
			pid=`cat ${DIR}/app.pid`
			if [ ${#pid} -eq 0 ]; then
				echo 'PIDが不明です'
				exit 1
			fi
			kill ${pid}
			rm -f ${DIR}/app.pid
		else
			echo 'PIDファイルが存在しません'
		fi
		exit 0
	elif [ $1 = "debug" ]; then
		mvn clean compile spring-boot:run
		exit 0
	fi
	echo '未定義のオプションです'
	exit 2
fi

mvn clean compile spring-boot:run > ${DIR}/app.log 2>&1 &
pid=`ps ax | grep 'spring-boot:run' | grep 'poppo' | awk '{print $1}'`

echo $! > ${DIR}/app.pid
