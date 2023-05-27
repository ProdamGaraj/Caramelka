package com.example.Caramelca.services;

import com.example.Caramelca.models.Role;
import com.example.Caramelca.models.User;
import com.example.Caramelca.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    public boolean checkUserByUsernameInDB(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public void addRoleAndSaveUser(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

}
