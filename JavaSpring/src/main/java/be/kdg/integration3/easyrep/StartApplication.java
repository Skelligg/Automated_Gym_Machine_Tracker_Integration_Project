package be.kdg.integration3.easyrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StartApplication.class, args);
    }
}
