package com.zuehlke.securesoftwaredevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SecureSoftwareDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureSoftwareDevelopmentApplication.class, args);
    }

}