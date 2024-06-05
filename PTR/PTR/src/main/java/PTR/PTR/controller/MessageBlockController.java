package PTR.PTR.controller;

import PTR.PTR.service.MessageBlockService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageBlockController {
    MessageBlockService messageBlockService;

    public MessageBlockController(MessageBlockService messageBlockService) {
        this.messageBlockService = messageBlockService;
    }
}
