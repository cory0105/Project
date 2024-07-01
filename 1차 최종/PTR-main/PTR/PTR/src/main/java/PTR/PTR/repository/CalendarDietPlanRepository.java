package PTR.PTR.repository;

import PTR.PTR.model.Calendar;
import PTR.PTR.model.CalendarDietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarDietPlanRepository extends JpaRepository<CalendarDietPlan,Long> {
    CalendarDietPlan findByCalendar(Calendar calendar);
    List<CalendarDietPlan> findAllByCalendarIn(List<Calendar> calendar);
}
