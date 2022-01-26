package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.security.Guest;
import ru.itmo.wp.lesson8.service.PersonService;
import ru.itmo.wp.lesson8.validator.PersonCredentialsRegisterValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterPage extends Page{
    private final PersonService personService;
    private final PersonCredentialsRegisterValidator personCredentialsRegisterValidator;

    public RegisterPage(PersonService personService, PersonCredentialsRegisterValidator personCredentialsRegisterValidator) {
        this.personService = personService;
        this.personCredentialsRegisterValidator = personCredentialsRegisterValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(personCredentialsRegisterValidator);
    }
    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("registerForm", new PersonCredentials());
        return "RegisterPage";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("registerForm") PersonCredentials registerForm,
                               BindingResult bindingResult, HttpSession httpSession){
        if(bindingResult.hasErrors()) {
            return "RegisterPage";
        }
        setUser(httpSession, personService.register(registerForm));
        setMessage(httpSession, "You have been succesfully registered");
        return "redirect:/";
    }
}










