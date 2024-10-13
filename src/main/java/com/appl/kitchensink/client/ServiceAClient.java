package com.appl.kitchensink.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-a", url = "http://localhost:8080/service-a")
public interface ServiceAClient {

    @GetMapping("/greet")
    String greet();
}

