package com.example.Caramelca.controllers.Admin;

import com.example.Caramelca.models.Appointment;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Procedure;
import com.example.Caramelca.services.Admin.AdminAppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
//TODO @RequestMapping("/appointment")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminAppointmentController {

    private final AdminAppointmentService adminAppointmentService;

    public AdminAppointmentController(AdminAppointmentService adminAppointmentService) {
        this.adminAppointmentService = adminAppointmentService;
    }

    @GetMapping("/appointment")
    public String appointment(Model model) {
        Iterable<Appointment> appointments = adminAppointmentService.appointmentsGetAll();
        Iterable<Employee> employees = adminAppointmentService.employeesGetAll();
        Iterable<Procedure> services = adminAppointmentService.servicesGetAll();

        model.addAttribute("services", services);
        model.addAttribute("employees", employees);
        model.addAttribute("appointments", appointments);

        LocalDate minDate = adminAppointmentService.mindDate();
        LocalDate maxDate = adminAppointmentService.maxdDate();

        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDate", maxDate);

        return "appointment";
    }

    @GetMapping("/appointment/filter")
    public String appointmentFilter(@RequestParam(required = false) Procedure service,
                                    @RequestParam(required = false) Employee employer,
                                    @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                                    Model model) {
        Iterable<Employee> employees = adminAppointmentService.employeesGetAll();
        Iterable<Procedure> services = adminAppointmentService.servicesGetAll();
        Iterable<Appointment> appointments = adminAppointmentService.appointmentsFiltred(service, employer, date);

        model.addAttribute("services", services);
        model.addAttribute("employees", employees);
        model.addAttribute("appointments", appointments);

        LocalDate minDate = adminAppointmentService.mindDate();
        LocalDate maxDate = adminAppointmentService.maxdDate();
        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDate", maxDate);

        return "appointment";
    }
}