package com.cooksys.neverland.daos;

import java.util.HashMap;
import java.util.List;

import com.cooksys.neverland.core.models.ConnectingFlights;
import com.cooksys.neverland.core.models.Flight;
import com.cooksys.neverland.core.models.FlightModel;
import com.cooksys.neverland.models.Route;

public interface RouteDao
{
	//AllPossibleRoutesModel getPossibleRoutes(Location start, Location end);
	//AllPossibleRoutesModel getPossibleRoutes(int loc_start_id, int loc_end_id);
	//HashMap<Integer, ConnectingFlights> getPossibleRoutes(int loc_start_id, int loc_end_id);

	List<ConnectingFlights> getPossibleRoutes(int loc_start_id, int loc_end_id);
	
	FlightModel getAllFlights();

	Route bookTrip(int userId, ConnectingFlights cf);

}
