package PTR.PTR.service;

import PTR.PTR.repository.CalendarExerciseRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class CalendarExerciseRecordService {
    CalendarExerciseRecordRepository calendarExerciseRecordRepository;

    public CalendarExerciseRecordService(CalendarExerciseRecordRepository calendarExerciseRecordRepository) {
        this.calendarExerciseRecordRepository = calendarExerciseRecordRepository;
    }
}
