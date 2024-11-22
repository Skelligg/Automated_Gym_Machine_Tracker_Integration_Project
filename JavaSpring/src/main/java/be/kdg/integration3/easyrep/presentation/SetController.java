package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.model.sessions.Set;
import be.kdg.integration3.easyrep.service.SetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sets")
public class SetController {

    private static Logger logger = LoggerFactory.getLogger(SetController.class);

    private SetService setService;

    @Autowired
    public SetController(SetService setService) {
        this.setService = setService;
    }

    @GetMapping
    public String getSetsView(Model model){
        logger.debug("getting SetsView");
        List<Set> sets = setService.getSets();
        logger.debug(sets.toString());
        model.addAttribute("machineSets", sets);
        return "GymGoer/sets";
    }
}
