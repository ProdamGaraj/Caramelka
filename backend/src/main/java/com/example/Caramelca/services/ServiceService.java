package com.example.Caramelca.services;

import com.example.Caramelca.models.Calendar;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Employee_Service;
import com.example.Caramelca.models.Service;
import com.example.Caramelca.repositories.AppointmetnRepository;
import com.example.Caramelca.repositories.CalendarRepository;
import com.example.Caramelca.repositories.Employee_ServiceRepository;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class ServiceService {
    private final CalendarRepository calendarRepository;

    private final Employee_ServiceRepository employeeServiceRepository;

    private final AppointmetnRepository appointmetnRepository;

    public ServiceService(CalendarRepository calendarRepository, Employee_ServiceRepository employeeServiceRepository, AppointmetnRepository appointmetnRepository) {
        this.calendarRepository = calendarRepository;
        this.employeeServiceRepository = employeeServiceRepository;
        this.appointmetnRepository = appointmetnRepository;
    }

    public Set<Employee> employeeByService(Service service) {
        Iterable<Employee_Service> employee = employeeServiceRepository.findByService(service);

        Set<Employee> employees = new HashSet<>();

        for (Employee_Service employeeService : employee) {
            employees.add(employeeService.getEmployee());
        }

        return employees;
    }

    public Iterable<Calendar> calendarsByEmployees(Set<Employee> employees) {
        return calendarRepository.findByEmployeeIn(employees);
    }

    public Pair<LocalDate, LocalDate> getMinMaxDates() {
        LocalDate minDate = LocalDate.now().plusDays(1);
        LocalDate maxDate = minDate.plusMonths(1);
        return Pair.of(minDate, maxDate);
    }

}
