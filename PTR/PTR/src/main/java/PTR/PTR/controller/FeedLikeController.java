package PTR.PTR.controller;

import PTR.PTR.service.FeedLikeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedLikeController {
    FeedLikeService feedLikeService;

    public FeedLikeController(FeedLikeService feedLikeService) {
        this.feedLikeService = feedLikeService;
    }
}
