package PTR.PTR.service;

import PTR.PTR.repository.CalendarRepository;
import org.springframework.stereotype.Service;

@Service
public class CalendarDietPlanService {
    CalendarRepository calendarRepository;

    public CalendarDietPlanService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }
}
