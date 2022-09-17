package com.grpc.client.demo.demoClient.ApiController;

import com.grpc.client.demo.demoClient.client.HelloWorldClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private HelloWorldClient helloWorldClient;

    @GetMapping("/sayhell")
    public String sayHell() {
        return "hello :)";
    }

    @GetMapping("/sayhelloClient")
    public String sayHello(@RequestParam(required=true) String firstname, @RequestParam(required=true) String lastname) {
        return helloWorldClient.sayHello(firstname, lastname);
    }
}
