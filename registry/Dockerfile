FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/registry.jar /app/
ENV JAVA_OPTS "-Xmx200m"
CMD ["java", $JAVA_OPTS, "-jar", "/app/registry.jar"]
