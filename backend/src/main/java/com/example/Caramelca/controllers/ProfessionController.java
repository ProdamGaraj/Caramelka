package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Profession;
import com.example.Caramelca.models.Procedure;
import com.example.Caramelca.repositories.EmployeeRepository;
import com.example.Caramelca.repositories.ProfessionRepository;
import com.example.Caramelca.repositories.ServiceRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profession")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProfessionController {

    private final ProfessionRepository employeeServiceRepository;

    private final EmployeeRepository employeeRepository;

    private final ServiceRepository serviceRepository;

    public ProfessionController(ProfessionRepository employeeServiceRepository, EmployeeRepository employeeRepository, ServiceRepository serviceRepository) {
        this.employeeServiceRepository = employeeServiceRepository;
        this.employeeRepository = employeeRepository;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/")
    public String profession(Model model) {
        Iterable<Profession> employeeServices = employeeServiceRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Procedure> services = serviceRepository.findAll();

        model.addAttribute("employeeServices", employeeServices);
        model.addAttribute("employees", employees);
        model.addAttribute("services", services);

        return "profession";
    }

    @PostMapping("/add")
    public String professionAdd(@RequestParam Employee employee,
                                @RequestParam Procedure service) {
        Profession employeeService = new Profession(employee, service);
        employeeServiceRepository.save(employeeService);
        return "redirect:/profession";
    }

    @GetMapping("/filter")
    public String professionFilter(@RequestParam(required = false) Employee employee,
                                   @RequestParam(required = false) Procedure service,
                                   Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Procedure> services = serviceRepository.findAll();
        Iterable<Profession> employeeServices = employeeServiceRepository.findAll();
        if(employee != null) {
            employeeServices = employeeServiceRepository.findByEmployee(employee);
        }
        if(service != null) {
            employeeServices = employeeServiceRepository.findByService(service);
        }
        model.addAttribute("employeeServices", employeeServices);
        model.addAttribute("employees", employees);
        model.addAttribute("services", services);
        return "profession";
    }

    @PostMapping("/delete/{id}")
    public String professionDelete(@PathVariable(value = "id") Long id) {
        employeeServiceRepository.deleteById(id);
        return "redirect:/profession";
    }

}
