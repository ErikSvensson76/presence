package se.lexicon.vxo.presence.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.TreeSet;

public interface AppCalendarDayRepository extends JpaRepository<AppCalendarDay, String> {
    @Query("SELECT day FROM AppCalendarDay day WHERE day.date = :date")
    Optional<AppCalendarDay> findByDate(@Param("date") LocalDate date);

    @Query("SELECT day FROM AppCalendarDay day WHERE day.month = :month AND day.year = :year")
    TreeSet<AppCalendarDay> findDaysInMonth(@Param("month") Month month,@Param("year") int year);

    @Query("SELECT day FROM AppCalendarDay day WHERE day.yearWeek = :weekNumber AND day.year = :year")
    TreeSet<AppCalendarDay> findByWeekNumber(@Param("weekNumber") int weekNumber, @Param("year") int year);

    @Query("SELECT day FROM AppCalendarDay day WHERE day.year = :year")
    TreeSet<AppCalendarDay> findAllDaysInYear(@Param("year") int year);

    TreeSet<AppCalendarDay> findByDateBetween(LocalDate start, LocalDate end);
}
