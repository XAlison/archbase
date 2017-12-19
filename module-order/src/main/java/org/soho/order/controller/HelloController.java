package org.soho.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
        httpSession.setAttribute("token", "ORDER");
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value ="/getsession", method = RequestMethod.GET)
    @ApiOperation(value = "获取设置Session")
    public ResponseEntity hello(HttpSession httpSession) {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(ip+":"+httpSession.getAttribute("token"));
    }
}
