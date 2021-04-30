package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchWikipediaArticleException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

//FIXME: ADD EXCEPTION BLOCKS WHERE IS NEEDED
@Service //Same as @Component
//Here we essentially want to create a spring Bean.
public class CityService {

    @Autowired // Dependency injection
    private final CityRepository cityRepository;

    /**
     * Since we have injected the CityRepository dependency in our class we want now to create a constructor that we
     * can pass a CityRepository Repository. Needed for testing.
     *
     * @param cityRepository Interface which extends JpaRepository necessary for our database communications and custom
     *                       Query's for fetching data with a special way.
     */
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     *
     * @return A list with all the city objects and their values from the database
     */
    public List<City> getCities(){
        return cityRepository.findAll();
    }

    /**
     * We are taking the city name and the code of the country is in and If we do not have it duplicate in our database
     * then we collect the necessary info about the city(Cafes, stadiums.... etc) with the respective error corrections
     * and checks then we create a new city object and storing it with springs save() method.
     *
     * @param CITY_NAME Name of the City we want to add
     * @param country The country code the city is in.
     *
     * @throws IOException OpenData may experience some issues due to external factors, so i will not make a successful
     * API call and it will produce most Likely a FileNotFoundException
     * @throws CityAlreadyExistsException The city already exists in our database.
     */
    public void addNewCity(String CITY_NAME, String country) throws IOException, CityAlreadyExistsException {

        /*
            Here we try to fetch the city from the database, if now is already in our database we are throwing an exception
            and dont go any further because we dont want and we cant have duplicates in our database.
         */

        if( cityRepository.findCityById(CITY_NAME.toUpperCase(),country).isPresent() ){
            throw new CityAlreadyExistsException(CITY_NAME.toUpperCase());
        }
        //The constructor handles the API call to the OpenWeatherMap in order to get the latitude & longitude.
        City c = null;
        try {
            c = new City(CITY_NAME,country);
        } catch (NoSuchOpenWeatherCityException | NoSuchWikipediaArticleException e) {
            //TODO:LOG THE STACKTRACE
            //     e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if(c != null)
            cityRepository.save(c);
        else
            //TODO:When we add UI remove the sout.
            System.out.println("There was a problem saving this city to our database");
    }

    //TODO: VALIDATE THE EXCEPTION.
    /**
     *
     * @param cityName The name of the city the client wants to "see"
     * @param country the country code, due to our database structure it is required to be in our query, also in order
     *                not to return the wrong city object(a city with the same name in an other country).
     * @return the requested city object.
     * @throws Exception In case something internally goes wrong or an I/O error occurs.
     */
    public City getCityByName(String cityName, String country) throws Exception{

        return cityRepository.findByName(cityName, country);

    }
}

