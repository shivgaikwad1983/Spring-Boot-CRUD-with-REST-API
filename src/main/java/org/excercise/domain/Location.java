package org.excercise.domain;

import javax.validation.constraints.NotNull;

public class Location {
	int id;
	 @NotNull(message = "CoffeShop name can not be null.")
	String name;
	 @NotNull(message = "CoffeShop address can not be null.")
	String address;
	 @NotNull(message = "CoffeShop latitude can not be null.")
	Double latitude;
	 @NotNull(message = "CoffeShop longitude can not be null.")
	Double longitude;
	
	public Location(){		
	}
	public Location(String name,String address,Double latitude,Double longitude){
		
		this.name=name;
		this.address=address;
		this.latitude=latitude;
		this.longitude=longitude;
		
	}
	public Location(int _id,String name,String address,Double latitude,Double longitude){
		this.id=_id;
		this.name=name;
		this.address=address;
		this.latitude=latitude;
		this.longitude=longitude;		
	}
	
	public int getId(){
		return this.id;
	}
	public void setId(int _id){
		this.id=_id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String _name){
		this.name = _name;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAdress(String _address){
		this.address = _address;
	}
	public double getLatitude(){
		return this.latitude;
	}
	public void setLatitude(double _latitude){
		this.latitude = _latitude;
	}
	public double getLongitude(){
		return this.longitude;
	}
	public void setLongitude(double _longitude){
		this.longitude = _longitude;
	}
}
