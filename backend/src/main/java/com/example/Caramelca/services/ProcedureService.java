package com.example.Caramelca.services;

import com.example.Caramelca.models.Auth.User;
import com.example.Caramelca.models.Client.*;
import com.example.Caramelca.repositories.AppointmentRepository;
import com.example.Caramelca.repositories.EmployeeRepository;
import com.example.Caramelca.repositories.QualificationRepository;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalTime;

@org.springframework.stereotype.Service
public class ProcedureService {
    private final QualificationRepository qualificationRepository;

    private final AppointmentRepository appointmentRepository;

    private final EmployeeRepository employeeRepository;

    public ProcedureService(QualificationRepository qualificationRepository,
                            AppointmentRepository appointmentRepository,
                            EmployeeRepository employeeRepository) {
        this.qualificationRepository = qualificationRepository;
        this.appointmentRepository = appointmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Pair<LocalDate, LocalDate> getMinMaxDates() {
        LocalDate minDate = LocalDate.now().plusDays(1);
        LocalDate maxDate = minDate.plusMonths(1);
        return Pair.of(minDate, maxDate);
    }

    public Appointment saveAppointment(Employee employee,
                                       Procedure procedure,
                                       User user,
                                       LocalDate date,
                                       LocalTime time) {
        Appointment appointment = new Appointment(new Qualification(employee, procedure), user, date, time);
        appointmentRepository.save(appointment);
        return appointment;
    }
}
