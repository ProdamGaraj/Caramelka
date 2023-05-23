package com.example.Caramelca.controllers;

import com.example.Caramelca.models.Employee;
import com.example.Caramelca.services.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String employeeMain(Model model) {
        Iterable<Employee> employees = employeeService.findAllEmployee();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping("/employees/add")
    public String employeeAdd(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String patronymic,
                              @RequestParam String number) {
        Employee employee = new Employee(name, surname, patronymic, number);
        employeeService.employeeSave(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/edit")
    public String employeesEdit(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String surname,
                                 @RequestParam(required = false) String number,
                                 Model model) {
        Iterable<Employee> employees = employeeService.findFilteredEmployee(name, surname, number);
        model.addAttribute("employees", employees);
        return "employees";
    }


    @GetMapping("/employees/filter")
    public String employeeFilter(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String surname,
                                 @RequestParam(required = false) String number,
                                 Model model) {
        Iterable<Employee> employees = employeeService.findFilteredEmployee(name, surname, number);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/{id}")
    public String employee(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employees-edit";
    }

    @PostMapping("/employees/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") Long id,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String patronymic,
                               @RequestParam String number) {
        Employee employee = employeeService.findById(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setNumber(number);
        employeeService.employeeSave(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String employeeDelete(@PathVariable(value = "id") Long id) {
        employeeService.employeeDelete(id);
        return "redirect:/employees";
    }
}
