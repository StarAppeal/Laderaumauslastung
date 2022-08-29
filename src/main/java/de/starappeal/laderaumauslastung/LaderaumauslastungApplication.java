package de.starappeal.laderaumauslastung;

import de.starappeal.laderaumauslastung.service.FahrzeugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaderaumauslastungApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaderaumauslastungApplication.class, args);
    }

}
