package PTR.PTR.controller;

import PTR.PTR.service.FeedService;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FeedController {
    FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }
}
