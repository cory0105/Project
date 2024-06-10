package PTR.PTR.service;

import PTR.PTR.model.Message;
import PTR.PTR.model.User;
import PTR.PTR.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message){
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> viewMessage(User user, User user2){
        List<Message> messages = messageRepository.findByUserAndUser2(user, user2);
        messages.addAll(messageRepository.findByUserAndUser2(user2,user));
        return messages.stream().sorted(Comparator.comparingLong(Message::getId)).collect(Collectors.toList());
    }
}
