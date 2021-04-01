package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.entitys.YoungTraveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

        YoungTraveller youngTraveller = new YoungTraveller(19,"Nick","Athens","gr",1,2,3,4,5,6,7,8,9,0);
        CityService cityService = new CityService(cityRepository);

        double similarityNumber=youngTraveller.calculate_similarity(cityService.getCityByName("ATHENS","gr"));

        String s = "The similarity for Nikos: " + similarityNumber;

        return s;
//        System.out.println(similarityNumber);
    }

    //TODO: IMPLEMENT the business logic.

}
