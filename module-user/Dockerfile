FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/module-user.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/module-user.jar"]
