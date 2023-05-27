package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Employee;
import com.example.Caramelca.models.Profession;
import com.example.Caramelca.models.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    Iterable<Profession> findByService(Procedure service);

    Iterable<Profession> findByEmployee(Employee employee);

    Iterable<Profession> deleteByEmployee(Long id);
}
