package org.hua.dit.oopii_21950_219113.entitys;

import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Service.CityService;
import org.hua.dit.oopii_21950_219113.Service.TravellersService;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FetchCities extends Thread
{
    CityRepository cityRepository;
    TravellersService travellersService= new TravellersService(cityRepository);

    public FetchCities(CityRepository cityRepository)
    {
        this.cityRepository=cityRepository;
    }

    @Override
    public void run()
    {
        CityService cityService = new CityService(cityRepository);
        travellersService.CitiesHashMap = (HashMap<String, City>) cityService.getCities().stream().collect(Collectors.toMap(City::getCityName, Function.identity()));
    }
}
