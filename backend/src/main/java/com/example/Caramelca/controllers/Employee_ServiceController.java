package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Employee_Service;
import com.example.Caramelca.models.Service;
import com.example.Caramelca.repositories.EmployeeRepository;
import com.example.Caramelca.repositories.Employee_ServiceRepository;
import com.example.Caramelca.repositories.ServiceRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class Employee_ServiceController {

    private final Employee_ServiceRepository employeeServiceRepository;

    private final EmployeeRepository employeeRepository;

    private final ServiceRepository serviceRepository;

    public Employee_ServiceController(Employee_ServiceRepository employeeServiceRepository, EmployeeRepository employeeRepository, ServiceRepository serviceRepository) {
        this.employeeServiceRepository = employeeServiceRepository;
        this.employeeRepository = employeeRepository;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/profession")
    public String profession(Model model) {
        Iterable<Employee_Service> employeeServices = employeeServiceRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Service> services = serviceRepository.findAll();

        model.addAttribute("employeeServices", employeeServices);
        model.addAttribute("employees", employees);
        model.addAttribute("services", services);

        return "profession";
    }

    @PostMapping("/profession/add")
    public String professionAdd(@RequestParam Employee employee,
                                @RequestParam Service service) {
        Employee_Service employeeService = new Employee_Service(employee, service);
        employeeServiceRepository.save(employeeService);
        return "redirect:/profession";
    }

    @GetMapping("/profession/filter")
    public String professionFilter(@RequestParam(required = false) Employee employee,
                                   @RequestParam(required = false) Service service,
                                   Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Service> services = serviceRepository.findAll();
        Iterable<Employee_Service> employeeServices = employeeServiceRepository.findAll();
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

    @PostMapping("/profession/delete/{id}")
    public String professionDelete(@PathVariable(value = "id") Long id) {
        employeeServiceRepository.deleteById(id);
        return "redirect:/profession";
    }

}
