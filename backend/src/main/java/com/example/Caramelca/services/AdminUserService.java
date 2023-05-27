package com.example.Caramelca.services;

import com.example.Caramelca.models.Admin.Role;
import com.example.Caramelca.models.Client.User;
import com.example.Caramelca.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    private final UserRepository userRepository;

    public AdminUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> usersGetAll() {
        Iterable<User> users = userRepository.findByRoles(Role.USER);
        return users;
    }

    public User userById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }

    public Iterable<User> userFiltred(User user) {
        Iterable<User> users = userRepository.findAll();

        if (user != null) {
            users = userRepository.findByPhone(user.getPhone());
        }

        return users;
    }
}
