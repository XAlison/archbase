FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server.jar"]
