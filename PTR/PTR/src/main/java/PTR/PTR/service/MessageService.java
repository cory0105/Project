package PTR.PTR.service;

import PTR.PTR.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
