package com.example.Caramelca.controllers;

import com.example.Caramelca.models.*;
import com.example.Caramelca.repositories.AppointmetnRepository;
import com.example.Caramelca.repositories.CalendarRepository;
import com.example.Caramelca.repositories.Employee_ServiceRepository;
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

    private final CalendarRepository calendarRepository;

    private final Employee_ServiceRepository employeeServiceRepository;

    private final AppointmetnRepository appointmetnRepository;

    private final ServiceService serviceService;


    public ServiceController(ServiceService serviceService, CalendarRepository calendarRepository, Employee_ServiceRepository employeeServiceRepository, AppointmetnRepository appointmetnRepository) {
        this.calendarRepository = calendarRepository;
        this.employeeServiceRepository = employeeServiceRepository;
        this.appointmetnRepository = appointmetnRepository;
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
        Iterable<Calendar> calendars = serviceService.calendarsByEmployees(employees);
        Pair<LocalDate, LocalDate> dates = serviceService.getMinMaxDates();

        if(date != null) {
            calendars = calendarRepository.findByDateAndEmployeeIn(date, employees);
        }
        if(employer != null && employees.contains(employer)) {
            employees.clear();
            employees.add(employer);
            calendars = calendarRepository.findByEmployeeIn(employees);
        }

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

        LocalDate datE = LocalDate.parse(date);
        LocalTime timE = LocalTime.parse(time);

        Appointment appointment = new Appointment(employer, service, user, datE, timE);
        appointmetnRepository.save(appointment);

        Iterable<Calendar> calendars = calendarRepository.findByDateAndEmployeeAndTime(datE, employer, timE);
        for (Calendar calendar : calendars) {
            calendarRepository.delete(calendar);
        }

        return "redirect:/index";
    }

}
