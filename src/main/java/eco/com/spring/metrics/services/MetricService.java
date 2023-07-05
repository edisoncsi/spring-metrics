package eco.com.spring.metrics.services;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class MetricService {
    private Random random = new Random();

    @Timed("eco.timer.timed")
    public String hello1() {
        try {
           // Thread.sleep(random.nextInt(50));
            System.out.println("Jello");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Hello2";
    }
}
