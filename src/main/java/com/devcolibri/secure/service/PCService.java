package com.devcolibri.secure.service;

import com.devcolibri.secure.entity.PC;
import com.devcolibri.secure.repository.PCRepository;
import com.devcolibri.secure.entity.User;
import com.devcolibri.secure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PCService {
    private final PCRepository PCRepository;
    private final UserRepository userRepository;

    public List<PC> listPCs(String title) {
        if (title != null) return PCRepository.findByTitle(title);
        return PCRepository.findAll();
    }

    public void savePC(Principal principal, PC PC) throws IOException {
        PC.setUser(getUserByPrincipal(principal));
        log.info("Saving new PC. Title: {}; Author email: {}", PC.getTitle(), PC.getUser().getEmail());
        PCRepository.save(PC);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    public PC getPCById(Long id) {
        return PCRepository.findById(id).orElse(null);
    }
}

