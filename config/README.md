config-server:
    统一配置管理模块

Spring Cloud Config也提供本地存储配置的方式。
我们只需要设置属性spring.profiles.active=native，Config Server会默认从应用的src/main/resource目录下检索配置文件。
也可以通过spring.cloud.config.server.native.searchLocations=file:/home/xx/属性来指定配置文件的位置。
虽然Spring Cloud Config提供了这样的功能，但是为了支持更好的管理内容和版本控制的功能，还是推荐使用git的方式。



config-client:

spring.application.name : 应用名称必须与git上文件名一致


### gateway 配置

    zuul.ignored-services:
        如果一个服务匹配到了要忽略的列表, 但是它也明确的配置在路由列表中, 将不会被忽略

    connect-timeout-millis
    socket-timeout-millis
        网关超时配置
    ribbon.ReadTimeout:
        请求处理的超时时间
    ConnectTimeout:
        请求连接的超时时间
    hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds：
        断路器的超时时间需要大于ribbon的超时时间，不然不会触发重试。


    zuul.stripPrefix:
        当stripPrefix=true的时候 （http://127.0.0.1:8181/api/user/list -> http://192.168.1.100:8080/user/list）
        当stripPrefix=false的时候（http://127.0.0.1:8181/api/user/list -> http://192.168.1.100:8080/api/user/list）
        设置 zuul.prefix 可以为所有的匹配增加前缀, 例如 /api,代理前缀默认会从请求路径中移除(通过 zuul.stripPrefix=false 可以关闭这个功能).
