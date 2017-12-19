FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]
