package com.example.neoproject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NeoProjectApplication extends SpringBootServletInitializer {

    private static final Logger Log= LoggerFactory.getLogger(NeoProjectApplication.class);
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("-------numero di processori usati "+Runtime.getRuntime().availableProcessors()+" -----------");
        return builder.sources(NeoProjectApplication.class);
    }*/

    public static void main(String[] args) {
        System.out.println("-------numero di processori usati " + Runtime.getRuntime().availableProcessors() + " -----------");
        Log.info("STARTS NEO PROJECT...");
        SpringApplication.run(NeoProjectApplication.class, args);
    }
}
