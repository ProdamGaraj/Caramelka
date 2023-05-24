package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Appointment;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmetnRepository extends CrudRepository<Appointment, Long> {
    Iterable<Appointment> findByDate(LocalDate date);

    Iterable<Appointment> findByEmployee(Employee employee);

    Iterable<Appointment> findByService(Procedure service);
}
