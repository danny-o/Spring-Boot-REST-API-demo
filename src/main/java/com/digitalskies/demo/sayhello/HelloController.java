package com.digitalskies.demo.sayhello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(){
        return "Hello there";
    }


    @RequestMapping("say-hello-jsp")
    public String sayHelloJSP(){
        return "say-hello";
    }
}
