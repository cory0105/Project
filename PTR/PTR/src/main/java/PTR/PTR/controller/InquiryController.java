package PTR.PTR.controller;

import PTR.PTR.model.Inquiry;
import PTR.PTR.model.InquiryReply;
import PTR.PTR.model.User;
import PTR.PTR.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InquiryController {
    InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }
    // 1:1 문의하기
    @PostMapping("/askInquiry")
    public ResponseEntity<Inquiry> askInquiry(@RequestBody Inquiry inquiry){
        return new ResponseEntity<>(inquiryService.askInquiry(inquiry), HttpStatus.OK);
    }
    @PostMapping("findInquiry")
    public ResponseEntity<List<Inquiry>> findInquiry(){
        return new ResponseEntity<>(inquiryService.findInquiry(), HttpStatus.OK);
    }
    @PostMapping("myInquiry")
    public ResponseEntity<List<Inquiry>> myInquiry(@RequestBody User user){
        return new ResponseEntity<>(inquiryService.myInquiry(user), HttpStatus.OK);
    }


}
