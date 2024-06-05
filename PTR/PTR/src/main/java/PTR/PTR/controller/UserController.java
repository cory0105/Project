package PTR.PTR.controller;

import PTR.PTR.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
