FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/trace-zipkin.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/trace-zipkin.jar"]
