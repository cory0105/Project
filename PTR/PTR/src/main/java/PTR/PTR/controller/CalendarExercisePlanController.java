package PTR.PTR.controller;

import PTR.PTR.service.CalendarExercisePlanService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarExercisePlanController {
    CalendarExercisePlanService calendarExercisePlanService;

    public CalendarExercisePlanController(CalendarExercisePlanService calendarExercisePlanService) {
        this.calendarExercisePlanService = calendarExercisePlanService;
    }
}
