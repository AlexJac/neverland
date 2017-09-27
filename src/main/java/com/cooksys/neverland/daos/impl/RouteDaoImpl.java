package com.cooksys.neverland.daos.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.neverland.controllers.FlightController;
import com.cooksys.neverland.controllers.LocationController;
import com.cooksys.neverland.controllers.UserController;
import com.cooksys.neverland.core.models.ConnectingFlights;
import com.cooksys.neverland.core.models.Flight;
import com.cooksys.neverland.core.models.FlightModel;
import com.cooksys.neverland.core.models.Location;
import com.cooksys.neverland.daos.RouteDao;
import com.cooksys.neverland.models.Route;
import com.cooksys.neverland.models.User;

@Repository
@Transactional
public class RouteDaoImpl implements RouteDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LocationController locationController;
	
	@Autowired
	private UserController userController;

	@Autowired
	private FlightController flightController;

	private Iterator<Flight> flightIterator;
	private FlightModel fModel;
	@Override
	public FlightModel getAllFlights()
	{
		FlightModel fm = com.cooksys.neverland.core.controllers.CoreModelsController.getFlightModel();
		return fm;
	}
	

	@Override
	public Route bookTrip(int userId, ConnectingFlights cf)
	{
		Session session = sessionFactory.getCurrentSession();
		Route r = new Route();
		r.setUser(userController.getUserById(userId));
		List<Flight> f = cf.getRoute();
		for(Flight flight: f){
			com.cooksys.neverland.models.Flight ft = new com.cooksys.neverland.models.Flight();
			ft.setFlightId(flight.getFlightId());
			ft.setLocationByOriginLocation(locationController.getLocationByCity(flight.getOrigin().getCity()));
			ft.setLocationByOriginLocation(locationController.getLocationByCity(flight.getDestination().getCity()));
			ft.setDepartureDay(flight.getDeparture() + cf.getTodaysDay());
			ft.setArrivalDay(flight.getDeparture() + flight.getEta() + cf.getTodaysDay());
			session.saveOrUpdate(ft);
			r.getFlights().add(ft);
		}
		Serializable id = session.save(r);
		return (Route) session.get(Route.class, id);
	}


	@Override
	// public AllPossibleRoutesModel getPossibleRoutes(int start, int end)
	public List<ConnectingFlights> getPossibleRoutes(int start, int end)
	{
		com.cooksys.neverland.models.Location startLocation = locationController.getLocation(start);
		Location sLocation = new Location(startLocation.getState(), startLocation.getCity());

		com.cooksys.neverland.models.Location endLocation = locationController.getLocation(end);
		Location eLocation = new Location(endLocation.getState(), endLocation.getCity());

		fModel = getAllFlights();
		List<Flight> allFlightOptions = fModel.getFlights();
		System.out.println("Get flights from model: "+ allFlightOptions.toString());

		List<Flight> openSeatsFlights = removeFilledFlights(allFlightOptions);
		System.out.println("Removed filled flights: " + openSeatsFlights.toString());

		List<Flight> optimizedOpenSeatsFlights = removeOutlierFlights(openSeatsFlights, sLocation, eLocation);
		System.out.println(optimizedOpenSeatsFlights.toArray().toString());

		List<Flight> startingFlights = getConnections(optimizedOpenSeatsFlights, sLocation, 0);
		System.out.println(startingFlights.toString());

		//AllPossibleRoutesModel allPossibleRoutes = new AllPossibleRoutesModel();
		
		HashMap<Integer, List<Flight>> possibles = new HashMap<Integer, List<Flight>>();
		
		HashMap<Integer, ConnectingFlights> possibleRoutes = new HashMap<Integer, ConnectingFlights>();
		
		List<ConnectingFlights> cnnF = new ArrayList<ConnectingFlights>();
		
		int i = 0;
		Iterator<Flight> f1Iterator = startingFlights.iterator();
		while (f1Iterator.hasNext())
		{
			Flight f = f1Iterator.next();
			List<Flight> temporaryFlightList = new ArrayList<Flight>();
			temporaryFlightList.addAll(optimizedOpenSeatsFlights);
			int newStartTime = f.getDeparture() + f.getEta();
			Location newStart = f.getDestination();
			List<Flight> secondaryFlights = getConnections(temporaryFlightList, newStart, newStartTime);
			System.out.println("Secondary Flights: " + secondaryFlights.toString());
			if (!secondaryFlights.isEmpty())
			{
				Iterator<Flight> f2Iterator = secondaryFlights.iterator();
				while (f2Iterator.hasNext())
				{
					Flight flighty = f2Iterator.next();
					newStartTime = flighty.getDeparture() + flighty.getEta();
					newStart = flighty.getDestination();
					List<Flight> thirdFlights = getConnections(temporaryFlightList, newStart, newStartTime);
					System.out.println("Third Flights: " + thirdFlights.toString());
					if (!thirdFlights.isEmpty())
					{
						Iterator<Flight> f3Iterator = thirdFlights.iterator();
						while (f3Iterator.hasNext())
						{
							Flight flt = f3Iterator.next();
							newStartTime = flt.getDeparture() + flt.getEta();
							newStart = flt.getDestination();
							List<Flight> fourthFlights = getConnections(temporaryFlightList, newStart, newStartTime);
							System.out.println("Fourth Flights: " + fourthFlights.toString());
							if (!fourthFlights.isEmpty())
							{
								Iterator<Flight> f4Iterator = fourthFlights.iterator();
								while (f4Iterator.hasNext())
								{
									Flight fght = f4Iterator.next();
									newStartTime = fght.getDeparture() + fght.getEta();
									newStart = fght.getDestination();
									List<Flight> fifthFlights = getConnections(temporaryFlightList, newStart,newStartTime);
									System.out.println("Fifth Flights: " + fifthFlights.toString());
									if (!fifthFlights.isEmpty())
									{
										Iterator<Flight> f5Iterator = fifthFlights.iterator();
										while (f5Iterator.hasNext())
										{
											Flight flightee = f5Iterator.next();
											newStartTime = flightee.getDeparture() + flightee.getEta();
											newStart = flightee.getDestination();
											List<Flight> sixthFlights = getConnections(temporaryFlightList, newStart, newStartTime);
											System.out.println("Sixth Flights: " + sixthFlights.toString());
											if(!sixthFlights.isEmpty()) 
											{
												Iterator<Flight> f6Iterator = sixthFlights.iterator();
												while(f6Iterator.hasNext()) 
												{
													Flight ft6 = f6Iterator.next();
													if(ft6.getDestination().equals(eLocation))
													{
														List<Flight> route6 = new ArrayList<Flight>();
														route6.add(startingFlights.get(0));
														route6.add(secondaryFlights.get(0));
														route6.add(thirdFlights.get(0));
														route6.add(fourthFlights.get(0));
														route6.add(fifthFlights.get(0));
														route6.add(ft6);
														i++;
														possibles.put(i, route6);
														ConnectingFlights cf6 = generateCFO(route6);
														possibleRoutes.put(i, cf6);
														cnnF.add(cf6);
													}
													f6Iterator.remove();
												}
											}
											if (flightee.getDestination().equals(eLocation))
											{
												List<Flight> route5 = new ArrayList<Flight>();
												route5.add(startingFlights.get(0));
												route5.add(secondaryFlights.get(0));
												route5.add(thirdFlights.get(0));
												route5.add(fourthFlights.get(0));
												route5.add(flightee);
												i++;
												System.out.println("Found possible route on 5th level: " + route5.toString());
												possibles.put(i, route5);
												ConnectingFlights cf5 = generateCFO(route5);
												possibleRoutes.put(i, cf5);
												cnnF.add(cf5);
												//allPossibleRoutes.possibilities.add(generateCFO(route5));
											}
											f5Iterator.remove();
										}
									}
									if (fght.getDestination().equals(eLocation))
									{
										List<Flight> route4 = new ArrayList<Flight>();
										route4.add(startingFlights.get(0));
										route4.add(secondaryFlights.get(0));
										route4.add(thirdFlights.get(0));
										route4.add(fght);
										i++;
										System.out.println("Found possible route on 4th level: " + route4.toString());
										possibles.put(i, route4);
										ConnectingFlights cf4 = generateCFO(route4);
										possibleRoutes.put(i, cf4);
										cnnF.add(cf4);
										//allPossibleRoutes.possibilities.add(generateCFO(route4));
									}
									f4Iterator.remove();
								}
							}
							if (flt.getDestination().equals(eLocation))
							{
								List<Flight> route3 = new ArrayList<Flight>();
								route3.add(startingFlights.get(0));
								route3.add(secondaryFlights.get(0));
								route3.add(flt);
								i++;
								System.out.println("Found possible route on 3rd level: " + route3.toString());
								possibles.put(i, route3);
								ConnectingFlights cf3 = generateCFO(route3);
								possibleRoutes.put(i, cf3);
								cnnF.add(cf3);
								//allPossibleRoutes.possibilities.add(generateCFO(route3));
							}
							f3Iterator.remove();
						}
					}
					if (flighty.getDestination().equals(eLocation))
					{
						List<Flight> route2 = new ArrayList<Flight>();
						route2.add(startingFlights.get(0));
						route2.add(flighty);
						i++;
						System.out.println("Found possible route on the 2nd level: " + route2.toString());
						possibles.put(i, route2);
						ConnectingFlights cf2 = generateCFO(route2);
						possibleRoutes.put(i, cf2);
						cnnF.add(cf2);
						//allPossibleRoutes.possibilities.add(generateCFO(route2));
					}
					f2Iterator.remove();
				}
			}
			if (f.getDestination().equals(eLocation))
			{
				List<Flight> singleton = new ArrayList<Flight>();
				singleton.add(f);
				i++;
				possibles.put(i, singleton);
				//allPossibleRoutes.possibilities.add(generateCFO(singleton));
				ConnectingFlights cf = generateCFO(singleton);
				possibleRoutes.put(i, cf);
				System.out.println("Found possible route singleton: " + f.toString());
				cnnF.add(cf);
			}
			f1Iterator.remove();
		}
		return cnnF;
	}

// --------------------------------------------------------------------------------------------------------------------------
	public boolean flightHasAvailableSeats(Flight flight)
	{
		boolean hasSeats = true;
		int newFlightId = flight.getFlightId();
		com.cooksys.neverland.models.Flight newFlight = (com.cooksys.neverland.models.Flight) flightController
				.getFlightById(newFlightId);
		if (newFlight != null)
		{
			if (newFlight.getRoutes() != null)
			{
				if (newFlight.getRoutes().size() >= 5)
				{
					hasSeats = false;
					System.out.println("Flight " + newFlight.getFlightId()
							+ " is full.");
				}
			}
		} else if (newFlight == null)
		{
			hasSeats = true;
		}
		return hasSeats;
	}

// ----------------------------------------------------------------------------------------------------------------------------
	public List<Flight> removeFilledFlights(List<Flight> listOfFlights)
	{
		int count = 0;
		boolean hasSeats;
		flightIterator = listOfFlights.iterator();

		while (flightIterator.hasNext())
		{
			Flight flight = flightIterator.next();
			hasSeats = flightHasAvailableSeats(flight);

			if (hasSeats == false)
			{
				flightIterator.remove();
				count++;
			}
		}
		System.out.println("Number of flights removed: " + count);
		return listOfFlights;
	}

// ---------------------------------------------------------------------------------------------------------------------------------
	public List<Flight> getConnections(List<Flight> flights, Location start,
			int startDay)
	{
		int count = 0;
		List<Flight> flightMatches = new ArrayList<Flight>();
		for (Flight f : flights)
		{
			if (f.getOrigin().equals(start))
			{
				if (f.getDeparture() >= startDay)
				{
					flightMatches.add(f);
					count++;
					System.out.println("Match found: Flight " + f.getFlightId()
							+ "... " + f.getOrigin().getCity() + ", "
							+ f.getOrigin().getState() + " to "
							+ f.getDestination().getCity() + ", "
							+ f.getDestination().getState() + " Departure: "
							+ f.getDeparture() + " Eta: " + f.getEta());
				}
			}
		}
		System.out.println("Total matches found: " + count);
		return flightMatches;
	}

// --------------------------------------------------------------------------------------------------------------------------

	public List<Flight> removeOutlierFlights(List<Flight> flights,
			Location start, Location end)
	{
		int count = 0;
		int tooEarly = 0;
		int tooLate = 0;
		int earliestDepDay = 50;
		int latestDepDay = 0;
		int countEarlyChange = 0;
		int countLateChange = 0;
		Flight judge = new Flight();
		Flight judge2 = new Flight();

		flightIterator = flights.iterator();

		while (flightIterator.hasNext())
		{
			Flight flight = flightIterator.next();

			if (flight.getOrigin().equals(start))
			{
				if (flight.getDeparture() < earliestDepDay)
				{
					earliestDepDay = flight.getDeparture();
					judge = flight;
					countEarlyChange++;
					System.out.println("Earliest departure day changed.");
				}
			}

			if (flight.getDestination().equals(end))
			{
				if (flight.getDeparture() > latestDepDay)
				{
					latestDepDay = flight.getDeparture();
					judge2 = flight;
					countLateChange++;
					System.out.println("Latest departure day changed.");
				}
			}
		}

		System.out.println("Earliest dep day changes: " + countEarlyChange);
		System.out.println("Latest dep day changes: " + countLateChange);

		Iterator<Flight> fIterator = flights.iterator();

		while (fIterator.hasNext())
		{
			Flight flight = fIterator.next();

			if (flight.getDeparture() < earliestDepDay)
			{
				fIterator.remove();
				count++;
				tooEarly++;
				System.out.println("Removed early flight.");
				System.out.println("Culprit: Flight " + flight.getFlightId()
						+ "Origin: " + flight.getOrigin().getCity() + ", "
						+ flight.getOrigin().getState() + " Destination: "
						+ flight.getDestination().getCity() + ", "
						+ flight.getDestination().getState() + " Departure: "
						+ flight.getDeparture() + " ETA: " + flight.getEta());
				System.out.println("Judge: Flight " + judge.getFlightId()
						+ "Origin: " + judge.getOrigin().getCity() + ", "
						+ judge.getOrigin().getState() + " Destination: "
						+ judge.getDestination().getCity() + ", "
						+ judge.getDestination().getState() + " Departure: "
						+ judge.getDeparture() + " ETA: " + judge.getEta());
			}
		}

		Iterator<Flight> ftIterator = flights.iterator();

		while (ftIterator.hasNext())
		{
			Flight flight = ftIterator.next();

			if (flight.getDeparture() < earliestDepDay)
			{
				fIterator.remove();
				count++;
				tooLate++;
				System.out.println("Removed late flight.");
				System.out.println("Culprit: Flight " + flight.getFlightId()
						+ "Origin: " + flight.getOrigin().getCity() + ", "
						+ flight.getOrigin().getState() + " Destination: "
						+ flight.getDestination().getCity() + ", "
						+ flight.getDestination().getState() + " Departure: "
						+ flight.getDeparture() + " ETA: " + flight.getEta());
				System.out.println("Judge: Flight " + judge2.getFlightId()
						+ "Origin: " + judge2.getOrigin().getCity() + ", "
						+ judge2.getOrigin().getState() + " Destination: "
						+ judge2.getDestination().getCity() + ", "
						+ judge2.getDestination().getState() + " Departure: "
						+ judge2.getDeparture() + " ETA: " + judge2.getEta());
			}
		}
		System.out.println("Flights removed: " + count);
		System.out.println("Early flights removed: " + tooEarly);
		System.out.println("Late flights removed: " + tooLate);
		System.out.println(flights);
		return flights;
	}

//--------------------------------------------------------------------------------------------------------------------------------	

	public ConnectingFlights generateCFO(List<Flight> newRoute){
		ConnectingFlights cfs = new ConnectingFlights();
		int tripDepDay = newRoute.get(0).getDeparture();
		int tripArrDay = newRoute.get(newRoute.size()-1).getDeparture() + newRoute.get(newRoute.size()-1).getEta();
		int tripTravT = tripArrDay - tripDepDay;
		int stops = newRoute.size()-1;
		cfs.setRoute(newRoute);
		cfs.setTripDepartureDay(tripDepDay);
		cfs.setTripArrivalDay(tripArrDay);
		cfs.setTravelTime(tripTravT);
		cfs.setNumberOfStops(stops);
		cfs.setTodaysDay(fModel.getCurrentDay());
		return cfs;
	}

}
