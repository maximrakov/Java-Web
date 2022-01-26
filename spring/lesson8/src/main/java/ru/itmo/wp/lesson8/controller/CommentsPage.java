package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.domain.Comment;
import ru.itmo.wp.lesson8.service.PersonService;
import ru.itmo.wp.lesson8.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.LongSummaryStatistics;

@Controller
public class CommentsPage extends Page{
    private final PersonService personService;
    private final PostService postService;

    public CommentsPage(PersonService personService, PostService postService) {
        this.personService = personService;
        this.postService = postService;
    }

    @PostMapping("/makeComment")
    public String makeComment(@Valid @ModelAttribute("comment") Comment comment,
                            @ModelAttribute("postNum") String postNum,
                            BindingResult bindingResult, HttpSession httpSession) {
        personService.writeComment(getUser(httpSession), comment, postService.findById(Long.parseLong(postNum)));
        postService.writeComment(postService.findById(Long.parseLong(postNum)), comment);
        return "redirect:/post/" + postNum;
    }
}
