package PTR.PTR.service;

import PTR.PTR.repository.FeedCommentLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedCommentLikeService {
    FeedCommentLikeRepository feedCommentLikeRepository;

    public FeedCommentLikeService(FeedCommentLikeRepository feedCommentLikeRepository) {
        this.feedCommentLikeRepository = feedCommentLikeRepository;
    }
}
