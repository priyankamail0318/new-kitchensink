package com.appl.kitchensink.service;

import com.appl.kitchensink.client.ServiceAClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CircuitBreakerService {

    private final ServiceAClient externalServiceClient;

    public CircuitBreakerService(ServiceAClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    @CircuitBreaker(name = "myFeignClient", fallbackMethod = "fallbackCallExternalService")
    public String callExternalService() {
        log.info("Circuit Breaker Called through Spring resilience4j!");
        return externalServiceClient.callExternalService();
    }

    public String fallbackCallExternalService(Throwable t) {
        log.info("Fall Back Called through Spring resilience4j!");
        return "Fallback response due to: " + t.getMessage();
    }
}
