FROM java:8-jre
MAINTAINER zhuozl <zhuozl@ffcs.cn>

ADD ./target/hystrix-dashboard.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hystrix-dashboard.jar"]
