package com.example.Caramelca.services;

import com.example.Caramelca.models.*;
import com.example.Caramelca.repositories.AppointmetnRepository;
import com.example.Caramelca.repositories.CalendarRepository;
import com.example.Caramelca.repositories.ProfessionRepository;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class ProcedureService {
    private final CalendarRepository calendarRepository;

    private final ProfessionRepository employeeServiceRepository;

    private final AppointmetnRepository appointmetnRepository;

    public ProcedureService(CalendarRepository calendarRepository, ProfessionRepository employeeServiceRepository, AppointmetnRepository appointmetnRepository) {
        this.calendarRepository = calendarRepository;
        this.employeeServiceRepository = employeeServiceRepository;
        this.appointmetnRepository = appointmetnRepository;
    }

    public Set<Employee> employeeByService(Procedure service) {
        Iterable<Profession> employee = employeeServiceRepository.findByService(service);

        Set<Employee> employees = new HashSet<>();

        for (Profession employeeService : employee) {
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

    public Iterable<Calendar> filteredCalendarsByDateAndEmployees(LocalDate date,
                                                                  Employee employee,
                                                                  Set<Employee> employees) {
        if (employee != null && employees.contains(employee)) {
            return calendarRepository.findByEmployeeIn(Set.of(employee));
        }
        if (date != null) {
            return calendarRepository.findByDateAndEmployeeIn(date, employees);
        }
        return calendarRepository.findByEmployeeIn(employees);
    }

    public Appointment saveAppointment(Employee employee,
                                Procedure service,
                                User user,
                                LocalDate date,
                                LocalTime time) {
        Appointment appointment = new Appointment(employee, service, user, date, time);
        appointmetnRepository.save(appointment);
        return appointment;
    }

    public void deleteCalendarByDateAndEmployee(Employee employee,
                                                LocalDate date,
                                                LocalTime time) {
        Iterable<Calendar> calendars = calendarRepository.findByDateAndEmployeeAndTime(date, employee, time);
        for (Calendar calendar : calendars) {
            calendarRepository.delete(calendar);
        }
    }
}
