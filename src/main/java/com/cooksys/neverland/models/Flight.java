package com.cooksys.neverland.models;

// Generated May 17, 2016 5:58:09 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Flight generated by hbm2java
 */
@Entity
@Table(name = "flight", catalog = "neverland")
public class Flight implements java.io.Serializable
{

	private int flightId;
	private Location locationByDestinationLocation;
	private Location locationByOriginLocation;
	private int departureDay;
	private int arrivalDay;
	private boolean flightArrived;
	private boolean flightDelayed;
	private int capacity;
	@JsonIgnore
	private Set<Route> routes = new HashSet<Route>(0);

	public Flight()
	{
	}

	public Flight(int flightId, Location locationByDestinationLocation,
			Location locationByOriginLocation, int departureDay,
			int arrivalDay, boolean flightArrived, boolean flightDelayed,
			int capacity)
	{
		this.flightId = flightId;
		this.locationByDestinationLocation = locationByDestinationLocation;
		this.locationByOriginLocation = locationByOriginLocation;
		this.departureDay = departureDay;
		this.arrivalDay = arrivalDay;
		this.flightArrived = flightArrived;
		this.flightDelayed = flightDelayed;
		this.capacity = capacity;
	}

	public Flight(int flightId, Location locationByDestinationLocation,
			Location locationByOriginLocation, int departureDay,
			int arrivalDay, boolean flightArrived, boolean flightDelayed,
			int capacity, Set<Route> routes)
	{
		this.flightId = flightId;
		this.locationByDestinationLocation = locationByDestinationLocation;
		this.locationByOriginLocation = locationByOriginLocation;
		this.departureDay = departureDay;
		this.arrivalDay = arrivalDay;
		this.flightArrived = flightArrived;
		this.flightDelayed = flightDelayed;
		this.capacity = capacity;
		this.routes = routes;
	}

	@Id
	@Column(name = "flight_id", unique = true, nullable = false)
	public int getFlightId()
	{
		return this.flightId;
	}

	public void setFlightId(int flightId)
	{
		this.flightId = flightId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_location", nullable = false)
	public Location getLocationByDestinationLocation()
	{
		return this.locationByDestinationLocation;
	}

	public void setLocationByDestinationLocation(
			Location locationByDestinationLocation)
	{
		this.locationByDestinationLocation = locationByDestinationLocation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "origin_location", nullable = false)
	public Location getLocationByOriginLocation()
	{
		return this.locationByOriginLocation;
	}

	public void setLocationByOriginLocation(Location locationByOriginLocation)
	{
		this.locationByOriginLocation = locationByOriginLocation;
	}

	@Column(name = "departure_day", nullable = false)
	public int getDepartureDay()
	{
		return this.departureDay;
	}

	public void setDepartureDay(int departureDay)
	{
		this.departureDay = departureDay;
	}

	@Column(name = "arrival_day", nullable = false)
	public int getArrivalDay()
	{
		return this.arrivalDay;
	}

	public void setArrivalDay(int arrivalDay)
	{
		this.arrivalDay = arrivalDay;
	}

	@Column(name = "flight_arrived", nullable = false)
	public boolean isFlightArrived()
	{
		return this.flightArrived;
	}

	public void setFlightArrived(boolean flightArrived)
	{
		this.flightArrived = flightArrived;
	}

	@Column(name = "flight_delayed", nullable = false)
	public boolean isFlightDelayed()
	{
		return this.flightDelayed;
	}

	public void setFlightDelayed(boolean flightDelayed)
	{
		this.flightDelayed = flightDelayed;
	}

	@Column(name = "capacity", nullable = false)
	public int getCapacity()
	{
		return this.capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "flights")
	public Set<Route> getRoutes()
	{
		return this.routes;
	}

	public void setRoutes(Set<Route> routes)
	{
		this.routes = routes;
	}

}