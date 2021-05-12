package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.Service.CityService;
import org.hua.dit.oopii_21950_219113.Service.TravellersService;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Here in the future we will have the main functionality of the web app, for now we hard coded most of it for the 1st
 * deliverable's sake.
 */
//@CrossOrigin(origins= "http://localhost:3000")
@CrossOrigin("*")
@RestController
@RequestMapping(path = "/") //Because we are hard coding data every time we will be showing stats for the same traveller.
public class TravellersController {

    /*
    NOT RECOMENED BY SPRING, for the purposes of this project we can ignore that because this helps us, as a small team
    write and test code faster, also it is an automation which makes the Dependency injection real easy to understand.
     */
    @Autowired
    private final TravellersService travellersService;
    private final CityRepository cityRepository;
    /**
     *
     * @param travellersService initialize the class object with a given TravellerService
     * @param cityRepository
     */
    public TravellersController(TravellersService travellersService, CityRepository cityRepository) {
        this.travellersService = travellersService;
        this.cityRepository=cityRepository;
    }

    //We do throws because everything is hardcoded, so most likely we will not have any exceptions
    @GetMapping
    public String getTraveller() {
        try {
            return travellersService.getTraveller();
        } catch (IOException | NoSuchOpenWeatherCityException e) {

//            System.out.println("Oupss! Something went wrong try to reload the page!");
            //TODO: LOG the failure
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping(path = "/travellers")
    public ArrayList<Traveller> getAllTravellers()
    {
        return travellersService.getAllTravellers();
    }

    //@PostMapping //https://www.youtube.com/watch?v=i-hoSg8iRG0 upload image section
    @GetMapping( path = "{name}/bestCity")
    public City findBestCityForTheUser(@PathVariable("name")String name)
    {
        return  travellersService.findBestCityForTheUser(name);
    }

    @GetMapping(path = "{cityName}/{country}/search")
    public City checkCity(@PathVariable("cityName")String cityName,@PathVariable("country")String country) throws Exception {
        return travellersService.searchCity(cityName,country);
    }

    @PostMapping( path = "/addYoungTraveller")
    public String  addNewTraveller(@RequestBody YoungTraveller traveller) throws IOException {
        return travellersService.addNewTraveller(traveller);
    }

    @PostMapping( path = "/addMiddleTraveller")
    public String  addNewTraveller(@RequestBody MiddleTraveller traveller) throws IOException {
        return travellersService.addNewTraveller(traveller);
    }

    @PostMapping( path = "/addElderTraveller")
    public String  addNewTraveller(@RequestBody ElderTraveller traveller) throws IOException {
        return travellersService.addNewTraveller(traveller);
    }

    //TODO: για το free ticket θα βαλουμε ενα switch case για του 12 μηνες του χρονου και καθε μηνα θα εχουμε διαφορερικη πολη για free ticket (αυτο θα γινετε στο frontend)



}

