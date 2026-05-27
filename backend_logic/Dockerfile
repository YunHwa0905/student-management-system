# Tomcat 9 + JDK 11 베이스 이미지
FROM tomcat:9.0-jdk11

# 기존 Tomcat 기본 앱 제거
RUN rm -rf /usr/local/tomcat/webapps/*

# Maven 빌드 결과물 WAR 파일 복사
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# 8080 포트 오픈
EXPOSE 8080

# Tomcat 실행
CMD ["catalina.sh", "run"]