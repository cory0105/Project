package PTR.PTR.controller;

import PTR.PTR.service.CalendarExerciseRecordService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarExerciseRecordController {
    CalendarExerciseRecordService calendarExerciseRecordService;

    public CalendarExerciseRecordController(CalendarExerciseRecordService calendarExerciseRecordService) {
        this.calendarExerciseRecordService = calendarExerciseRecordService;
    }
}
