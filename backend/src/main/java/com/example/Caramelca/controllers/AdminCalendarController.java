package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Calendar;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.services.AdminCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminCalendarController {

    private final AdminCalendarService adminCalendarService;

    @GetMapping("/calendar")
    public String adminCalendar(Model model) {
        Iterable<Calendar> calendars = adminCalendarService.calendarGetAll();
        Iterable<Employee> employees = adminCalendarService.employeesGetAll();

        model.addAttribute("calendar", calendars);
        model.addAttribute("employees", employees);

        Pair<LocalDate, LocalDate> dates = adminCalendarService.getMinMaxDates();
        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "calendar";
    }

    @PostMapping("/calendar/add")
    public String calendarAdd(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                              @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time,
                              @RequestParam Employee employee) {
        Calendar calendar = new Calendar(employee, date, time);
        adminCalendarService.calendarSave(calendar);
        return "redirect:/calendar";
    }

    @GetMapping("/calendar/filter")
    public String calendarFilter(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                 @RequestParam(required = false) Employee employee,
                                 Model model) {
        Iterable<Employee> employees = adminCalendarService.employeesGetAll();
        Iterable<Calendar> calendar = adminCalendarService.calendarFiltred(date, employee);

        model.addAttribute("calendar", calendar);
        model.addAttribute("employees", employees);

        Pair<LocalDate, LocalDate> dates = adminCalendarService.getMinMaxDates();
        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "calendar";
    }

    @PostMapping("/calendar/delete/{id}")
    public String calendarDelete(@PathVariable(value = "id") Long id) {
        adminCalendarService.calendarDelete(id);
        return "redirect:/calendar";
    }
}
