package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Role;
import com.example.Caramelca.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Iterable<User> findByNumber(String number);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByNumber(String number);
}
