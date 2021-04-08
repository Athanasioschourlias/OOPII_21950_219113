package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;

import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchWikipediaArticleException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
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

    public String getTestTraveller() {

        CityService cityService = new CityService(cityRepository);

        ArrayList<Traveller> travellers = new ArrayList<>();

        //TODO: ADD MORE TRAVELLERS, USE GETTERS AND SETTERS
        String Cities = "Athens";

        YoungTraveller youngTraveller = null;
        youngTraveller = new YoungTraveller();


        travellers.add(youngTraveller);

        try {
            if(Objects.requireNonNull(youngTraveller).calculate_free_ticket(cityService.getCityByName(Cities.toUpperCase(),"gr"), travellers).equals(youngTraveller))
                System.out.println("You WON!!!!");
            else
                System.out.println("Well, sometimes you win sometimes you dont!");
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            return "The similarity for Nikos: " + Objects.requireNonNull(youngTraveller).calculate_similarity(cityService.getCityByName(Cities.toUpperCase(),"gr"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
