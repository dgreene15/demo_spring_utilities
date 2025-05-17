package resilience4j;

import com.example.demo.resilience4j.MyServiceUsingCircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.DemoApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DemoApplication.class)
public class CircuitBreakerTests {

    @Autowired
    private MyServiceUsingCircuitBreaker myService;

    @Test
    public void testCircuitBreaker() {
        CircuitBreakerConfig defaultConfig = CircuitBreakerConfig.ofDefaults();

        System.out.println("Default CircuitBreaker Configuration:");
        System.out.println("  Failure Rate Threshold: " + defaultConfig.getFailureRateThreshold());
        System.out.println("  Slow Call Rate Threshold: " + defaultConfig.getSlowCallRateThreshold());
        System.out.println("  Slow Call Duration Threshold: " + defaultConfig.getSlowCallDurationThreshold());
        System.out.println("  Minimum Number of Calls: " + defaultConfig.getMinimumNumberOfCalls());
        System.out.println("  Permitted Number of Calls in Half-Open State: " + defaultConfig.getPermittedNumberOfCallsInHalfOpenState());
        System.out.println("  Automatic Transition From Open To Half-Open Enabled: " + defaultConfig.isAutomaticTransitionFromOpenToHalfOpenEnabled());

        assertEquals("Local fallback: Fallback from external service", myService.callExternal(), "fallback");
        assertEquals("Local fallback: Fallback from external service", myService.callExternal(), "fallback");

        assertEquals("External service success", myService.callExternal(), "success");
    }

}

