package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Service.ElderTravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ElderTraveller")
public class ElderTravellerController {

    private final ElderTravellerService elderTravellerService;

    @Autowired
    public ElderTravellerController(ElderTravellerService elderTravellerService) {
        this.elderTravellerService = elderTravellerService;
    }

//    @Autowired
//    private final TravellerService travellerService; //Object instatiation???!???
    //TODO:see what i will do with the travellers

}