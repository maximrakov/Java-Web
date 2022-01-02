package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.domain.Person;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.service.PersonService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EnterPage extends Page{
    private final PersonService personService;

    public EnterPage(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/enter")
    public String enterGet(Model model) {
        model.addAttribute("registerForm", new PersonCredentials());
        return "EnterPage";
    }
    @PostMapping("/enter")
    public String enterPost(@Valid @ModelAttribute("registerForm") PersonCredentials registerForm,
                            BindingResult bindingResult, HttpSession httpSession) {
        if(bindingResult.hasErrors()){
            return "EnterPage";
        }

        Person person = personService.enter(registerForm);
        if(person == null){
            setMessage(httpSession, "You account is disabled");
            return "redirect:/";
        }
        setUser(httpSession, person);
        setMessage(httpSession, "You are welcome");
        return "redirect:/";
    }
}
