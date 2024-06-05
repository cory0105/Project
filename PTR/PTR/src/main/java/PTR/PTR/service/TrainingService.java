package PTR.PTR.service;

import PTR.PTR.repository.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }
}
