package org.excercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import org.excercise.Exception.ExceptionResponse;
import org.excercise.domain.Location;
import org.excercise.service.CoffeeShopService;
import org.excercise.service.CoffeeShopServiceImpl;
import org.excercise.service.GeoCodingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/")
public class CoffeeShopController  {

	private static final Logger log = LoggerFactory.getLogger(CoffeeShopServiceImpl.class);
	
    @Autowired
	CoffeeShopService CSService;
    @Autowired
	GeoCodingService geoService;
		
    @RequestMapping(value="hello", method=RequestMethod.GET)
    public ResponseEntity<Location> sayHello() {
    	
    	Location loc = new Location();
    	loc.setId(0);
    	loc.setName("myname");
    	loc.setAdress("myaddress");
    	loc.setLatitude(123.234);
    	loc.setLongitude(234.345);
        return new ResponseEntity<Location>(loc,HttpStatus.OK);
    }

    @RequestMapping(value="/CoffeeShop", method=RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Object> Create(@Valid @RequestBody Location location){   	 
		int id = CSService.createCoffeeShop(location); 
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Object>("{\"id\": "+"\""+id+"\"}", headers,HttpStatus.OK);
    }
    
    @RequestMapping(value="/CoffeeShop/{id}", method=RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Object> Read(@PathVariable("id") int id){
		Location loc = CSService.getCoffeeShop(id);
		HttpHeaders headers = new HttpHeaders();
		if(loc==null){
			ExceptionResponse response = new ExceptionResponse("id","Not Found");			
			return new ResponseEntity<Object>(response, headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loc, headers,HttpStatus.OK);
    }
    
    @RequestMapping(value="/CoffeeShop/{id}", method=RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<Object> Update(@PathVariable("id") int id,@Valid @RequestBody Location location){
    	Location loc = CSService.updateCoffeeShop(id, location); 
    	HttpHeaders headers = new HttpHeaders();
    	if(loc==null){
			ExceptionResponse response = new ExceptionResponse("id","Not Found");			
			return new ResponseEntity<Object>(response, headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loc, headers,HttpStatus.OK);
    }
   
    @RequestMapping(value="/CoffeeShop/{id}", method=RequestMethod.DELETE,  headers="Accept=application/json")
    public ResponseEntity<Object> Delete(@PathVariable("id") int id){
    	String result =  CSService.deleteCoffeeShop(id);
    	HttpHeaders headers = new HttpHeaders();
    	if(result==null){
			ExceptionResponse response = new ExceptionResponse("id","Not Found");			
			return new ResponseEntity<Object>(response, headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>("{\"status\":"+ "\""+result+ "\"}", headers,HttpStatus.OK);
    }
  
    @RequestMapping(value="/CoffeeShop/FindNearest", method=RequestMethod.GET,headers="Accept=application/json")
    public ResponseEntity<Object> FindNearest(@RequestParam String address){
    	HttpHeaders headers = new HttpHeaders();
		return  new ResponseEntity<Object>("{\"name\":"+ "\"" + geoService.findNearest(address) + "\"}",headers,HttpStatus.OK);    	
    }

}


