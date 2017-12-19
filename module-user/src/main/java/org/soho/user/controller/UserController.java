package org.soho.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soho.user.client.OrderClient;
import org.soho.user.domain.User;
import org.soho.user.service.UserService;
import org.soho.user.vo.OrderVo;
import org.soho.user.vo.UserNewInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhuozl on 17-4-25.
 * @RequestMapping(value = "/api/vi")
 */
@RestController
@Api(tags = "用户模块")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user/{id}" , method = RequestMethod.GET)
    @ApiOperation(value = "查询用户")
    public User getUserById(@PathVariable String id) {
        logger.info("查看用户");
        return userService.getByID(id);
    }


    @RequestMapping(path = "/user" , method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页数", required = false, dataType = "int", paramType = "query", defaultValue = "20")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public User getUser(@RequestParam String keywork
    ,@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return userService.getUserByName(keywork);
    }

    @RequestMapping(path = "/usernewinfo" , method = RequestMethod.POST)
    @ApiOperation(value = "用户发布信息")
    public UserNewInfoVo postUserNewInfo(@RequestBody UserNewInfoVo vo){
        logger.info("用户发布信息");
        return userService.postUserNewInfo(vo);
    }

    @Autowired
    private OrderClient orderClient;


    @RequestMapping(path = "/purchase" , method = RequestMethod.GET)
    @ApiOperation(value = "购买")
    public String  postPurchase(@RequestHeader(value = "Access-Token", required = false) String token,@RequestParam(value = "userid") String userId, @RequestParam Double money){
        logger.info("购买............" );
        logger.info("token:" + token);
        return orderClient.postPurchaseOrder(token,userId,money);
    }



    @RequestMapping(path = "/purchasevo" , method = RequestMethod.POST)
    @ApiOperation(value = "购买Vo")
    public String  postPurchaseVo(@RequestBody OrderVo vo){
        logger.info("购买 Vo............");
        return orderClient.postPurchaseOrderVo(vo);
    }

}