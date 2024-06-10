package PTR.PTR.service;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.Notification;
import PTR.PTR.model.Teacher;
import PTR.PTR.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    NotificationRepository notificationRepository;
    NotificationUserService notificationUserService;

    public NotificationService(NotificationRepository notificationRepository, NotificationUserService notificationUserService) {
        this.notificationRepository = notificationRepository;
        this.notificationUserService = notificationUserService;
    }

    public void lectureNotification(Teacher teacher){
        Notification notification = new Notification();
        notification.setTitle(teacher.getUser().getUsername()+"님의 새로운 영상이 업로드 되었습니다.");
        notification.setText(teacher.getUser().getUsername()+"님의 새로운 영상이 업로드 되었습니다.");
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
        notificationUserService.lectureNotificationUser(notification, teacher);
    }
}
