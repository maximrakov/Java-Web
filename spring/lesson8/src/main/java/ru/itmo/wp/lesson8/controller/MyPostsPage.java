package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.wp.lesson8.domain.Role;
import ru.itmo.wp.lesson8.security.AnyRole;

@Controller
public class MyPostsPage extends Page{
    @AnyRole(Role.Name.WRITER)
    @GetMapping("/MyPosts")
    public String MyPosts() {
        return "MyPostsPage";
    }
}
