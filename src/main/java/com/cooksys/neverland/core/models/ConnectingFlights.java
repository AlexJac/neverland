package com.cooksys.neverland.core.models;

import java.util.ArrayList;
import java.util.List;

public class ConnectingFlights
{
	public List<Flight> route;

	public int numberOfStops;
	
	public int tripDepartureDay;
	
	public int tripArrivalDay;
	
	public int travelTime;
	
	public int todaysDay;
	
	public ConnectingFlights(Flight flight) {
		this.route.add(flight);
	}
	
	public ConnectingFlights(List<Flight> flights) {
		this.route = flights;
	}
	
	public ConnectingFlights()
	{
		// TODO Auto-generated constructor stub
	}

	public List<Flight> getRoute()
	{
		return route;
	}

	public void setRoute(List<Flight> route)
	{
		this.route = route;
	}

	public int getNumberOfStops()
	{
		return numberOfStops;
	}

	public void setNumberOfStops(int numberOfStops)
	{
		this.numberOfStops = numberOfStops;
	}

	public int getTripDepartureDay()
	{
		return tripDepartureDay;
	}

	public void setTripDepartureDay(int tripDepartureDay)
	{
		this.tripDepartureDay = tripDepartureDay;
	}

	public int getTripArrivalDay()
	{
		return tripArrivalDay;
	}

	public void setTripArrivalDay(int tripArrivalDay)
	{
		this.tripArrivalDay = tripArrivalDay;
	}

	public int getTravelTime()
	{
		return travelTime;
	}

	public void setTravelTime(int travelTime)
	{
		this.travelTime = travelTime;
	}

	public int getTodaysDay()
	{
		return todaysDay;
	}

	public void setTodaysDay(int todaysDay)
	{
		this.todaysDay = todaysDay;
	}
}
