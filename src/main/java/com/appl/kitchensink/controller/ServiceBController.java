package com.appl.kitchensink.controller;

import com.appl.kitchensink.client.ServiceAClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

    @Autowired
    private ServiceAClient serviceAClient;

    @GetMapping("/call-service-a")
    public String callServiceA() {
        return serviceAClient.callExternalService();
    }
}

