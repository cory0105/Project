package PTR.PTR.controller;

import PTR.PTR.service.NotificationUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationUserController {
    NotificationUserService notificationUserService;

    public NotificationUserController(NotificationUserService notificationUserService) {
        this.notificationUserService = notificationUserService;
    }
}
