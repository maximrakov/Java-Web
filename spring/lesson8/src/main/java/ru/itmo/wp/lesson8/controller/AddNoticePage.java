package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.form.NoticeCredentials;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.service.NoticeService;

import javax.validation.Valid;

@Controller
public class AddNoticePage extends Page{
    private final NoticeService noticeService;

    public AddNoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("/AddNotice")
    public String pushNotice(@Valid @ModelAttribute("noticeForm") NoticeCredentials noticeCredentials){
        noticeService.makeNotice(noticeCredentials);
        return "redirect:/";
    }
    @GetMapping("/AddNotice")
    public String AddNotice(Model model) {
        model.addAttribute("noticeForm", new NoticeCredentials());
        return "AddNoticePage";
    }
}