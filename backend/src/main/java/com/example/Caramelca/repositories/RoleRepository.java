package com.example.Caramelca.repositories;

import com.example.Caramelca.models.ERole;
import com.example.Caramelca.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}