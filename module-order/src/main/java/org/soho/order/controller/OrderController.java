package org.soho.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soho.order.client.PayClient;
import org.soho.order.domain.ExOrder;
import org.soho.order.service.ExOrderService;
import org.soho.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhuozl on 17-4-26.
 * @RequestMapping(value = "/api/v1")
 */
@RestController
@Api(tags = "Order模块")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ExOrderService exOrderService;

    @Autowired
    private PayClient payclinet;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/hello")
    public String home(Principal principal) {
        return "SUCCESS，授权成功拿到资源啦.当前用户：" + principal.getName();
    }

    @RequestMapping("/hello2")
    public String home2() {
        return "Hello World";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    @ApiOperation(value = "用户购买订单")
    public String putPurchaseOrder(@RequestHeader(value = "Access-Token", required = false) String token,@RequestParam(value = "userid") String userId, @RequestParam double money) {
        logger.info("用户购买订单............");
        logger.info("token:"+ token);

        ExOrder exOrder = new ExOrder();
        exOrder.setId(UUID.randomUUID().toString());
        exOrder.setUserId(userId);
        exOrder.setAmount(money);
        exOrderService.insert(exOrder);
        Map<String,Object> urlVariables = new HashMap();
        urlVariables.put("userid",userId);
        urlVariables.put("money",money);


        String result = payclinet.postPurchaseOrder(userId,money);
      /*
        String result = restTemplate.getForEntity("http://MODULE-PAY/paymoney?userid=1&money=2", String.class ).getBody();
        String result1 = restTemplate.getForEntity("http://MODULE-PAY/pay/paymoney?userid={userid}&money={money}", String.class,urlVariables ).getBody();
        */
        return result;
    }


    @HystrixCommand(fallbackMethod = "addServiceFallbackVo")
    @RequestMapping(value = "/purchasevo", method = RequestMethod.POST)
    @ApiOperation(value = "用户购买订单Vo")
    public String putPurchaseOrderVo(@RequestBody OrderVo vo) {
        logger.info("用户购买订单Vo............");
        ExOrder exOrder = new ExOrder();
        exOrder.setId(UUID.randomUUID().toString());
        exOrder.setUserId(vo.getUserId());
        exOrder.setAmount(vo.getAmount());
        exOrderService.insert(exOrder);
        Map<String,Object> urlVariables = new HashMap();
        urlVariables.put("userid",vo.getUserId());
        urlVariables.put("money",vo.getAmount());

        //String result = restTemplate.getForEntity("http://MODULE-PAY/paymoney?userid=1&money=2", String.class ).getBody();
        String result = restTemplate.getForEntity("http://MODULE-PAY/paymoney?userid={userid}&money={money}", String.class,urlVariables ).getBody();
        return result;
    }

    public String addServiceFallback(String userId,double money) {
        logger.info("error:用户购买订单............" + userId + ":" +money);
        return "error";
    }

    public String addServiceFallbackVo(@RequestBody OrderVo vo) {
        logger.info("error:用户购买订单Vo............" + vo.getUserId() + ":" +vo.getId());
        return "error";
    }
}
