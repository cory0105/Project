package PTR.PTR.controller;

import PTR.PTR.service.FeedCommentLikeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedCommentLikeController {
    FeedCommentLikeService feedCommentLikeService;

    public FeedCommentLikeController(FeedCommentLikeService feedCommentLikeService) {
        this.feedCommentLikeService = feedCommentLikeService;
    }
}
