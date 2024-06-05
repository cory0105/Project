package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="calendar_diet_plan")
public class CalendarDietPlan {
    @Id
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;
    @Column
    private int carbohydrate;
    @Column
    private int protein;
    @Column
    private int fat;
    @Column
    private String detail;
}
