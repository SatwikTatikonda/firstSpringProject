package com.scaler.demospringboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloService {

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String SayHello(){

        return "Hello World";
    }

    @RequestMapping(value="/helloOne",method= RequestMethod.GET)
    public String SayHelloOne(){

        return "Hello World Satwik Tatikonda";
    }

}
