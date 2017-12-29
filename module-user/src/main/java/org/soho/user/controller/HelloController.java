package org.soho.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by zhuozl on 17-12-28.
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String home(Principal principal) {
        return "SUCCESS，授权成功拿到资源啦.当前用户：" + principal.getName();
    }
}
