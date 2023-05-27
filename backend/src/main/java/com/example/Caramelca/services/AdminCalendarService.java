package com.example.Caramelca.services;

import com.example.Caramelca.models.Calendar;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.repositories.CalendarRepository;
import com.example.Caramelca.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AdminCalendarService {
    private final EmployeeRepository employeeRepository;


    private final CalendarRepository calendarRepository;

    public Iterable<Employee> employeesGetAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Iterable<Calendar> calendarGetAll() {
        Iterable<Calendar> calendars = calendarRepository.findAll();
        return calendars;
    }

    public Iterable<Calendar> calendarFiltred(LocalDate date, Employee employee) {
        Iterable<Calendar> calendar = calendarRepository.findAll();
        if(date != null) {
            calendar = calendarRepository.findByDate(date);
        }
        if(employee != null) {
            calendar = calendarRepository.findByEmployee(employee);
        }

        return calendar;
    }

    public void calendarDelete(Long id) {
        calendarRepository.deleteById(id);
    }

    public void calendarSave(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    public Pair<LocalDate, LocalDate> getMinMaxDates() {
        LocalDate minDate = LocalDate.now().plusDays(1);
        LocalDate maxDate = minDate.plusMonths(1);
        return Pair.of(maxDate, maxDate);
    }
}
