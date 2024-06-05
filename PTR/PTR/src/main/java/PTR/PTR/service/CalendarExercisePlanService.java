package PTR.PTR.service;

import PTR.PTR.repository.CalendarExercisePlanRepository;
import org.springframework.stereotype.Service;

@Service
public class CalendarExercisePlanService {
    CalendarExercisePlanRepository calendarExercisePlanRepository;

    public CalendarExercisePlanService(CalendarExercisePlanRepository calendarExercisePlanRepository) {
        this.calendarExercisePlanRepository = calendarExercisePlanRepository;
    }
}
