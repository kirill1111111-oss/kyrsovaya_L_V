package com.devcolibri.secure.controllers;

import com.devcolibri.secure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/Users")
    public String Users(Model model) {
        model.addAttribute("users", userService.list());
        return "Users";
    }
}
