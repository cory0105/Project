package PTR.PTR.controller;

import PTR.PTR.service.TrainingReviewService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingReviewController {
    TrainingReviewService trainingReviewService;

    public TrainingReviewController(TrainingReviewService trainingReviewService) {
        this.trainingReviewService = trainingReviewService;
    }
}
