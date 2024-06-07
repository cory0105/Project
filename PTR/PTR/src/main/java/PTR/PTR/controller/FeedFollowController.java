package PTR.PTR.controller;

import PTR.PTR.service.FeedFollowService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedFollowController {
    FeedFollowService feedFollowService;

    public FeedFollowController(FeedFollowService feedFollowService) {
        this.feedFollowService = feedFollowService;
    }
}
