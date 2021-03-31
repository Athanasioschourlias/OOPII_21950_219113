package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class CityController {

    @Autowired
    private final CityService cityService;

    /**
     * We created a custom constructor in order to have in memory the correct object of CityService when creating new
     * CityController objects.
     *
     * @param cityService
     */
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }



}
