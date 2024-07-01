package PTR.PTR.repository;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietPlan;
import PTR.PTR.model.CalendarExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarExerciseRecordRepository extends JpaRepository<CalendarExerciseRecord,Long> {
    CalendarExerciseRecord findByCalendar(Calendar calendar);
    List<CalendarExerciseRecord> findAllByCalendarIn(List<Calendar> calendar);
}
