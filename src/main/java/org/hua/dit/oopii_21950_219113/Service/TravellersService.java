package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.Style;
import javax.swing.text.TextAction;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * For the time being We use it as our "MAIN".
 */
@Component
public class TravellersService {
    private ArrayList<Traveller> travellers;
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

    public String  getTraveller() throws IOException, NoSuchOpenWeatherCityException {

        CityService cityService = new CityService(cityRepository);
        HashMap<String, City> CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));

        JsonSaver jsc = new JsonSaver();
        /**
         * test travellers
         */
//        ElderTraveller Babis = new ElderTraveller(70,"Babis","Barcelona","es",2,10,7,8,5,7,3,10,9,5);
//        Babis.compareCities(CitiesHashMap);
//
//        YoungTraveller Nick = new YoungTraveller(19,"Nick","Athens","gr",1,10,10,6,10,9,2,10,8,1);
//        Nick.compareCities(CitiesHashMap);
//
//        YoungTraveller Paul = new YoungTraveller(19,"Paul","Sofia","bg",2,10,10,6,10,9,2,10,8,1);
//        Paul.compareCities(CitiesHashMap);
//
//        MiddleTraveller George = new MiddleTraveller(30,"George" ,"Paris","fr",0,10,10,10,0,0,10,10,10,3);
//        George.compareCities(CitiesHashMap);
//
//        YoungTraveller Nick2 = new YoungTraveller(19,"Nick","Athens","gr",2,10,10,6,10,9,2,10,8,1);
//        Nick2.compareCities(CitiesHashMap);
//
//
//        /* STANDARD ENTRIES */
//        travellers.add(George);
//
//        travellers.add(Nick);
//
//        travellers.add(Babis);
//
//        travellers.add(Paul);
//
//        travellers.add(Nick2);
//        /* END */



        //jsc.writeJSON(travellers); // write to json

//        travellers.clear(); //clear arraylist
//
        travellers = jsc.readJSON(); //fill it out reading from json file

        for (Traveller traveller : travellers) {
            System.out.println(traveller.getName());
        }
        System.out.println("After Sorting ");
        travellers.sort(Comparator.comparing(Traveller::getTimeStamp));
        for (Traveller traveller : travellers) {
            System.out.println(traveller.getName());
        }

        YoungTraveller testTraveller = new YoungTraveller();

        List<City> bestCities;

        //FREE TICKET GIVE-AWAY
        String FreeCity = "Amsterdam";
        String FreeCountry = "nl";

        String SearchCity= "Cairo";
        String SearchCountry = "eg";


//        travellers.clear();
//        travellers = jsc.readJSON();

        //Shorting by time stamp
        //Fast & compact way

        //Exercise way.

        Collections.sort(travellers);


        for(Traveller traveller : travellers){
            System.out.println(traveller.getName() + " " + traveller.getTimeStamp());
        }


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

        //return travellers;
        return null;
    }

    public ArrayList<Traveller> getAllTravellers()
    {
        return travellers;
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
