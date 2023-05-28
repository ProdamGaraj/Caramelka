package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Auth.Role;
import com.example.Caramelca.models.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Iterable<User> findByPhone(String phone);
    User findByUsername(String username);

    Iterable<User> findByRoles(Role role);
}
