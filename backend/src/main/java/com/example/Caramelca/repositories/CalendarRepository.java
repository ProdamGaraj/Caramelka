package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Calendar;
import com.example.Caramelca.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Long> {

    Iterable<Calendar> findByDateAndEmployeeIn(LocalDate date, Set<Employee> employees);
    Iterable<Calendar> findByEmployee(Employee employee);
    Iterable<Calendar> findByDate(LocalDate date);
    Iterable<Calendar> findByEmployeeIn(Set<Employee> employees);
    Iterable<Calendar> findByDateAndEmployeeAndTime(LocalDate date, Employee employee, LocalTime time);
}
