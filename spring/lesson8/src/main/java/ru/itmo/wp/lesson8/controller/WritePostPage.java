package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.domain.Post;
import ru.itmo.wp.lesson8.domain.Role;
import ru.itmo.wp.lesson8.domain.Tag;
import ru.itmo.wp.lesson8.security.AnyRole;
import ru.itmo.wp.lesson8.service.PersonService;
import ru.itmo.wp.lesson8.service.PostService;
import ru.itmo.wp.lesson8.service.TagService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class WritePostPage extends Page {
    private final PersonService personService;
    private final TagService tagService;
    private final PostService postService;

    public WritePostPage(PersonService personService, TagService tagService, PostService postService) {
        this.personService = personService;
        this.tagService = tagService;
        this.postService = postService;
    }

    @AnyRole(Role.Name.WRITER)
    @GetMapping("/writePost")
    public String writePostGet(Model model) {
        model.addAttribute("post", new Post());
        return "WritePostPage";
    }

    @AnyRole(Role.Name.WRITER)
    @PostMapping("/writePost")
    public String registerPost(@Valid @ModelAttribute("post") Post post,
                               BindingResult bindingResult, HttpSession httpSession){
        if(bindingResult.hasErrors()) {
            return "WritePostPage";
        }

        personService.writePost(getUser(httpSession), post);
        StringBuilder currentTag = new StringBuilder();
        for(int i = 0; i < post.getTagsAsString().length(); i++) {
            if(post.getTagsAsString().charAt(i) == ' ') {
                Tag nTag = tagService.MakeTag(postService.findByTitle(post.getTitle()), currentTag.toString());
                postService.addTag(postService.findByTitle(post.getTitle()), nTag);
                currentTag = new StringBuilder();
            } else {
                currentTag.append(post.getTagsAsString().charAt(i));
            }
        }
        if(currentTag.length() != 0) {
            Tag nTag = tagService.MakeTag(postService.findByTitle(post.getTitle()), currentTag.toString());
            postService.addTag(postService.findByTitle(post.getTitle()), nTag);
        }
        setMessage(httpSession, "Post has been published");

        return "redirect:/";
    }
}










