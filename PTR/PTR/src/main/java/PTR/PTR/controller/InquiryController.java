package PTR.PTR.controller;

import PTR.PTR.service.InquiryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InquiryController {
    InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }
}
