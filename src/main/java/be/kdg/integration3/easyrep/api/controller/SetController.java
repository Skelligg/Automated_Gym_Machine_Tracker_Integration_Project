package be.kdg.integration3.easyrep.api.controller;


import be.kdg.integration3.easyrep.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class SetController {


    private SetService setService;

    @Autowired
    public SetController(SetService setService) {
        this.setService = setService;
    }

    @GetMapping
    public void inputSet(@RequestParam LocalTime startTime, LocalTime endTime, int repCount){
        setService.createSet(startTime, endTime, repCount);
    }

}
