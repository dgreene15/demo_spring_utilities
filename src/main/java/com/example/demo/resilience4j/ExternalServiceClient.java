package com.example.demo.resilience4j;

import org.springframework.stereotype.Service;

@Service
public class ExternalServiceClient {

    private int invocationCount = 0;

    public String callExternalService() {
        invocationCount++;
        if (invocationCount <= 2) {
            throw new RuntimeException("Simulated external service failure");
        }
          return "External service success";
    }

    public String externalServiceFallback() {
        return "Fallback from external service";
    }
}


