package be.kdg.integration3.easyrep.presentation;

import be.kdg.integration3.easyrep.service.TensorFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/predict")
public class PredictionController {



    @Autowired
    private TensorFlowService tensorFlowService;

    public PredictionController(TensorFlowService tensorFlowService) {
        this.tensorFlowService = tensorFlowService;
    }

   /* @GetMapping("/predict")
    public String predict(@RequestParam(value = "exercise_id") float exercise, @RequestParam(value = "user_id") float user_id, Model model) {
        float rep = 10;
        float prediction = tensorFlowService.predict(exercise, rep, user_id);
        String predictionString = prediction + "";
        model.addAttribute("prediction", predictionString);
        return "home";
    }*/

    @GetMapping("/predict")
    public String predict(Model model) {
        float exercise_id = 100;
        float user_id = 1;
        float rep = 10;
        float prediction = tensorFlowService.predict(exercise_id, rep, user_id);
        String predictionString = prediction + "";
        model.addAttribute("prediction", predictionString);
        return "home";
    }
}
