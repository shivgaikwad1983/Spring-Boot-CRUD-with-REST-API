package org.excercise.service;

import org.springframework.stereotype.Component;

@Component
public interface GeoCodingService {

	public String findNearest(String _address);
	public double[] getLatLong(String _address);
	public double distance(double lat1, double lat2, double lon1,double lon2);	
	
}
