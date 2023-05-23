package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Long> {

    Iterable<Employee> findByName(String name);
    Iterable<Employee> findBySurname(String surname);
    Iterable<Employee> findByNumber(String number);
}