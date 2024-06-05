package PTR.PTR.service;

import PTR.PTR.repository.MessageBlockRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageBlockService {
    MessageBlockRepository messageBlockRepository;

    public MessageBlockService(MessageBlockRepository messageBlockRepository) {
        this.messageBlockRepository = messageBlockRepository;
    }
}
