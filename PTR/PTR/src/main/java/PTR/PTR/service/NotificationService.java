package PTR.PTR.service;

import PTR.PTR.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
