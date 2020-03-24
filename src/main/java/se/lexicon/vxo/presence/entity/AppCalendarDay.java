package se.lexicon.vxo.presence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

@Entity
public class AppCalendarDay implements Comparable<AppCalendarDay>{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String dayId;
    @Column(unique = true)
    private LocalDate date;
    private int year;
    private Month month;
    private int yearWeek;
    private DayOfWeek weekDay;


    public AppCalendarDay(LocalDate date) {
        this.dayId = null;
        this.date = date;
        year = date.getYear();
        weekDay = date.getDayOfWeek();
        month = date.getMonth();
        TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        yearWeek = date.get(weekOfYear);
    }

    public AppCalendarDay(){}

    public String getDayId() {
        return dayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWeekDay() {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public String getMonth() {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public int getYearWeek() {
        return yearWeek;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppCalendarDay that = (AppCalendarDay) o;
        return Objects.equals(dayId, that.dayId) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayId, date);
    }

    @Override
    public int compareTo(AppCalendarDay o) {
        return date.compareTo(o.getDate());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppCalendarDay{");
        sb.append("dayId='").append(dayId).append('\'');
        sb.append(", date=").append(date);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", yearWeek=").append(yearWeek);
        sb.append(", weekDay=").append(weekDay);
        sb.append('}');
        return sb.toString();
    }
}
