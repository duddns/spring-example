# 배포
> deploy.sh 파일은 우분투 기준으로 작성된 스크립트입니다. OS 환경에 맞게 수정해야 합니다.

> nginx.conf 디렉토리 안에는 blue/green 배포를 위한 nginx 설정 파일들이 들어 있습니다.

## 사전 준비

### APP_NAME 변경
```
$ vi deploy.sh
     APP_NAME 변경하기
     APP_PROFILE 변경하기
$ chmod +x deploy.sh
```

### git 저장소 받기
```
$ git clone -b {branch_name} --single-branch {저장소 URL}
```

### git 접속 정보 저장
```
$ git config credential.helper store
```


### deploy 디렉토리 생성
```
$ mkdir deploy
$ mkdir deploy/confg
$ mkdir deploy/jars
$ mkdir deploy/run
```

### 외부 application.yml 파일 적용
```
$ vi deploy/confg/application.yml
```
> 프로파일별로 application-{profile}.yml 파일로 분리해도 됨

### nginx 설정
nginx.conf 디렉토리 파일들을 참고하여 nginx 설정하기

## 서비스 등록

### init.d 추가
```
$ sudo ln -sf deploy/{APP_NAME}-blue.jar /etc/init.d/{APP_NAME}-blue
$ sudo ln -sf deploy/{APP_NAME}-green.jar /etc/init.d/{APP_NAME}-green
```

### 서비스 실행시 필요한 conf 파일 설정
```
$ vi deploy/{APP_NAME}-blue.conf
$ vi deploy/{APP_NAME}-green.conf
```

## 기타

### gradle daemon 비활성화
```
$ vi ~/.gradle/gradle.properties
     org.gradle.daemon=false
``` 