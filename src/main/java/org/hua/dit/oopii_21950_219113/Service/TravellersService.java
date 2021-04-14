package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * For the time being We use it as our "MAIN".
 */
@Component
public class TravellersService {

    @Autowired // Dependency injection
    private final CityRepository cityRepository;

    /**
     * Since we have injected the CityRepository dependency in our class we want now to create a constructor that we
     * can pass a CityRepository Repository(not exactly an interface). Needed for testing.
     *
     * @param cityRepository
     */
    public TravellersService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public String getTraveller() throws IOException, NoSuchOpenWeatherCityException {

        CityService cityService = new CityService(cityRepository);

        ArrayList<Traveller> travellers = new ArrayList<>();

        YoungTraveller testTraveller = new YoungTraveller();

        HashMap<String, City> CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));

        List<City> bestCities;

//        try
//        {
//            cityService.addNewCity("Warsaw","pl"); //how to add new city in database
//
//        }catch (CityAlreadyExistsException e)
//        {
//            e.printStackTrace();
//        }

        String FreeCity = "Amsterdam";
        String FreeCountry = "nl";
        String SearchCity= "Cairo";
        String SearchCountry = "eg";

        YoungTraveller youngTraveller = new YoungTraveller(19,"Nick","Athens","gr",1,10,10,6,10,9,2,10,8,1);
        MiddleTraveller middleTraveller = new MiddleTraveller(30,"George" ,"Paris","fr",0,10,10,10,0,0,10,10,10,3);
        ElderTraveller elderTraveller = new ElderTraveller(70,"Babis","Barcelona","es",2,10,7,8,5,7,3,10,9,5);
        travellers.add(youngTraveller);
        travellers.add(middleTraveller);
        travellers.add(elderTraveller);

        if(checkCityAvailability(SearchCity,SearchCountry)) //traveller searches for a city and in case that this city isn't into database the system adds it into database
        {
            System.out.println("This city ("+SearchCity+") is already into database ");
        }
        else
        {
            System.out.println("This city wasn't into database, now it is btw :)");
            //so we update the hashMap to be up to date :)
            CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));
        }

        for (Traveller traveller : travellers)
        {
            bestCities= traveller.compareCities(CitiesHashMap);
            System.out.println("The best city for "+traveller.getName()+" is :"+bestCities.get(0).getCityName());
            bestCities=traveller.compareCities(3,bestCities);
            System.out.println("And the next 3 best cities for "+traveller.getName()+" are: ");
            for (City bestCity : bestCities)
            {
                System.out.println(bestCity.getCityName());
            }
        }

        try {
            return ("And after all we have a free ticket for: "+FreeCity+" and the traveller that he gets it is: "+testTraveller.calculate_free_ticket(cityService.getCityByName(FreeCity.toUpperCase(),FreeCountry),travellers).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean checkCityAvailability (String cityName, String country)
    {
        CityService cityService = new CityService(cityRepository);
        HashMap<String, City> cities = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));

        for (String s : cities.keySet())
        {
            if(s.equals(cityName.toUpperCase()))
                return true;
        }
        try {
            cityService.addNewCity(cityName, country);
            System.out.println(cityName+" added to database ");
        }catch (CityAlreadyExistsException | IOException e)
        {
            System.out.println("There was an error ");
            e.printStackTrace();
        }
        //if it returns false this means that we have to update our hashmap because now there is a new city in our database
        return false;
    }


}
