package org.excercise.coffeeshop;

import java.io.File;

import org.excercise.service.CoffeeShopServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
/**
 * Coffee Shop Application!
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.excercise.domain","org.excercise.service","org.excercise.controller", "org.excercise.coffeeshop","org.excercise.Exception"})

public class CoffeeShopApp 
{
	private static final Logger log = LoggerFactory.getLogger(CoffeeShopApp.class);
    public static void main( String[] args )
    {
    	
    	StringBuilder Usage = new StringBuilder("Required Params: --csvfile=provide full path of locations.csv including name of the file \n");
    							   Usage.append("       		  --apikey=provide google geocode api key");
    	if(args.length <= 1){ 
    		System.out.println(Usage.toString());
    		
    		return;
    	}
    	else if (args.length >=2  && ( !(args[0].contains("--csvfile=")|| args[1].contains("--csvfile=")) || ! (args[0].contains("--apikey=") || args[1].contains("--apikey="))) ){
    		System.out.println(Usage.toString());
    		return;
    	}

    	File f =null;
    	if(args[0].contains("--csvfile="))
    	   f = new File(args[0].trim().split("=")[1]);
    	else if(args[1].contains("--csvfile="))
    	   f = new File(args[1].trim().split("=")[1]);
    		
    	
    	if(!f.exists() || f.isDirectory()) {
    	    System.out.println("Invalid File Name:"+ f.getName());
    	    return;
    	}
    	
    	
    	SpringApplication.run(CoffeeShopApp.class, args);
    	 
    }
   
}
