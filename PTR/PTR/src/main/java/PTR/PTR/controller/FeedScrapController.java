package PTR.PTR.controller;

import PTR.PTR.service.FeedScrapService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedScrapController {
    FeedScrapService feedScrapService;

    public FeedScrapController(FeedScrapService feedScrapService) {
        this.feedScrapService = feedScrapService;
    }
}
