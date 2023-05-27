package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.services.AdminCalendarService;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
//TODO @RequestMapping("/calendar")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdminCalendarController {

    private final AdminCalendarService adminCalendarService;

    public AdminCalendarController(AdminCalendarService adminCalendarService) {
        this.adminCalendarService = adminCalendarService;
    }

    @GetMapping("/calendar")
    public String adminCalendar(Model model) {
        Iterable<Employee> employees = adminCalendarService.employeesGetAll();

        model.addAttribute("employees", employees);

        Pair<LocalDate, LocalDate> dates = adminCalendarService.getMinMaxDates();
        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "calendar";
    }

    @GetMapping("/calendar/filter")
    public String calendarFilter(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                 @RequestParam(required = false) Employee employee,
                                 Model model) {
        Iterable<Employee> employees = adminCalendarService.employeesGetAll();

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
