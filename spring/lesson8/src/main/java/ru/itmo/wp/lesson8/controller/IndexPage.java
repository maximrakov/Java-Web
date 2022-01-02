package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.lesson8.service.PersonService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexPage extends Page{
    private final PersonService personService;

    public IndexPage(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping({"","/"})
    public String index(Model model) {
        model.addAttribute("notes", getNotes());
        return "IndexPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        unsetUser(httpSession);
        setMessage(httpSession,"Good Bye");
        return "redirect:";
    }
    @GetMapping("/person/{id}")
    public String personInfo(@PathVariable String id, Model model){
        model.addAttribute("person", personService.findById(Integer.parseInt(id)));
        return "PersonInfoPage";
    }
}
