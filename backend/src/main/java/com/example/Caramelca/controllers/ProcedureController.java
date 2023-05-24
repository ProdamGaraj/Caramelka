package com.example.Caramelca.controllers;

import com.example.Caramelca.models.*;
import com.example.Caramelca.services.ProcedureService;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Controller
@RequestMapping("/procedure")
public class ProcedureController {

    private final ProcedureService procedureService;


    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/{id}")
    public String service(@PathVariable(value = "id") Procedure service,
                          Model model) {
        Set<Employee> employees = procedureService.employeeByService(service);
        Iterable<Calendar> calendars = procedureService.calendarsByEmployees(employees);
        Pair<LocalDate, LocalDate> dates = procedureService.getMinMaxDates();

        model.addAttribute("calendars", calendars);
        model.addAttribute("employees", employees);

        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "procedure";
    }

    @GetMapping("/{id}/filter")
    public String serviceFilter(@PathVariable(value = "id") Procedure service,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                @RequestParam(required = false) Employee employer,
                                Model model) {

        Set<Employee> employees = procedureService.employeeByService(service);
        Iterable<Calendar> calendars = procedureService.filteredCalendarsByDateAndEmployees(date, employer, employees);
        Pair<LocalDate, LocalDate> dates = procedureService.getMinMaxDates();

        model.addAttribute("employees", employees);
        model.addAttribute("calendars", calendars);

        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "procedure";
    }

    @PostMapping("/{id}/appointment")
    public String appointment(@PathVariable(value = "id") Procedure service,
                              @RequestParam String date,
                              @RequestParam String time,
                              @RequestParam Employee employer,
                              @AuthenticationPrincipal User user) {

        Appointment appointment = procedureService.saveAppointment(
                employer, service, user,
                LocalDate.parse(date),
                LocalTime.parse(time));

        procedureService.deleteCalendarByDateAndEmployee(employer,
                appointment.getDate(),
                appointment.getTime());

        return "redirect:/index";
    }

}
