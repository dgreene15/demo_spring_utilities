package com.example.demo.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class MyServiceUsingCircuitBreaker {

    private final ExternalServiceClient externalServiceClient;

    public MyServiceUsingCircuitBreaker(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    @CircuitBreaker(name = "externalService", fallbackMethod = "handleExternalServiceFailure")
    public String callExternal() {
        return externalServiceClient.callExternalService();
    }

    public String handleExternalServiceFailure(Throwable t) {
        return "Local fallback: " + externalServiceClient.externalServiceFallback();
    }
}
