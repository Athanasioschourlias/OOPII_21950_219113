package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.entitys.City;
import org.hua.dit.oopii_21950_219113.entitys.Traveller;
import org.hua.dit.oopii_21950_219113.entitys.YoungTraveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

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

    public String getTestTraveller() throws Exception {

        ArrayList<Traveller> travellers = new ArrayList<>();

        CityService cityService = new CityService(cityRepository);


        String City = "Athens";
        //TODO: ADD MORE TRAVELLERS

        YoungTraveller youngTraveller = new YoungTraveller(19,"Nick",City,"gr",1,2,3,4,5,6,7,8,9,0);

        travellers.add(youngTraveller);

        double similarityNumber=youngTraveller.calculate_similarity(cityService.getCityByName(City.toUpperCase(),"gr"));

        if(youngTraveller.calculate_free_ticket(cityService.getCityByName(City.toUpperCase(),"gr"), travellers).equals(youngTraveller))
            System.out.println("You WON!!!!");
        else
            System.out.println("Well, sometimes you win sometimes you dont!");



        String s = "The similarity for Nikos: " + similarityNumber;

        return s;
//        System.out.println(similarityNumber);
    }


}
