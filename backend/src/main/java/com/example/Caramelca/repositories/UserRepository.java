package com.example.Caramelca.repositories;

import com.example.Caramelca.models.Role;
import com.example.Caramelca.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Iterable<User> findByNumber(String number);
    User findByUsername(String username);

    Iterable<User> findByRoles(Role role);
}
