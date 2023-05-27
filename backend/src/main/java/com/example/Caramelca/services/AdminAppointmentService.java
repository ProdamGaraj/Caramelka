package com.example.Caramelca.services;

import com.example.Caramelca.models.Client.Appointment;
import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.models.Client.Procedure;
import com.example.Caramelca.repositories.AppointmentRepository;
import com.example.Caramelca.repositories.EmployeeRepository;
import com.example.Caramelca.repositories.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminAppointmentService {

    private final EmployeeRepository employeeRepository;

    private final ProcedureRepository procedureRepository;

    private final AppointmentRepository appointmentRepository;

    public AdminAppointmentService(EmployeeRepository employeeRepository, ProcedureRepository procedureRepository, AppointmentRepository appointmentRepository) {
        this.employeeRepository = employeeRepository;
        this.procedureRepository = procedureRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Iterable<Employee> employeesGetAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Iterable<Procedure> servicesGetAll () {
        Iterable<Procedure> services = procedureRepository.findAll();
        return  services;
    }

    public Iterable<Appointment> appointmentsGetAll() {
        Iterable<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public Iterable<Appointment> appointmentsFindByDate(LocalDate date) {
        Iterable<Appointment> appointments = appointmentRepository.findByDate(date);
        return appointments;
    }

    public Iterable<Appointment> appointmentsFiltred(Procedure service,
                                                     Employee employer,
                                                     LocalDate date) {
        Iterable<Appointment> appointments = appointmentsGetAll();

        if(date != null) {
            appointments = appointmentsFindByDate(date);
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
