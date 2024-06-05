package PTR.PTR.service;

import PTR.PTR.repository.FeedCommentRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedCommentService {
    FeedCommentRepository feedCommentRepository;

    public FeedCommentService(FeedCommentRepository feedCommentRepository) {
        this.feedCommentRepository = feedCommentRepository;
    }
}
