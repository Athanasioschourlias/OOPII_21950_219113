package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Service.CityService;
import org.hua.dit.oopii_21950_219113.entitys.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//FIXME: ADD EXCEPTION BLOCKS WHERE IS NEEDED
@RestController
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
        } catch (Exception e) {
            System.out.println("Something went wrong, please check your input and try again");
            System.out.println(e.toString());
            //TODO: LOG THE FAILURES, dont print it to the client/user.
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Printing to a client that we do not support this operation publicly, because we do not want any outsider
     * interfering with our database nad our data. We always catch an exception because the addNewCity() method all
     * it does is to throw a respective exception.
     * Cities?CITY_NAME={CITY_NAME}?country={country}
     */
    @PostMapping
    public void addNewCity(@RequestParam String CITY_NAME,
                           @RequestParam String country) throws IOException {
        //TODO: DEVELOPMENT CODE!!!
        cityService.addNewCity(CITY_NAME,country);


        //TODO: PRODUCTION CODE
//        try {
//            cityService.addNewCity();
//        } catch (NoSuchMethodException e) {
//            System.out.println(" We are sorry this is Not a valid Action :( ");
//        }
    }

}
