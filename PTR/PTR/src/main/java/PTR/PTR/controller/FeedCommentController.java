package PTR.PTR.controller;

import PTR.PTR.service.FeedCommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedCommentController {
    FeedCommentService feedCommentService;

    public FeedCommentController(FeedCommentService feedCommentService) {
        this.feedCommentService = feedCommentService;
    }
}
