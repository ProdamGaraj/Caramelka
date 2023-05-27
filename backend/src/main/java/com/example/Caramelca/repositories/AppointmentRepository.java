package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Client.Appointment;
import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.models.Client.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Iterable<Appointment> findByDate(LocalDate date);
}
