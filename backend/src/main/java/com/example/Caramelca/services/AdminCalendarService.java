package com.example.Caramelca.services;

import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.repositories.QualificationRepository;
import com.example.Caramelca.repositories.EmployeeRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminCalendarService {
    private final EmployeeRepository employeeRepository;


    private final QualificationRepository qualificationRepository;

    public AdminCalendarService(EmployeeRepository employeeRepository, QualificationRepository qualificationRepository) {
        this.employeeRepository = employeeRepository;
        this.qualificationRepository = qualificationRepository;
    }

    public Iterable<Employee> employeesGetAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public void calendarDelete(Long id) {
        qualificationRepository.deleteById(id);
    }

    public Pair<LocalDate, LocalDate> getMinMaxDates() {
        LocalDate minDate = LocalDate.now().plusDays(1);
        LocalDate maxDate = minDate.plusMonths(1);
        return Pair.of(maxDate, maxDate);
    }
}
