package com.demo;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


/**	
 * Hello world!
 *
 */
@Component
@SpringBootApplication
@EnableAutoConfiguration
public class Application  
{	
	 private static final org.slf4j.Logger logger =LoggerFactory.getLogger(SpringApplication.class);
	 
 	 public static void main( String[] args ){ 	
         SpringApplication.run(Application.class, args);
         logger.info("$$$$$$$$$$$$$$$$$$ Start $$$$$$$$$$$$$$$$$$$$$$");
    }
}
