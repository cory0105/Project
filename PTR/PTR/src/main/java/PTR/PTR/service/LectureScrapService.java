package PTR.PTR.service;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureScrap;
import PTR.PTR.model.User;
import PTR.PTR.repository.LectureScrapRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureScrapService {
    LectureScrapRepository lectureScrapRepository;

    public LectureScrapService(LectureScrapRepository lectureScrapRepository) {
        this.lectureScrapRepository = lectureScrapRepository;
    }

    public LectureScrap scrapLecture(LectureScrap lectureScrap){
        lectureScrap.setCreatedAt(LocalDateTime.now());
        return lectureScrapRepository.save(lectureScrap);
    }

    public String deleteScrapLecture(LectureScrap lectureScrap){
        lectureScrapRepository.delete(lectureScrap);
        return "삭제";
    }
    
    public List<LectureScrap> myScrapLecture(User user){
        return lectureScrapRepository.findByUser(user);
    }
}
