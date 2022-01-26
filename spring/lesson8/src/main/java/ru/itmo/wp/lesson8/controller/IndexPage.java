package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.lesson8.domain.Comment;
import ru.itmo.wp.lesson8.security.Guest;
import ru.itmo.wp.lesson8.service.PersonService;
import ru.itmo.wp.lesson8.service.PostService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexPage extends Page{
    private final PersonService personService;
    private final PostService postService;
    public IndexPage(PersonService personService, PostService postService) {
        this.personService = personService;
        this.postService = postService;
    }

//    @Guest
    @GetMapping({"","/"})
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("notes", getNotes());
        return "IndexPage";
    }

    @Guest
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        setMessage(httpSession,"Good Bye");
        return "redirect:";
    }

    @GetMapping("/person/{id}")
    public String personInfo(@PathVariable String id, Model model) {
        model.addAttribute("person", personService.findById(Integer.parseInt(id)));
        return "PersonInfoPage";
    }

    @GetMapping("/post/{id}")
    public String postInfo(@PathVariable String id, Model model){
        model.addAttribute("comment", new Comment());
        model.addAttribute("post", postService.findById(Long.parseLong(id)));
        return "PostInfoPage";
    }
}
