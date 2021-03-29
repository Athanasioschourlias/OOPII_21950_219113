package org.hua.dit.oopii_21950_219113.Controller;

import org.hua.dit.oopii_21950_219113.Service.YoungTravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/YoungerTraveller")
public class YoungerTravellerController {

    @Autowired
    private final YoungTravellerService youngTravellerService;

    public YoungerTravellerController(YoungTravellerService youngTravellerService) {
        this.youngTravellerService = youngTravellerService;
    }

    @GetMapping
    public void getTraveller(){
        System.out.println("Hello world");
    }


    //TODO:see what i will do with the travellers

}
