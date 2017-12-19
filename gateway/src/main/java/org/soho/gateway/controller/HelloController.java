package org.soho.gateway.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zhuozl on 17-4-26.
 * @RequestMapping(value = "/api/v1")
 */
@RestController
@Api(tags = "Hello演示")
public class HelloController {

    @RequestMapping(value ="/setsession", method = RequestMethod.GET)
    @ApiOperation(value = "设置Session")
    public ResponseEntity index(HttpSession httpSession) {
        httpSession.setAttribute("user", "helloword");
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value ="/getsession", method = RequestMethod.GET)
    @ApiOperation(value = "获取设置Session")
    public ResponseEntity hello(HttpSession httpSession) {
        return ResponseEntity.ok(httpSession.getAttribute("user"));
    }

    /**
     * 简单输入输出参数
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    @ApiOperation(nickname = "swagger-helloworld", value = "Swagger的世界", notes = "测试HelloWorld")
    public String helloWorld(@ApiParam(value = "昵称") @RequestParam String nickname) {
        return "Hello world, " + nickname;
    }


}
