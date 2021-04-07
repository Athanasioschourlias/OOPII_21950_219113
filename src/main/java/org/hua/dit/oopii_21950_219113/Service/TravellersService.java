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

        //Creating random instance.
        Random random = new Random();

        ArrayList<Traveller> travellers = new ArrayList<>();

        //TODO: ADD MORE TRAVELLERS, USE GETTERS AND SETTERS
        String Cities = "Athens";

//        HashMap<Integer,node> Ct = new HashMap<>();
//
//        JSONParser parser = new JSONParser();
//        JSONArray a = null;
//        try {
//            a = (JSONArray) parser.parse(new FileReader("./other/city.list.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        int j = 0;
//        assert a != null;
//        for (Object o : a)
//        {
//            JSONObject city = (JSONObject) o;
//
//            String name = (String) city.get("name");
//            String country = (String) city.get("country");
//
//            Ct.put(j, new node(name, country));
//            j++;
//
//        }
//
        YoungTraveller youngTraveller = null;
        try {
            youngTraveller = new YoungTraveller(19,"Nick",Cities,"gr",1,2,3,4,5,6,7,8,9,0);
        } catch (IOException | NoSuchOpenWeatherCityException e) {
            e.printStackTrace();
        }

        travellers.add(youngTraveller);
//        YoungTraveller youngTraveller = null;
//        MiddleTraveller middleTraveller = null;
//        ElderTraveller elderTraveller = null;
//        for(int i = 0; i < 75; i++){
//
//            int rmd = random.nextInt(Ct.size());
//            if( i < 25) {
//                try {
//                    youngTraveller = new YoungTraveller(Ct.get(rmd).getName(), Ct.get(rmd).getCountry());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (NoSuchOpenWeatherCityException e) {
//                    e.printStackTrace();
//                }
//                travellers.add(youngTraveller);
//            }else if(i < 50) {
//                try {
//                    middleTraveller = new MiddleTraveller(Ct.get(rmd).getName(), Ct.get(rmd).getCountry());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (NoSuchOpenWeatherCityException e) {
//                    e.printStackTrace();
//                }
//                travellers.add(middleTraveller);
//            }else {
//                try {
//                    elderTraveller = new ElderTraveller(Ct.get(rmd).getName(), Ct.get(rmd).getCountry());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (NoSuchOpenWeatherCityException e) {
//                    e.printStackTrace();
//                }
//                travellers.add(elderTraveller);
//            }
//        }
//
//        int i = 0;
//        for(Traveller traveller : travellers){
////            int rmd = random.nextInt(i);
//            City c = null;
//            try {
//                c = new City(Ct.get(i).getName(),Ct.get(i).getCountry());
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            } catch (NoSuchOpenWeatherCityException e) {
//                System.out.println(e.getMessage());
//            } catch (NoSuchWikipediaArticleException e) {
//                System.out.println(e.getMessage());
//            }
//
//            System.out.println(Ct.get(i).getName() + " " + Ct.get(i).getCountry());
//
//            try {
//                if(traveller.calculate_free_ticket(cityService.getCityByName(Cities.toUpperCase(),"gr"), travellers).equals(traveller))
//                    return "You WON!! The similarity for "+ traveller.getName() + ": " + traveller.calculate_similarity(c);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//            i++;
//        }
//
//        return null;
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

    /**
     * RANDOM NAME GENERATOR
     */
    // class variable
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    final Set<String> identifiers = new HashSet<>();

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public static class node{

        private String name;
        private String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public node(String name, String country){
            this.name=name;
            this.country=country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return Objects.equals(name, node.name) && Objects.equals(country, node.country);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, country);
        }
    }

}
