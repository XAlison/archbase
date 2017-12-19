archbase:
    基于Spring boot 与 Spring Cloud 基础框架


###启动顺序

先启动config 模块,再启动registry模块


###config
    端口:7001

###registry

    环境变量:
    CONFIG_SERVICE_URL=http://127.0.0.1:7001
    REGISTRY_SERVICE_URL=http://127.0.0.1:1111/eureka/

    地址:http://127.0.0.1:1111

###gateway
    端口:9000

###业务模块:module-user,module-order,module-pay

    环境变量:
    CONFIG_SERVICE_URL=http://127.0.0.1:7001
    REGISTRY_SERVICE_URL=http://127.0.0.1:1111/eureka/
    TRACEING_SERVICE_URL=http://127.0.0.1:9411/
    REDIS_SERVICE_IP=127.0.0.1
    DB_IP=127.0.0.1:3306

    端口:8000,8001,8002

    swagger:http://127.0.0.1:8000/user/swagger-ui.html



###hystrix turbine:

    http://localhost:8100/turbine.stream(ping)


###hystrix dashboard:

    http://127.0.0.1:8000/hystrix.stream(ping)
    http://127.0.0.1:8200/hystrix.stream
　
###trace

    http://127.0.0.1:9411

###依赖: mysql,redis

mysql:

    docker run -d \
        --name=soho_mysql \
        -p 3306:3306 \
        -e MYSQL_ROOT_PASSWORD=root \
            mysql:5.6


redis:

    docker run -d \
        --name=soho_redis \
        -p 6379:6379 \
            redis:3.2.3


rabbitmq:

    docker run -d \
        --hostname my-rabbit \
        --name soho-rabbit \
        -p 5672:5672    \
        rabbitmq:3.6


 zipkin :

    docker run -d \
    -p 9411:9411 \
    openzipkin/zipkin:1.21


###日志配置(debug级别):
            java -jar demo.jar --debug


资源文件

    application-dev.properties：开发环境
    application-test.properties：测试环境
    application-prod.properties：生产环境

    执行java -jar xxx.jar，默认的开发环境（dev）
    执行java -jar xxx.jar --spring.profiles.active=test，测试环境的配置（test）
    执行java -jar xxx.jar --spring.profiles.active=prod，生产环境的配置（prod）



bootstrap.yml  和application.yml  都可以用来配置参数
bootstrap.yml可以理解成系统级别的一些参数配置，这些参数一般是不会变动的
application.yml 可以用来定义应用级别的，如果搭配spring-cloud-config使用 application.yml里面定义的文件可以实现动态替换
