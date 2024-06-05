package PTR.PTR.service;

import PTR.PTR.repository.FeedLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedLikeService {
    FeedLikeRepository feedLikeRepository;

    public FeedLikeService(FeedLikeRepository feedLikeRepository) {
        this.feedLikeRepository = feedLikeRepository;
    }
}
