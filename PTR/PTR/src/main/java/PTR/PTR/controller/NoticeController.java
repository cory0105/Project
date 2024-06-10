package PTR.PTR.controller;

import PTR.PTR.model.Notice;
import PTR.PTR.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    @PostMapping("createNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice){
        return new ResponseEntity<>(noticeService.createNotice(notice), HttpStatus.OK);
    }
}
