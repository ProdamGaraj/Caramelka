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

    private final ProfessionRepository professionRepository;

    private final EmployeeRepository employeeRepository;

    private final ServiceRepository serviceRepository;

    public ProfessionController(ProfessionRepository professionRepository, EmployeeRepository employeeRepository, ServiceRepository serviceRepository) {
        this.professionRepository = professionRepository;
        this.employeeRepository = employeeRepository;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/")
    public String profession(Model model) {
        Iterable<Profession> professions = professionRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Procedure> services = serviceRepository.findAll();

        model.addAttribute("employeeServices", professions);
        model.addAttribute("employees", employees);
        model.addAttribute("services", services);

        return "profession";
    }

    @PostMapping("/add")
    public String professionAdd(@RequestParam Employee employee,
                                @RequestParam Procedure service) {
        Profession profession = new Profession(employee, service);
        professionRepository.save(profession);
        return "redirect:/profession";
    }

    @GetMapping("/filter")
    public String professionFilter(@RequestParam(required = false) Employee employee,
                                   @RequestParam(required = false) Procedure service,
                                   Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Procedure> services = serviceRepository.findAll();
        Iterable<Profession> professions = professionRepository.findAll();
        if(employee != null) {
            professions = professionRepository.findByEmployee(employee);
        }
        if(service != null) {
            professions = professionRepository.findByService(service);
        }
        model.addAttribute("employeeServices", professions);//TODO: ебануть то же на фронте но т.к. реакт похуй
        model.addAttribute("employees", employees);
        model.addAttribute("services", services);
        return "profession";
    }

    @PostMapping("/delete/{id}")
    public String professionDelete(@PathVariable(value = "id") Long id) {
        professionRepository.deleteById(id);
        return "redirect:/profession";
    }

}
