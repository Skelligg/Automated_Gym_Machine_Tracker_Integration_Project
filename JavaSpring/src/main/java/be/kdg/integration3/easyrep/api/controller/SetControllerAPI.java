package be.kdg.integration3.easyrep.api.controller;


import be.kdg.integration3.easyrep.service.MachineSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/setInput")
public class SetControllerAPI {

    Logger logger = LoggerFactory.getLogger(SetControllerAPI.class);
    private MachineSetService machineSetService;

    @Autowired
    public SetControllerAPI(MachineSetService machineSetService) {
        this.machineSetService = machineSetService;
    }

    @GetMapping
    public void inputSet(@RequestParam int setNumber, @RequestParam String setTime, @RequestParam int repCount){
//    public void inputSet(@RequestParam String startTime, String endTime, int repCount){
//        String[] startTimeParts = startTime.split(":");
//        int startHour = Integer.parseInt(startTimeParts[0]);
//        int startMinute = Integer.parseInt(startTimeParts[1]);
//        int startSecond = Integer.parseInt(startTimeParts[2]);
//
//        LocalTime startLocalTime = LocalTime.of(startHour, startMinute, startSecond);

//        String[] endTimeParts = endTime.split(":");
//        int endHour = Integer.parseInt(endTimeParts[0]);
//        int endMinute = Integer.parseInt(endTimeParts[1]);
//        int endSecond = Integer.parseInt(endTimeParts[2]);

//        LocalTime endLocalTime = LocalTime.of(endHour, endMinute, endSecond);


//        machineSetService.addSet(startLocalTime, endLocalTime, repCount);
        logger.debug("Inputting values");
        machineSetService.addSet(setNumber, setTime, repCount, weightCount);
    }

}
