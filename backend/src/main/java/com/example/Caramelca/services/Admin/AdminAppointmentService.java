package com.example.Caramelca.services.Admin;

import com.example.Caramelca.models.Appointment;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Procedure;
import com.example.Caramelca.repositories.AppointmetnRepository;
import com.example.Caramelca.repositories.EmployeeRepository;
import com.example.Caramelca.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminAppointmentService {

    private final EmployeeRepository employeeRepository;

    private final ServiceRepository serviceRepository;

    private final AppointmetnRepository appointmetnRepository;

    public AdminAppointmentService(EmployeeRepository employeeRepository, ServiceRepository serviceRepository, AppointmetnRepository appointmetnRepository) {
        this.employeeRepository = employeeRepository;
        this.serviceRepository = serviceRepository;
        this.appointmetnRepository = appointmetnRepository;
    }

    public Iterable<Employee> employeesGetAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Iterable<Procedure> servicesGetAll () {
        Iterable<Procedure> services = serviceRepository.findAll();
        return  services;
    }

    public Iterable<Appointment> appointmentsGetAll() {
        Iterable<Appointment> appointments = appointmetnRepository.findAll();
        return appointments;
    }

    public Iterable<Appointment> appointmentsFindByDate(LocalDate date) {
        Iterable<Appointment> appointments = appointmetnRepository.findByDate(date);
        return appointments;
    }

    public Iterable<Appointment> appointmentsFindByService(Procedure service) {
        Iterable<Appointment> appointments = appointmetnRepository.findByService(service);
        return appointments;
    }

    public Iterable<Appointment> appointmentsFindByEmployee(Employee employee) {
        Iterable<Appointment> appointments = appointmetnRepository.findByEmployee(employee);
        return appointments;
    }

    public Iterable<Appointment> appointmentsFiltred(Procedure service,
                                                     Employee employer,
                                                     LocalDate date) {
        Iterable<Appointment> appointments = appointmentsGetAll();

        if(date != null) {
            appointments = appointmentsFindByDate(date);
        }
        if(service != null) {
            appointments = appointmentsFindByService(service);
        }
        if(employer != null) {
            appointments = appointmentsFindByEmployee(employer);
        }
        return appointments;
    }

    public LocalDate mindDate() {
        LocalDate minDate = LocalDate.now().plusDays(1);
        return minDate;
    }

    public LocalDate maxdDate() {
        LocalDate minDate = mindDate();
        LocalDate maxDate = minDate.plusMonths(1);
        return maxDate;
    }
}
