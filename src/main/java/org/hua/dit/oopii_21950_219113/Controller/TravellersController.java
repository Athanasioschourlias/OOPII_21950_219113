package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Service.CityService;
import org.hua.dit.oopii_21950_219113.Service.TravellersService;
import org.hua.dit.oopii_21950_219113.entitys.YoungTraveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/") //Because we are hard coding data every time we will be showing stats for the same traveller.
public class TravellersController {

    /*
    NOT RECOMENED BY SPRING, for the purposes of this project we can ignore that because this helps us, as a small team
    write and test code faster, also it is an automation which makes the Dependency injection real easy to understand.
     */
    @Autowired
    private final TravellersService travellersService;

    /**
     *
     * @param travellersService initialize the class object with a given TravellerService
     * @param cityRepository
     */
    public TravellersController(TravellersService travellersService, CityRepository cityRepository) {
        this.travellersService = travellersService;
    }

    //We do throws because everything is hardcoded, so most likely we will not have any exceptions
    @GetMapping
    public String testTraveller() throws Exception {

        return travellersService.getTestTraveller();
    }
}
