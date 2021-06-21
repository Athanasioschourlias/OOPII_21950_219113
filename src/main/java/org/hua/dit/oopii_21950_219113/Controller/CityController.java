package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchCityException;
import org.hua.dit.oopii_21950_219113.Service.CityService;
import org.hua.dit.oopii_21950_219113.entitys.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.List;

//FIXME: ADD EXCEPTION BLOCKS WHERE IS NEEDED
@CrossOrigin("*") //!!!Not an ideal solution to the cross origin problem!!!
@RestController //
@RequestMapping(path = "/Cities")
public class CityController {

    /*
    NOT RECOMENED BY SPRING, for the purposes of this project we can ignore that because this helps us, as a small team
    write and test code faster, also it is an automation which makes the Dependency injection real easy to understand.
     */
    @Autowired
    private final CityService cityService;

    /**
     *
     * @param cityService Initializing a new object and giving it the
     */
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     *
     * @return A list with all the cities we stored in the database.
     */
    @GetMapping
    public List<City> getAllCities(){
        //No need for a try catch block, this is a non-user depended method.
        //FIXME: TALK A BIT ABOUT THAT EXCEPTION.
        try{
            return cityService.getCities();
        }catch (Exception e){
            System.out.println("Something went wrong :(, please try to refresh the page!");
        }

        return null;
    }

    /**
     * To visit this page you can type at your client => http://localhost:8080/Cities/{CITY_NAME}?country={COUNTRY}
     *
     * @param cityName The name of the city object the client would like to see. Also a path variable "/{CITY_NAME}"
     * @param country Because of the way our database is structured we have to provide the {COUNTRY} code alongside with
     *                the city's name.
     * @return The city object the client requested.
     */
    @GetMapping(path = "{CityName}")
    public City getCityByName(@PathVariable("CityName") String cityName,
                              @RequestParam String country){
        //FIXME: production ready?
        try {
            return cityService.getCityByName(cityName, country);
        } catch (NoSuchCityException e) {
            System.out.println("Something went wrong, please check your input and try again");
            //TODO: LOG THE FAILURES, dont print it to the client/user.
//            e.printStackTrace();
        }
        return null;
    }

    /**
     * IRL Querry to add a new city with a name and it's country code ../Cities?CITY_NAME={CITY_NAME}&country={country}
     *
     * @param CITY_NAME The name of the city we want to enter
     * @param country country={country code}
     */
    @PostMapping
    public void addNewCity(@RequestParam String CITY_NAME,
                           @RequestParam String country) {
        try {
            cityService.addNewCity(CITY_NAME,country);
        } catch (IOException e) {
            System.out.println("Something went worng, try again!");
        } catch (CityAlreadyExistsException e) {
            System.out.println("A city with the name of" + CITY_NAME.toLowerCase()
                    + " and country code of "+country
                    +" Already exists in our database"
            );
        }

    }

}
