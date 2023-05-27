package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Procedure, Long> {
}
