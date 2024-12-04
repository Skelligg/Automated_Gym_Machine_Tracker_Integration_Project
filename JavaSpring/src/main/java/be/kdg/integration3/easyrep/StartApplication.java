package be.kdg.integration3.easyrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//test
@EnableJpaRepositories(basePackages = "be.kdg.integration3.easyrep.repository")
@EntityScan(basePackages = "be.kdg.integration3.easyrep.model")
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StartApplication.class, args);
    }
}
