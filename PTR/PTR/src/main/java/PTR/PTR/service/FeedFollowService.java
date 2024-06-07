package PTR.PTR.service;

import PTR.PTR.repository.FeedFollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedFollowService {
    FeedFollowRepository feedFollowRepository;

    public FeedFollowService(FeedFollowRepository feedFollowRepository) {
        this.feedFollowRepository = feedFollowRepository;
    }
}
