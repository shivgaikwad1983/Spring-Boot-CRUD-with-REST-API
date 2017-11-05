package org.excercise.service;


import org.excercise.domain.Location;
import org.springframework.stereotype.Component;

@Component
public interface CoffeeShopService {
	public int createCoffeeShop(Location loc);
	public Location getCoffeeShop(int id);
	public Location updateCoffeeShop(int id,Location loc);
	public String deleteCoffeeShop(int id);
}
