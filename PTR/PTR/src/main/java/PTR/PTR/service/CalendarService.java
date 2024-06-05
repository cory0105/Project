package PTR.PTR.service;

import PTR.PTR.repository.CalendarRepository;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
    CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }
}
