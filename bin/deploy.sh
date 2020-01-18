#!/bin/sh
#功能简介：启动app.jar 文件
#注意：在sh文件中=赋值，左右两侧不能有空格
APP="tunnel-test-1.0.0"
APP_NAME=${APP}".jar"

log_dir="/data/test/log/"
log_file="/data/test/log/intellectMedicate.log"

command=$1

# 启动
function start(){
    # 日志文件夹不存在，则创建
    if [ ! -d "${log_dir}" ];then
        mkdir "${log_dir}"
    fi

    rm -f tpid
    java -classpath %dir%\${APP};%dir%\lib\* org.platform.tunnel.test.App
    echo $! > tpid
    check
}

# 停止
function stop(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'stop process...'
        kill -15 $tpid
    fi

    sleep 5

    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'Kill Process!'
        kill -9 $tpid
    else
        echo 'Stop Success!'
    fi
}

# 检查
function check(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'App is running.'
    else
        echo 'App is NOT running.'
    fi

}

# 强制kill进程
function forcekill(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`

    if [ ${tpid} ]; then
        echo 'Kill Process!'
        kill -9 $tpid

    fi

}

# 输出进程号
function showtpid(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'process '$APP_NAME' tpid is '$tpid
    else
        echo 'process '$APP_NAME' is not running.'
    fi
}

if [ "${command}" ==  "start" ]; then
    start

elif [ "${command}" ==  "stop" ]; then
     stop

elif [ "${command}" ==  "check" ]; then
     check

elif [ "${command}" ==  "kill" ]; then
     forcekill

elif [ "${command}" == "tpid" ];then
     showtpid

else
    echo "Unknow argument...."
fi
