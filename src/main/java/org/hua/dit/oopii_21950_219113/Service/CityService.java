package org.hua.dit.oopii_21950_219113.Service;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
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
     *
     * @param CITY_NAME
     * @param country
     */
    public void addNewCity(String CITY_NAME, String country) throws IOException {

        Vectors vectors= new Vectors();
        int tmpCafe;
        int tmpStadiums;
        int tmpMuseums;
        int tmpSeas;
        int tmpRestaurants;
        int tmpMountains;
        int tmpHotel;
        int tmpMetro;
        int tmpBars;
        int tmpSun;
        //TODO:we will put 10 cities into the data base and by the program starting we'll take them
        //TODO: if value is grater than 10 make it 10!
        OpenData openData = new OpenData();
        String article;
        String appid = "4abb3288d8abfd8b3b72670196c0175f";
        article=openData.RetrieveData(CITY_NAME,country,appid);
        CountWords countWords= new CountWords();
        Check check = new Check();
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

        City c = new City(CITY_NAME,country,tmpCafe,tmpSeas,tmpMuseums,tmpRestaurants,tmpStadiums,tmpMountains,tmpHotel,tmpMetro,tmpBars,tmpSun);

        //DEV CODE
        cityRepository.save(c);

        //PRODUCTION CODE
        //throw new NoSuchMethodException();
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

