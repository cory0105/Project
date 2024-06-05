package PTR.PTR.service;

import PTR.PTR.repository.InquiryReplyRepository;
import org.springframework.stereotype.Service;

@Service
public class InquiryReplyService {
    InquiryReplyRepository inquiryReplyRepository;

    public InquiryReplyService(InquiryReplyRepository inquiryReplyRepository) {
        this.inquiryReplyRepository = inquiryReplyRepository;
    }
}
