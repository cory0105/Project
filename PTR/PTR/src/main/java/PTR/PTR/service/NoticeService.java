package PTR.PTR.service;

import PTR.PTR.model.Notice;
import PTR.PTR.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoticeService {
    NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice createNotice(Notice notice){
        notice.setCreatedAt(LocalDateTime.now());
        return noticeRepository.save(notice);
    }
}
