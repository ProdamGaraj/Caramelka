package com.example.Caramelca.services;

import com.example.Caramelca.models.Auth.Role;
import com.example.Caramelca.models.Auth.User;
import com.example.Caramelca.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserByUsernameInDB(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public void addRoleAndSaveUser(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

}
