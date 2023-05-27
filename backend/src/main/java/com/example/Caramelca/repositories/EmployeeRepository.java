package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Client.Employee;
import com.example.Caramelca.models.Client.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Iterable<Employee> findByName(String name);
    Iterable<Employee> findBySurname(String surname);
    Iterable<Employee> findByNumber(String number);
}