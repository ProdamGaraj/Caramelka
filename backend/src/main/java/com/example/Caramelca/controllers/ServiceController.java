package com.example.Caramelca.controllers;

import com.example.Caramelca.models.*;
import com.example.Caramelca.services.ServiceService;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Controller
public class ServiceController {

    private final ServiceService serviceService;


    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/service/{id}")
    public String service(@PathVariable(value = "id") Service service,
                          Model model) {
        Set<Employee> employees = serviceService.employeeByService(service);
        Iterable<Calendar> calendars = serviceService.calendarsByEmployees(employees);
        Pair<LocalDate, LocalDate> dates = serviceService.getMinMaxDates();

        model.addAttribute("calendars", calendars);
        model.addAttribute("employees", employees);

        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "service";
    }

    @GetMapping("/service/{id}/filter")
    public String serviceFilter(@PathVariable(value = "id") Service service,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                @RequestParam(required = false) Employee employer,
                                Model model) {

        Set<Employee> employees = serviceService.employeeByService(service);
        Iterable<Calendar> calendars = serviceService.filteredCalendarsByDateAndEmployees(date, employer, employees);
        Pair<LocalDate, LocalDate> dates = serviceService.getMinMaxDates();

        model.addAttribute("employees", employees);
        model.addAttribute("calendars", calendars);

        model.addAttribute("minDate", dates.getFirst());
        model.addAttribute("maxDate", dates.getSecond());

        return "service";
    }

    @PostMapping("/service/{id}/appointment")
    public String appointment(@PathVariable(value = "id") Service service,
                              @RequestParam String date,
                              @RequestParam String time,
                              @RequestParam Employee employer,
                              @AuthenticationPrincipal User user) {

        Appointment appointment = serviceService.saveAppointment(
                employer, service, user,
                LocalDate.parse(date),
                LocalTime.parse(time));

        serviceService.deleteCalendarByDateAndEmployee(employer,
                appointment.getDate(),
                appointment.getTime());

        return "redirect:/index";
    }

}
