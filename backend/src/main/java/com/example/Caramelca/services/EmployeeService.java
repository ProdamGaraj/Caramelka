package com.example.Caramelca.services;

import com.example.Caramelca.models.Employee;
import com.example.Caramelca.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAllEmployee() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return employee;
    }

    public void employeeSave(Employee employee) {
        employeeRepository.save(employee);
    }

    public Iterable<Employee> findFilteredEmployee(String name, String surname, String number) {
        Iterable<Employee> employees = employeeRepository.findAll();
        if (name != null && !name.isEmpty()) {
            employees = employeeRepository.findByName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            employees = employeeRepository.findBySurname(surname);
        }
        if (number != null && !number.isEmpty()) {
            employees = employeeRepository.findByNumber(number);
        }
        return employees;
    }

    public void employeeDelete(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
    }
}
