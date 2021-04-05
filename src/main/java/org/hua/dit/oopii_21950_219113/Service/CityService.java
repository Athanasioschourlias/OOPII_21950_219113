package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.CityAlreadyExistsException;
import org.hua.dit.oopii_21950_219113.entitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//FIXME: ADD EXCEPTION BLOCKS WHERE IS NEEDED
@Service //Same as @Component
//Here we essentially want to create a spring Bean.
public class CityService {

    @Autowired // Dependency injection
    private final CityRepository cityRepository;

    /**
     * Since we have injected the CityRepository dependency in our class we want now to create a constructor that we
     * can pass a CityRepository Repository(not exactly an interface). Needed for testing.
     *
     * @param cityRepository
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

        if( cityRepository.findCityById(CITY_NAME.toUpperCase(),country).isPresent() ){
            throw new CityAlreadyExistsException(CITY_NAME.toUpperCase());
        }

//        Vectors vectors= new Vectors();
        int tmpCafe,tmpStadiums,tmpMuseums,tmpSeas,tmpRestaurants,tmpMountains,tmpHotel,tmpMetro,tmpBars,tmpSun;
        OpenData openData = new OpenData();
        String article;
        Check check = new Check();
        CountWords countWords= new CountWords();
        City c;

        article=openData.RetrieveData(CITY_NAME);
        tmpCafe= check.checkVectorValue(countWords.countCriterionfCity(article,"cafe"));
        tmpStadiums= check.checkVectorValue(countWords.countCriterionfCity(article,"stadium"));
        tmpMuseums= check.checkVectorValue(countWords.countCriterionfCity(article,"museum"));
        tmpSeas= check.checkVectorValue(countWords.countCriterionfCity(article,"sea"));
        tmpRestaurants= check.checkVectorValue(countWords.countCriterionfCity(article,"restaurant"));
        tmpMountains= check.checkVectorValue(countWords.countCriterionfCity(article,"mountain"));
        tmpHotel=check.checkVectorValue(countWords.countCriterionfCity(article,"hotel"));
        tmpMetro=check.checkVectorValue(countWords.countCriterionfCity(article,"metro"));
        tmpBars=check.checkVectorValue(countWords.countCriterionfCity(article,"bar"));
        tmpSun=check.checkVectorValue(countWords.countCriterionfCity(article,"sun"));

        //The constructor handles the API call to the OpenWeatherMap in order to get the latitude & longitude.
        //TODO: USE GETTERS AND SETTERS
        c = new City(CITY_NAME,country,tmpCafe,tmpSeas,tmpMuseums,tmpRestaurants,tmpStadiums,tmpMountains,tmpHotel,tmpMetro,tmpBars,tmpSun);

        cityRepository.save(c);
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

