package PTR.PTR.controller;

import PTR.PTR.service.CalendarDietPlanService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarDietPlanController {
    CalendarDietPlanService calendarDietPlanService;

    public CalendarDietPlanController(CalendarDietPlanService calendarDietPlanService) {
        this.calendarDietPlanService = calendarDietPlanService;
    }
}
