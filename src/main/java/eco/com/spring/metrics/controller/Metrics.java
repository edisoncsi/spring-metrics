package eco.com.spring.metrics.controller;

import eco.com.spring.metrics.services.MetricService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@RestController
@RequestMapping("api/metrics")
public class Metrics {

    public static final Logger log = LoggerFactory.getLogger(Metrics.class);
    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private MetricService metricService;

    @GetMapping
    //,  extraTags = { "status", "#{result?.status}"}
    @Timed(value ="eco.timer.test")
    public ResponseEntity<String> get() {
        log.info("MeterRegistry used {}", meterRegistry.getClass().getName());
        //meterRegistry.counter("eco.metrics").increment();
        int number = new Random().nextInt(20);

        switch (number) {
            case 2:
                log.warn("WARNING");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No resources");
            case 6:
                log.debug("WARNING BAD REQUEST");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
            case 9 :
                log.error("WARNING SERVER");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
        return new ResponseEntity<String>("prueba inicial", HttpStatus.OK);
    }

    @GetMapping("getTimer")
    public ResponseEntity<String> getTimer(){
        log.info("MeterRegistry used {}", meterRegistry.getClass().getName());

        //ESTO LO USAREMOS PARA ANALIZAR UN BLOQUE DE CODIGO ESPECIFICO
         Timer timer = meterRegistry.timer("eco.timer");
        timer.record(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            log.info("Usless task");
        });
        return new ResponseEntity<String>("edisoncsi", HttpStatus.OK);
    }

    @GetMapping("getTimer2")
    public ResponseEntity<String> getTimer2(){
        log.info("MeterRegistry used timer 2 {}", meterRegistry.getClass().getName());

        metricService.hello1();
        return new ResponseEntity<String>("timer2", HttpStatus.OK);
    }

}
