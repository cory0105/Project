package PTR.PTR.controller;

import PTR.PTR.service.InquiryReplyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InquiryReplyController {
    InquiryReplyService inquiryReplyService;

    public InquiryReplyController(InquiryReplyService inquiryReplyService) {
        this.inquiryReplyService = inquiryReplyService;
    }
}
