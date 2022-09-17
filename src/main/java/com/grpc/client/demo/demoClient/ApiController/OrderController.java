package com.grpc.client.demo.demoClient.ApiController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class OrderController {
    private WebClient webClient = WebClient.create();
    private static final String ORDER_SERVICE = "orderService";
    public String getWebClientResult(String uri) {
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    @GetMapping("/order")
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "orderFallback")
    public ResponseEntity<String> createOrder() {
        String response = getWebClientResult("http://localhost:8080/item");
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> orderFallback(Exception e) {
        return new ResponseEntity<String>("Item service is down", HttpStatus.OK);
    }

}
