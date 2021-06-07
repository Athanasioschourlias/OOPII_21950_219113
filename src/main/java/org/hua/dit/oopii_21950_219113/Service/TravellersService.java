package org.hua.dit.oopii_21950_219113.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchCityException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.hua.dit.oopii_21950_219113.entitys.weather.OpenWeatherMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
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
    public static ArrayList<Traveller> travellers; //Here we store the travellers we already have saved in the json file and the
                                            //new-ones once we do the necessary checks and save them.
    public static HashMap<String, City> CitiesHashMap;
    /**
     * Since we have injected the CityRepository dependency in our class we want now to create a constructor that we
     * can pass a CityRepository Repository(not exactly an interface). Needed for testing.
     *
     * @param cityRepository
     */
    public TravellersService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public ArrayList<Traveller> getAllTravellers() throws InterruptedException
    {
        Thread t = new JsonSaver();
        t.start();
        t.join();
        ArrayList<Traveller> buffer = removeDuplicateTravellers(travellers);
        return buffer;
    }

    public boolean checkCityAvailability (String cityName, String country)
    {
        CityService cityService = new CityService(cityRepository);
//        CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));
        FetchCities fetchCities = new FetchCities(cityRepository);
        new Thread(fetchCities).start();
        for (String s : CitiesHashMap.keySet())
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

    public ArrayList<Traveller> removeDuplicateTravellers(ArrayList<Traveller> Buffer)
    {

        //this is a relatively expensive operation but it serves our purpose well
        List<Traveller> copy = Buffer.stream()
                .distinct()
                .collect(Collectors.toList());

        return (ArrayList<Traveller>) copy;
    }

    public City findBestCityForTheUser(String name) throws InterruptedException {
        List<City> bestCities = null;
        JsonSaver jsc = new JsonSaver();
        Thread t = new JsonSaver();
        Thread t2 = new FetchCities(cityRepository);
        t.start();
        t2.start();
        t.join();
        t2.join();
        removeDuplicateTravellers(travellers);
        for (Traveller traveller : travellers) {
            if(traveller.getName().equals(name))
            {
                bestCities=traveller.compareCities(CitiesHashMap);
            }
        }
        if(bestCities==null)
        {
            return null;
        }
        jsc.writeJSON(travellers);
        return bestCities.get(0);
    }

    public String addNewTraveller(Traveller traveller) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + traveller.getCityName()+ "," + traveller.getCountry()+ "&APPID=4abb3288d8abfd8b3b72670196c0175f"+""), OpenWeatherMap.class);
        double[] geodesicVector = new double[2];
        geodesicVector[0] = weather_obj.getCoord().getLat();
        geodesicVector[1] = weather_obj.getCoord().getLon();
        traveller.setGeodesicVector(geodesicVector);
        JsonSaver jsc = new JsonSaver();
        ArrayList<Traveller> buffer = new ArrayList<>();
        buffer.add(traveller);
        Thread t = new JsonSaver();
        Thread t2 = new FetchCities(cityRepository);
        t.start();
        t2.start();
        t.join();
        t2.join();
        while(CitiesHashMap.isEmpty() || travellers.isEmpty())
        {
            CitiesHashMap.wait();
            travellers.wait();
        }
        traveller.compareCities(CitiesHashMap);
        //Adding the new travellers even if the they exist.
        //FIXME: Not very efficient!!!!!!
        travellers.addAll(buffer);

        jsc.writeJSON(travellers);
        return "Traveller Added";
    }

    public City searchCity(String cityName, String country) throws  NoSuchCityException
    {
        CityService cityService = new CityService(cityRepository);
        checkCityAvailability(cityName, country);
        return cityService.getCityByName(cityName.toUpperCase(),country);
    }

    public Traveller findFreeTicket(String FreeCity,String FreeCountry) throws NoSuchCityException, InterruptedException {
        JsonSaver jsc = new JsonSaver();
        new Thread(jsc).start();
        Thread t = new JsonSaver();
        t.start();
        t.join();
        CityService cityService = new CityService(cityRepository);
        Traveller testTraveller = new YoungTraveller();
        if(FreeCity==null)
            return null;
        return testTraveller.calculate_free_ticket(cityService.getCityByName(FreeCity.toUpperCase(),FreeCountry),travellers);
    }

    public ArrayList<City> findXBestCities(String name,int number) throws InterruptedException
    {
        List<City> bestCities = null;
        Thread t = new JsonSaver();
        Thread t2 = new FetchCities(cityRepository);
        t.start();
        t2.start();
        t.join();
        t2.join();
        for (Traveller traveller : travellers) {
            if(traveller.getName().equals(name))
            {
                bestCities=traveller.compareCities(CitiesHashMap);
                bestCities=traveller.compareCities(number,bestCities);
            }
        }
        if(bestCities==null)
        {
            return null;
        }
        return (ArrayList<City>) bestCities;
    }

    public City findBestCityCollaborating(String name) throws InterruptedException {
        CollaborativeFiltering collaborativeFiltering = new CollaborativeFiltering();
        Thread t = new JsonSaver();
        Thread t2 = new FetchCities(cityRepository);
        t.start();
        t2.start();
        t.join();
        t2.join();
        removeDuplicateTravellers(travellers);
        for (Traveller traveller : travellers) {
            if(traveller.getName().equals(name))
            {
                return collaborativeFiltering.bestCity(traveller,travellers);
            }
        }
        return null;
    }

}
