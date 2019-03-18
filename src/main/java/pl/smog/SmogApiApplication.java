package pl.smog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmogApiApplication.class, args);
    }

}

