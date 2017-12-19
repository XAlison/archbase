package org.soho.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.soho.user.vo.Input;
import org.soho.user.vo.Output;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        httpSession.setAttribute("token", "USER");
        System.out.println("test");
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

    /**
     * 简单对象输入输出
     * @param input
     * @return
     */
    @RequestMapping(value = "/objectio", method = RequestMethod.POST)
    @ApiOperation(nickname = "swagger-objectio", value = "简单对象输入输出", notes = "测试对象输入输出")
    public Output objectIo(@ApiParam(value = "输入") @RequestBody Input input) {
        Output output = new Output();
        output.setId(input.getId());
        output.setName("张三");

        return output;
    }

    /**
     * List对象输入输出
     * @param input
     * @return
     */
    @RequestMapping(value = "/listobjectio", method = RequestMethod.POST)
    @ApiOperation(nickname = "swagger-list-objectio", value = "List对象输入输出", notes = "测试对象输入输出")
    public List<Output> listObjectIo(@ApiParam(value = "List输入") @RequestBody List<Input> input) {
        List<Output> result = new ArrayList<Output>();
        Output output = new Output();
        output.setId("100");
        output.setName("张三");
        result.add(output);
        return result;
    }

    /**
     * 当前不支持Map：https://github.com/OAI/OpenAPI-Specification/issues/38
     * Map对象输入输出
     * @param input
     * @return
     */
    @RequestMapping(value = "/mapobjectio", method = RequestMethod.POST)
    @ApiOperation(nickname = "swagger-map-objectio", value = "Map对象输入输出", notes = "测试对象输入输出")
    public Map<String,Output> mapObjectIo(@ApiParam(value = "Map输入") @RequestBody Map<String,Input> input) {
        Map<String,Output> result = new HashMap<String,Output>();
        Output output = new Output();
        output.setId("100");
        output.setName("张三");
        result.put("张三",output);
        return result;
    }
}
