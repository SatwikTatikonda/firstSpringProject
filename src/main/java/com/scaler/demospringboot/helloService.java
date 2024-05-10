package com.scaler.demospringboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class helloService {

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String SayHello(){

        return "Hello World";
    }

    @RequestMapping(value="/helloOne",method= RequestMethod.GET)
    public String SayHelloOne(){

        return "Hello World to all";
    }
    @RequestMapping(value="/helloOneId/{id}",method= RequestMethod.GET)
    public String SayHelloOneId(@PathVariable("id") String id ){

        return "Hello World "+id;
    }

}
