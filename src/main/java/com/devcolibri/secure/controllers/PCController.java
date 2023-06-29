package com.devcolibri.secure.controllers;

import com.devcolibri.secure.entity.PC;
import com.devcolibri.secure.service.PCService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.security.Principal;

@Controller//Говорит spring о том что здесь находится контроллер
@RequiredArgsConstructor//Генерирует конструктор с 1 параметром для каждого поля
public class PCController {
    private final PCService PCService;

    @GetMapping("/")//Говорит Spring MVC создать get запрос в скобках указываются url пути
    public String main(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {//RequestParam вытаксивает из формы параметр и передает его в переменную
        model.addAttribute("main", PCService.listPCs(title));//Добавляет атрибут в представление
        model.addAttribute("user", PCService.getUserByPrincipal(principal));
        return "main";//возвращает страницу
    }


    @PostMapping("/PC/add")//Говорит Spring MVC создать post запрос в скобках указываются url пути
    public String createPC(PC PC, Principal principal) throws IOException {//создание записи
        PCService.savePC(principal, PC);
        return "redirect:/";//возвращает страницу
    }

}