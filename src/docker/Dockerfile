FROM java:8
#拥有者
LABEL maintainer="feng"

VOLUME "/tmp"

# *.jar是你项目的jar包

COPY *.jar /app.jar

#对外暴露的端口

EXPOSE 8998

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
