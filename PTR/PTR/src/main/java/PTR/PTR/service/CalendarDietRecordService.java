package PTR.PTR.service;

import PTR.PTR.repository.CalendarDietRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class CalendarDietRecordService {
    CalendarDietRecordRepository calendarDietRecordRepository;

    public CalendarDietRecordService(CalendarDietRecordRepository calendarDietRecordRepository) {
        this.calendarDietRecordRepository = calendarDietRecordRepository;
    }
}
