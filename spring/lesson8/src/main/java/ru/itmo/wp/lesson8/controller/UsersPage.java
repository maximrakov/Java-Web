package ru.itmo.wp.lesson8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.service.PersonService;

import javax.validation.Valid;

@Controller
public class UsersPage extends Page{

    private final PersonService persoService;

    public UsersPage(PersonService persoService) {
        this.persoService = persoService;
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("persons", persoService.findAll());
        model.addAttribute("personId",0);
        return "UsersPage";
    }
    @PostMapping("/changeAccess")
    public String changeAccess(@ModelAttribute("personId") int personId) {
        persoService.changeAccess(personId);
        return "redirect:/users";
    }
}
