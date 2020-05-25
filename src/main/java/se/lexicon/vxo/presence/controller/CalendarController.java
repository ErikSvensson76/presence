package se.lexicon.vxo.presence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;
import se.lexicon.vxo.presence.service.calendar.CalendarService;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class CalendarController {

    private CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping(value = {"/calendar"})
    public String calendarPage(Model model){

        model.addAttribute("thisMonth", LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        model.addAttribute("calendarDaysByWeek",
                Arrays.stream(calendarService.getEvenMonthInYear(LocalDate.now().getMonth(), LocalDate.now().getYear()))
                    .collect( Collectors.groupingBy(AppCalendarDay::getYearWeek) )
        );

        return "calendar/calendar";
    }
}
