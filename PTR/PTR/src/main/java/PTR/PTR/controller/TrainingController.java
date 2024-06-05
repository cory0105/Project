package PTR.PTR.controller;

import PTR.PTR.service.TrainingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {
    TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }
}
