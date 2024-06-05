package PTR.PTR.service;

import PTR.PTR.repository.TrainingReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingReviewService {
    TrainingReviewRepository trainingReviewRepository;

    public TrainingReviewService(TrainingReviewRepository trainingReviewRepository) {
        this.trainingReviewRepository = trainingReviewRepository;
    }
}
