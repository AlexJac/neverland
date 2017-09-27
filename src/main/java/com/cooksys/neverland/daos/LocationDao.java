package com.cooksys.neverland.daos;

import java.util.List;

import com.cooksys.neverland.models.Location;

public interface LocationDao
{

	List<Location> getAllLocations();

	Location addLocation(Location location);

	Location getLocation(int location_id);

	List<Location> getLocationsByState(String state);

	Location getLocationByCity(String city);

}
