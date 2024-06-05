package PTR.PTR.service;

import PTR.PTR.repository.NotificationUserRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationUserService {
    NotificationUserRepository notificationUserRepository;

    public NotificationUserService(NotificationUserRepository notificationUserRepository) {
        this.notificationUserRepository = notificationUserRepository;
    }
}
