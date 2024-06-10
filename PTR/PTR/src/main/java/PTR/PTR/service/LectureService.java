package PTR.PTR.service;

import PTR.PTR.model.Category;
import PTR.PTR.model.Lecture;
import PTR.PTR.model.Notification;
import PTR.PTR.model.Teacher;
import PTR.PTR.repository.LectureRepository;
import PTR.PTR.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
    LectureRepository lectureRepository;
    NotificationService notificationService;

    public LectureService(LectureRepository lectureRepository, NotificationService notificationService) {
        this.lectureRepository = lectureRepository;
        this.notificationService = notificationService;
    }

    public Lecture createLecture(Lecture lecture){
        lecture.setCreatedAt(LocalDateTime.now());
        notificationService.lectureNotification(lecture.getTeacher());
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findTeacherLecture(Teacher teacher){
        return lectureRepository.findByTeacher(teacher);
    }

    public List<Lecture> findCategoryLecture(Category category){
        return lectureRepository.findByCategory(category);
    }

    public List<Lecture> searchLecture(String search){
        return lectureRepository.findAll().stream()
                .filter(l->l.getLectureName().contains(search) ||l.getDescription().contains(search)).collect(Collectors.toList());
    }

    public List<Lecture> findPriceLecture(int minPrice, int maxPrice){
        return lectureRepository.findAll().stream()
                .filter(l->l.getPrice()>=minPrice && l.getPrice()<=maxPrice).collect(Collectors.toList());
    }
}
