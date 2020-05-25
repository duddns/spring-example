#!/bin/bash

# 사전 준비
# APP_NAME 변경
# $ git clone -b {branch_name} --single-branch {저장소 URL}
# deploy deploy/config deploy/jars deploy/run 디렉토리 생성
# nginx 설정

APP_NAME=3o3_admin
APP_PROFILE=dev

if [ -z $APP_PROFILE ]; then
    echo "> APP_PROFILE 을 설정해 주세요. [ dev | prod ]"
    exit 1
fi

BASE_DIR=$PWD
SRC_DIR=$BASE_DIR/$APP_NAME
DEPLOY_DIR=$BASE_DIR/deploy

BLUE_PROFILE=blue
BLUE_PORT=8081
GREEN_PROFILE=green
GREEN_PORT=8082

# TODO dir check
echo "> Dir 확인"
echo "> BASE_DIR $BASE_DIR"
echo "> SRC_DIR $SRC_DIR"
echo "> DEPLOY_DIR $DEPLOY_DIR"
echo

# TODO 소스 저장소에서 소스 가져오기
# TODO 빌드 서버가 없기 때문에 직접 빌드하는데, 빌드 서버가 있다면 분리해야함
echo "> Build"
cd $SRC_DIR
git pull
./gradlew bootJar -x test
cd $BASE_DIR
echo

echo "> Jar 파일 복사"
JAR_FILE=$(ls $SRC_DIR/build/libs/*.jar)
JAR_NAME=$(basename $JAR_FILE)
echo "> $JAR_NAME"
echo "> mv $JAR_FILE $DEPLOY_DIR/jars"
mv $JAR_FILE $DEPLOY_DIR/jars
echo

CURRENT_PROFILE_FILE=$DEPLOY_DIR/profile

echo "> Profile 확인"
if [ -f "$CURRENT_PROFILE_FILE" ]; then
    CURRENT_PROFILE=$(cat $CURRENT_PROFILE_FILE)
fi

if [ "$CURRENT_PROFILE" == "$BLUE_PROFILE" ]; then
    OLD_PROFILE=$BLUE_PROFILE
    NEW_PROFILE=$GREEN_PROFILE
    NEW_PORT=$GREEN_PORT
elif [ "$CURRENT_PROFILE" == "$GREEN_PROFILE" ]; then
    OLD_PROFILE=$GREEN_PROFILE
    NEW_PROFILE=$BLUE_PROFILE
    NEW_PORT=$BLUE_PORT
else
    echo "> Default Profile: $BLUE_PROFILE"
    OLD_PROFILE=$GREEN_PROFILE
    NEW_PROFILE=$BLUE_PROFILE
    NEW_PORT=$BLUE_PORT
fi

echo "> $CURRENT_PROFILE -> $NEW_PROFILE"
echo

echo "> Jar 파일 교체"
NEW_APP=$APP_NAME-$NEW_PROFILE
NEW_APP_FILE=$DEPLOY_DIR/$NEW_APP.jar

echo "> ln -sf $DEPLOY_DIR/jars/$JAR_NAME $NEW_APP_FILE"
ln -sf $DEPLOY_DIR/jars/$JAR_NAME $NEW_APP_FILE
echo

echo "> 새버전 $NEW_PROFILE 앱을 시작합니다."
echo "> sudo ln -sf $NEW_APP_FILE /etc/init.d/$NEW_APP"
sudo ln -sf $NEW_APP_FILE /etc/init.d/$NEW_APP

sudo systemctl daemon-reload
echo "> sudo service $NEW_APP start"
sudo service $NEW_APP start
sudo update-rc.d $NEW_APP defaults
echo

echo "> $NEW_PROFILE Health check 시작"
echo "> curl -s http://localhost:$NEW_PORT/actuator/health "

sleep 10

for retry_count in {1..10}
do
    response=$(curl -s http://localhost:$NEW_PORT/actuator/health)
    up_count=$(echo $response | grep 'UP' | wc -l)

    if [ $up_count -ge 1 ]; then
        echo "> Health check 성공."
        break
    else
        echo "> Health check 실패. 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
        echo "> Health check: ${response}"
    fi

    if [ $retry_count -eq 10 ]; then
        echo "> Health check 실패."
        echo "> Nginx에 연결하지 않고 배포를 종료합니다."
        exit 1
    fi

    echo "> Health check 재시도..."
    sleep 10
done
echo

sleep 3

echo "> Nginx Upstream을 $NEW_PROFILE로 변경"
sudo ln -sf /etc/nginx/conf.d/$NEW_PROFILE.conf.available /etc/nginx/conf.d/upstreams.conf

echo "> Nginx Reload"
sudo service nginx reload
sleep 1
echo

sleep 3

echo "> 이전 버전 종료"
OLD_APP=$APP_NAME-$OLD_PROFILE
echo "> 이전버전 $OLD_PROFILE 앱을 종료합니다."
sudo service $OLD_APP stop
sudo update-rc.d $OLD_APP remove
echo

service --status-all | grep $APP_NAME
echo 

echo "> 배포 성공"
echo $NEW_PROFILE > $CURRENT_PROFILE_FILE
