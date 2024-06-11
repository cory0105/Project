package PTR.PTR.controller;

import PTR.PTR.service.UserFollowService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFollowController {
    UserFollowService userFollowService;

    public UserFollowController(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }
}
