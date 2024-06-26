package PTR.PTR.repository;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarDietRecordRepository extends JpaRepository<CalendarDietRecord,Long> {
    CalendarDietRecord findByCalendar(Calendar calendar);
    List<CalendarDietRecord> findAllByCalendarIn(List<Calendar> calendar);
}
