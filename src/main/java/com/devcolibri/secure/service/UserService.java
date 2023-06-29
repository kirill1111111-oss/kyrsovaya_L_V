package com.devcolibri.secure.service;

import com.devcolibri.secure.entity.enums.Role;
import com.devcolibri.secure.entity.User;
import com.devcolibri.secure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        }

        if (userRepository.findByEmail("kiril.j2018@yandex.ru")==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.ADMIN);
            log.info("Saving new user with email {}", email);
            userRepository.save(user);
            return true;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.USER);
            log.info("Saving new user with email {}", email);
            userRepository.save(user);
            return true;
        }
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
