package com.cooksys.neverland.core.controllers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cooksys.neverland.core.models.Flight;
import com.cooksys.neverland.core.models.FlightModel;
import com.cooksys.neverland.core.models.Location;

@Repository
@Transactional
public class CoreModelsController
{	
	private static RestTemplate restTemplate;
	
	public static void main(String args[]) {
		
		//restTemplate = new RestTemplate();

		//getFlightModel();
		
		getLocations();
	}	
	public static FlightModel getFlightModel() {
		restTemplate = new RestTemplate();
		String uri = "http://localhost:8080/bc-final-webservice/getFlightModel";
		//RestTemplate restTemplate = new RestTemplate();
		FlightModel flightModel = restTemplate.getForObject(uri, FlightModel.class);
		//System.out.println(flightModel.toString());
		int count = 0;
		int max_departure = 0;
		int max_eta = 0;
		for(Flight flight: flightModel.getFlights()) {
			count++;
			int flight_departure = flight.getDeparture();
			int flight_eta = flight.getEta();
			if(flight_departure > max_departure) {
				max_departure = flight_departure;
			}
			if(flight_eta > max_eta) {
				max_eta = flight_eta;
			}
			//System.out.println((((Location)flight.getOrigin()).getCity()) + ", " + ((Location)flight.getOrigin()).getState() + "\t to \t" + ((Location)flight.getDestination()).getCity() + ", " + ((Location)flight.getDestination()).getState());
		}
		//System.out.println(count);
		//System.out.println(max_departure);
		//System.out.println(max_eta);
		return flightModel;
		}
	
	public static String getLocations() {
		String uri = "http://localhost:8080/bc-final-webservice/getLocations";
		//RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		System.out.println(result);
		return result;
	}
	
}
