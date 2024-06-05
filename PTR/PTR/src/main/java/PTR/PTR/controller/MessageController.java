package PTR.PTR.controller;

import PTR.PTR.service.MessageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
}
