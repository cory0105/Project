package PTR.PTR.controller;

import PTR.PTR.service.CalendarDietRecordService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarDietRecordController {
    CalendarDietRecordService calendarDietRecordService;

    public CalendarDietRecordController(CalendarDietRecordService calendarDietRecordService) {
        this.calendarDietRecordService = calendarDietRecordService;
    }
}
