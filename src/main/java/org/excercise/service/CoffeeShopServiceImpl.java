package org.excercise.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.excercise.domain.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShopServiceImpl implements CoffeeShopService{

	private static final Logger log = LoggerFactory.getLogger(CoffeeShopServiceImpl.class);
	public static HashMap<Integer,Location> CoffeeShopMap = loadCoffeeShops();
	static int index =1;

	
	public CoffeeShopServiceImpl(@Value("${csvfile}") String csvfile){
		if(csvfile == null)
			return;
		
		
		if(CoffeeShopMap ==null){
			CoffeeShopMap = new HashMap<Integer,Location>();
			loadCSV(csvfile);
			log.info("CSV File uploaded...");
		}
	}

	static HashMap<Integer,Location> loadCoffeeShops(){
		return CoffeeShopMap;
	}
	
	public int createCoffeeShop(Location loc){
		 loc.setId(++index);
		 CoffeeShopMap.put(index,loc);
		    return  index ;
	}
	
	public Location getCoffeeShop(int _id){
		 if(!CoffeeShopMap.containsKey(_id))
			 return null;
				 
		 return CoffeeShopMap.get(_id) ;
			     
	}
	
	public Location updateCoffeeShop(int _id,Location loc){
		if(!CoffeeShopMap.containsKey(_id))
		    return  null;
		loc.setId(_id);
		CoffeeShopMap.put(_id,loc);
		return loc;
	}
	
	public String deleteCoffeeShop(int _id){
		if(!CoffeeShopMap.containsKey(_id))
		    return  null;
		CoffeeShopMap.remove(_id) ;
		return "success";
	}
	public void loadCSV(String _filename){
		
        BufferedReader br = null;
        String line = null;
        int count=0;
        try {

            br = new BufferedReader(new FileReader(_filename));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] loc = line.split(",");
                index=Integer.valueOf(loc[0].trim());
                Location location = new Location(index,loc[1].trim(),loc[2].trim(),Double.valueOf(loc[3].trim()),Double.valueOf(loc[4].trim()));
                CoffeeShopMap.put(index, location);
                count++;
            }
            log.info("Total records uploaded:"+count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

	}

}
