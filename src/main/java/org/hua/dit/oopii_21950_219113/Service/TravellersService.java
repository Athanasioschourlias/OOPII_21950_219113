package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

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

    private ArrayList<Traveller> travellers; //Here we store the travellers we already have saved in the json file and the
                                            //new-ones once we do the necessary checks and save them.

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

         //Here we temporary store the travellers who used the app the
                                                        //last time we run it and at the end do check if we need to make
                                                        //any changes to our "main" list and json file.

        CityService cityService = new CityService(cityRepository);

        HashMap<String, City> CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));
        List<City> bestCities;
        JsonSaver jsc = new JsonSaver();
        ArrayList<Traveller> buffer = new ArrayList<>();
        travellers = jsc.readJSON(); //Reading the saved travellers.


        //Here users will be added by with the UI in the future now we add a new one we dont have and one almost tha same
        //for testing and demonstrating purposes

        /**
         * test travellers
         */

        /* STANDARD ENTRIES */
        ElderTraveller thanos = new ElderTraveller(70,"Thanos","Larissa","gr",7,10,7,8,5,7,3,10,9,5);
        thanos.compareCities(CitiesHashMap);

        YoungTraveller Nick = new YoungTraveller(19,"Nick","Athens","gr",8,10,10,6,10,9,2,10,8,1);
        Nick.compareCities(CitiesHashMap);

        buffer.add(Nick);

        buffer.add(thanos);

        /* END */


        //Updating the list with our travellers and adding the new-ones(if-any)
        if(buffer.size() > 0)
            removeDuplicateTravellers(buffer,travellers);

        System.out.println("AFTER SORT");
        for(Traveller traveller : travellers){
            System.out.println(traveller.getName() + " " + traveller.getTimeStamp() + " " + traveller.getCafe());
        }

        YoungTraveller testTraveller = new YoungTraveller();

        //FREE TICKET GIVE-AWAY
        String FreeCity = "Athens";
        String FreeCountry = "gr";

        String SearchCity= "Cairo";
        String SearchCountry = "eg";


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

        jsc.writeJSON(travellers);

        try {
            return ("And after all we have a free ticket for: "+FreeCity+" and the traveller that he gets it is: "+testTraveller.calculate_free_ticket(cityService.getCityByName(FreeCity.toUpperCase(),FreeCountry),travellers).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Traveller> getAllTravellers()
    {
        JsonSaver jsc = new JsonSaver();
        travellers=jsc.readJSON();
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

    public void removeDuplicateTravellers(ArrayList<Traveller> Buffer,ArrayList<Traveller> travellers)
    {
        for(Traveller traveller : Buffer){
            if(!travellers.contains(traveller))
                travellers.add(traveller);
            else{
                travellers.remove(travellers.get(travellers.indexOf(traveller)));
                travellers.add(traveller);
            }

        }
        Collections.sort(travellers);
    }
    public void removeDuplicateTravellers(ArrayList<Traveller> Buffer)
    {
        for(Traveller traveller : Buffer){
            if(!travellers.contains(traveller))
                travellers.add(traveller);
            else{
                travellers.remove(travellers.get(travellers.indexOf(traveller)));
                travellers.add(traveller);
            }

        }
        Collections.sort(travellers);
    }

    public List<City> findBestCityForTheUser(String name)
    {
        CityService cityService = new CityService(cityRepository);
        List<City> bestCities = null;
        HashMap<String, City> CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));
        JsonSaver jsc = new JsonSaver();
        ArrayList<Traveller> travellers= jsc.readJSON();
        for (Traveller traveller : travellers) {
            if(traveller.getName().equals(name))
            {
                bestCities=traveller.compareCities(CitiesHashMap);
            }
        }
        return bestCities;
    }

}
