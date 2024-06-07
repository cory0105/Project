package PTR.PTR.controller;

import PTR.PTR.service.NoticeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
}
