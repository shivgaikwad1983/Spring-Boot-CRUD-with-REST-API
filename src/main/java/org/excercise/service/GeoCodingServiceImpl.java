package org.excercise.service;

import java.io.IOException;
import java.util.Map;



import org.excercise.domain.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

@Component
public class GeoCodingServiceImpl implements GeoCodingService{
		

 public static GeoApiContext context ;
 private static final Logger log = LoggerFactory.getLogger(CoffeeShopServiceImpl.class);

 public GeoCodingServiceImpl (@Value("${apikey}") String apikey) {
	if(apikey == null)
		return;
	 context =	new GeoApiContext.Builder()
			    .apiKey(apikey)
			    .build();
 }	
 @Autowired
 CoffeeShopServiceImpl css ;
 
	public String findNearest(String _address){
		
		
		Map<Integer,Location> shoplocations = CoffeeShopServiceImpl.CoffeeShopMap;
		double[] latlng = getLatLong(_address);
		double min=Integer.MAX_VALUE;
		int id=0;
		
		if(latlng != null){
			for(Map.Entry<Integer,Location> entryset: shoplocations.entrySet() ){
				Location loc = entryset.getValue();
				double d = distance(loc.getLatitude(),latlng[0],loc.getLongitude(),latlng[1]);
				if( d < min){
					min =d;
					id = loc.getId();
				 }
			 } 
			if (id >0)
				return shoplocations.get(id).getName();
		}
	 return "Not Found";
	}
	
	
	public double[] getLatLong(String _address){
		GeocodingResult[] results=null;
		double[] cords = new double[2];
	    
			try {
				results =  GeocodingApi.geocode(context,_address).await();
			} catch (ApiException e) {
				e.printStackTrace();
		
				return cords;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return cords;
			} catch (IOException e) {
				e.printStackTrace();
				return cords;
			}
				//Gson gson = new GsonBuilder().setPrettyPrinting().create();
				//System.out.println("lat:"+ gson.toJson(results[0].geometry.location.lat));
				double lat = Double.valueOf(results[0].geometry.location.lat);
				double lng = Double.valueOf(results[0].geometry.location.lng);
				
				cords[0]=lat;
				cords[1]=lng;
				
			return cords;
	}
	
	public  double distance(double lat1, double lat2, double lon1,double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters
	    //System.out.println("distance:"+distance);
        return distance;

	} 

	
}
