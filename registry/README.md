**registry:**
    服务器注册中心


###配置
    eureka.instance.prefer-ip-address = true
        就可以将IP注册到Eureka Server上，而如果不配置就是机器的主机名。
    registerWithEureka:
        表示是否注册自身到eureka服务器, false 不注册
    fetchRegistry:
        表示是否从eureka服务器获取注册信息
    WaitTimeInMsWhenSyncEmpty:
        在Eureka服务器获取不到集群里对等服务器上的实例时，需要等待的时间，单位为毫秒，默认为1000 * 60 * 5

