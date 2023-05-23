package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Employee_Service;
import com.example.Caramelca.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee_ServiceRepository extends CrudRepository<Employee_Service, Long> {
    Iterable<Employee_Service> findByService(Service service);

    Iterable<Employee_Service> findByEmployee(Employee employee);

    Iterable<Employee_Service> deleteByEmployee(Long id);
}
