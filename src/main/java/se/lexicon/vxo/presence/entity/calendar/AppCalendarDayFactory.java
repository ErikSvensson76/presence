package se.lexicon.vxo.presence.entity.calendar;

import java.time.LocalDate;

public abstract class AppCalendarDayFactory {
    protected AppCalendarDay createAppCalendarDay(LocalDate date){
        if(date == null) throw new NullPointerException("Invalid parameter: date was null");
        return new AppCalendarDay(date);
    }
}
