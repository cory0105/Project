package PTR.PTR.controller;

import PTR.PTR.service.CalendarService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {
    CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }
}
