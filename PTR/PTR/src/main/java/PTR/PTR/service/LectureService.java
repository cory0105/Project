package PTR.PTR.service;

import PTR.PTR.repository.LectureRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }
}
