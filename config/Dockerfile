FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/config.jar /app/
ENV JAVA_OPTS ""
CMD java $JAVA_OPTS -jar /app/config.jar