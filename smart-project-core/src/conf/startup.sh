set -e
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
umask 022
#ulimit -c 0
source /etc/profile

echo "Begin to startup:"
APP_HOME=$(cd "$(dirname "$0")";pwd)

APP_NAME="smart-project-core"
APP_LOG_HOME=${APP_HOME}/logs
APP_CONF_HOME=${APP_HOME}/config

#检查并创建log的目录
if [[ ! -d ${APP_LOG_HOME} && ! -h ${APP_LOG_HOME} ]];then
    echo "${APP_LOG_HOME} not exists"
    mkdir ${APP_LOG_HOME}
fi

if [[ ! -d $JAVA_HOME && ! -h $JAVA_HOME ]];then
    echo "JAVA_HOME dir not exists"
    exit -1
fi

echo `date` >> ${APP_LOG_HOME}/$APP_NAME.log

JAVA_OPTS="-server -XX:+UseG1GC -XX:-UseBiasedLocking -XX:-UseCounterDecay -XX:AutoBoxCacheMax=20000"
CLASS_PATH="-Xbootclasspath/a:${APP_CONF_HOME}"

echo "APP_HOME=$APP_HOME"
echo "APP_NAME=$APP_NAME"
echo "APP_LOG_HOME=$APP_LOG_HOME"
echo "APP_CONF_HOME=$APP_CONF_HOME"
echo "CLASS_PATH=$CLASS_PATH"
echo "JAVA_OPTS=$JAVA_OPTS"

#java ${CLASS_PATH} ${JAVA_OPTS} -jar $APP_HOME/$APP_NAME.jar

nohup java ${CLASS_PATH} ${JAVA_OPTS} -jar $APP_HOME/$APP_NAME.jar >>$APP_HOME/logs/$APP_NAME.log 2>&1 &