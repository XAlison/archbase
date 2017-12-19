FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/module-order.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/module-order.jar"]
