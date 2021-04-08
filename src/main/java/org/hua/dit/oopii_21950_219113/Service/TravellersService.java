package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.hua.dit.oopii_21950_219113.entitys.weather.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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

    public String getTestTraveller() throws IOException, NoSuchOpenWeatherCityException {

        CityService cityService = new CityService(cityRepository);

        ArrayList<Traveller> travellers = new ArrayList<>();

        ArrayList<City> cities = (ArrayList<City>) cityService.getCities();

        YoungTraveller testTraveller = new YoungTraveller();

        List<City> bestCities;


        //TODO: ADD MORE TRAVELLERS, USE GETTERS AND SETTERS
        String FreeCity = "Amsterdam";
        String FreeCountry = "nl";

        YoungTraveller youngTraveller = new YoungTraveller(19,"Nick","Athens","gr",1,10,10,6,10,9,2,10,8,1);
        MiddleTraveller middleTraveller = new MiddleTraveller(30,"George" ,"Paris","fr",0,10,10,10,0,0,10,10,10,3);
        ElderTraveller elderTraveller = new ElderTraveller(70,"Babis","Barcelona","es",2,10,7,8,5,7,3,10,9,5);
        travellers.add(youngTraveller);
        travellers.add(middleTraveller);
        travellers.add(elderTraveller);
        for (Traveller traveller : travellers)
        {
            bestCities= traveller.compareCities(cities);
            System.out.println("The best city for "+traveller.getName()+" is :"+bestCities.get(0).getCityName());
            bestCities=traveller.compareCities(3,bestCities);
            System.out.println("And the next 3 best cities for "+traveller.getName()+" are: ");
            for (City bestCity : bestCities)
            {
                System.out.println(bestCity.getCityName());
            }
        }

//        try {
//            if(Objects.requireNonNull(youngTraveller).calculate_free_ticket(cityService.getCityByName(FreeCity.toUpperCase(),FreeCountry), travellers).equals(youngTraveller))
//                System.out.println("You WON!!!!");
//            else
//                System.out.println("Well, sometimes you win sometimes you dont!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
//            return "The similarity for Nikos: " + Objects.requireNonNull(youngTraveller).calculate_similarity(cityService.getCityByName(FreeCity.toUpperCase(),"gr"));
            return ("And after all we have a free ticket for: "+FreeCity+" and the traveller that he gets it is: "+testTraveller.calculate_free_ticket(cityService.getCityByName(FreeCity.toUpperCase(),FreeCountry),travellers).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
