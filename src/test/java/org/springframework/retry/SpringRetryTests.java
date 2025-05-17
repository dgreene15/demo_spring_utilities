package org.springframework.retry;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = DemoApplication.class)
@ContextConfiguration(classes = SpringRetryTests.RetryConfig.class)
public class SpringRetryTests {

    @Autowired
    private RetryableService retryableService;

    private static int attemptCount = 0;

    @Test
    public void testSpringRetryFailures() {
        attemptCount = 0;
        String result = retryableService.callExternalService(2);

        assert attemptCount == 2 : "Expected 2 attempts, but got " + attemptCount;
    }

    @Test
    public void testSpringRetrySuccess() {
        attemptCount = 0;
        assertThrows(RemoteServiceUnavailableException.class, () -> retryableService.callExternalService(4));
    }

    @Configuration
    @EnableRetry
    static class RetryConfig {
        @Bean
        public RetryableService retryableService() {
            return new RetryableService();
        }
    }

    @Service
    static class RetryableService {
        private final Random random = new Random();

        @Retryable(
                value = { RemoteServiceUnavailableException.class },
                maxAttempts = 3,
                backoff = @Backoff(delay = 500)
        )
        public String callExternalService(int threshold) {
            attemptCount++;
            if (attemptCount < threshold) {
                throw new RemoteServiceUnavailableException("Simulated external service unavailable");
            }
            return "Service call successful";
        }

        @Retryable(
                value = { IllegalArgumentException.class },
                maxAttempts = 2,
                backoff = @Backoff(delay = 200)
        )
        public String processInput(String input) {
            System.out.println("Processing input: " + input);
            if ("error".equals(input)) {
                throw new IllegalArgumentException("Invalid input");
            }
            return "Processed: " + input;
        }
    }

    static class RemoteServiceUnavailableException extends RuntimeException {
        public RemoteServiceUnavailableException(String message) {
            super(message);
        }
    }
}
