package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Service.MiddleTravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/MiddleTraveller")
public class MiddleTravellerController {

    private final MiddleTravellerService middleTravellerService;

    @Autowired
    public MiddleTravellerController(MiddleTravellerService middleTravellerService) {
        this.middleTravellerService = middleTravellerService;
    }
//    @Autowired
//    private final TravellerService travellerService; //Object instatiation???!???
    //TODO:see what i will do with the travellers

}