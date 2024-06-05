package PTR.PTR.service;

import PTR.PTR.repository.InquiryRepository;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {
    InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }
}
