package org.iStat.api.iApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="org.iStat.api")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
