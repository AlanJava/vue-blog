package cn.tgkzxy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * @author tengguokun
 * @date 2022/1/17 16:59
 */
@RestController
public class TestController {
    @PostMapping("/test")
    public String test(){
        return "测试";
    }
}
