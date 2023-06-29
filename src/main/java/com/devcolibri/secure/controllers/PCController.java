package com.devcolibri.secure.controllers;

import com.devcolibri.secure.entity.PC;
import com.devcolibri.secure.service.PCService;
import com.devcolibri.secure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PCController {
    private final PCService PCService;

    @GetMapping("/")
    public String main(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("main", PCService.listPCs(title));
        model.addAttribute("user", PCService.getUserByPrincipal(principal));
        return "main";
    }

    @GetMapping("/PC/{id}")
    public String PCInfo(@PathVariable Long id, Model model) {
        PC PC = PCService.getPCById(id);
        model.addAttribute("PC", PC);
        return "PC-info";
    }

    @PostMapping("/PC/add")
    public String createPC(PC PC, Principal principal) throws IOException {
        PCService.savePC(principal, PC);
        return "redirect:/";
    }

}