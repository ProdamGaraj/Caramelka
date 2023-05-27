package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.models.Client.Procedure;
import com.example.Caramelca.models.Client.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {

    Iterable<Qualification> findByEmployee(Employee employee);
    Iterable<Qualification> findByProcedure(Procedure employee);
}
