package PTR.PTR.service;

import PTR.PTR.repository.LectureUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureUserService {
    LectureUserRepository lectureUserRepository;

    public LectureUserService(LectureUserRepository lectureUserRepository) {
        this.lectureUserRepository = lectureUserRepository;
    }
}
