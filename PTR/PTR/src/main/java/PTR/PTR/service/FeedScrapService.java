package PTR.PTR.service;

import PTR.PTR.repository.FeedScrapRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedScrapService {
    FeedScrapRepository feedScrapRepository;

    public FeedScrapService(FeedScrapRepository feedScrapRepository) {
        this.feedScrapRepository = feedScrapRepository;
    }
}
