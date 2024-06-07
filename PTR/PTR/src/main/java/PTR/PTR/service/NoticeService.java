package PTR.PTR.service;

import PTR.PTR.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
}
