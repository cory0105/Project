package PTR.PTR.service;

import PTR.PTR.repository.FeedRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }
}
