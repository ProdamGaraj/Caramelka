package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Appointment;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Service;
import com.example.Caramelca.repositories.AppointmetnRepository;
import com.example.Caramelca.repositories.EmployeeRepository;
import com.example.Caramelca.repositories.ServiceRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminAppointmentController {

    private final EmployeeRepository employeeRepository;

    private final ServiceRepository serviceRepository;

    private final AppointmetnRepository appointmetnRepository;

    public AdminAppointmentController(EmployeeRepository employeeRepository, ServiceRepository serviceRepository, AppointmetnRepository appointmetnRepository) {
        this.employeeRepository = employeeRepository;
        this.serviceRepository = serviceRepository;
        this.appointmetnRepository = appointmetnRepository;
    }

    @GetMapping("/appointment")
    public String appointment(Model model) {
        Iterable<Appointment> appointments = appointmetnRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Service> services = serviceRepository.findAll();

        model.addAttribute("services", services);
        model.addAttribute("employees", employees);
        model.addAttribute("appointments", appointments);

        LocalDate minDate = LocalDate.now().plusDays(1);
        LocalDate maxDate = minDate.plusMonths(1);
        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDate", maxDate);

        return "appointment";
    }

    @GetMapping("/appointment/filter")
    public String appointmentFilter(@RequestParam(required = false) Service service,
                                    @RequestParam(required = false) Employee employer,
                                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                    Model model) {
        Iterable<Appointment> appointments = appointmetnRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Service> services = serviceRepository.findAll();

        if(date != null) {
            appointments = appointmetnRepository.findByDate(date);
        }
        if(service != null) {
            appointments = appointmetnRepository.findByService(service);
        }
        if(employer != null) {
            appointments = appointmetnRepository.findByEmployee(employer);
        }

        model.addAttribute("services", services);
        model.addAttribute("employees", employees);
        model.addAttribute("appointments", appointments);

        LocalDate minDate = LocalDate.now().plusDays(1);
        LocalDate maxDate = minDate.plusMonths(1);
        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDate", maxDate);

        return "appointment";
    }
}
