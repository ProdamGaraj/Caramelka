package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Appointment;
import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface AppointmetnRepository extends CrudRepository<Appointment, Long> {
    Iterable<Appointment> findByDate(LocalDate date);

    Iterable<Appointment> findByEmployee(Employee employee);

    Iterable<Appointment> findByService(Service service);
}
