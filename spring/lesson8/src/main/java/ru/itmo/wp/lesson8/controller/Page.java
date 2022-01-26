package ru.itmo.wp.lesson8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.wp.lesson8.domain.Notice;
import ru.itmo.wp.lesson8.domain.Person;
import ru.itmo.wp.lesson8.service.NoticeService;
import ru.itmo.wp.lesson8.service.PersonService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";
    private static final String MESSAGE_SESSION_KEY = "message";

//    public Page(Model model) {
//        model.addAttribute(noticeService.findAll());
//    }

    @Autowired
    private PersonService personService;

    @Autowired
    private NoticeService noticeService;

    void setUser(HttpSession httpSession, Person person){
        httpSession.setAttribute(USER_ID_SESSION_KEY, person.getId());
    }

    void unsetUser(HttpSession httpSession){
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    @ModelAttribute("person")
    public Person getUser(HttpSession httpSession) {
        Object userId = httpSession.getAttribute(USER_ID_SESSION_KEY);
        if(userId == null){
            return null;
        } else{
            return personService.findById((long)userId);
        }
    }
    @ModelAttribute("notes")
    List<Notice> getNotes() {
        return noticeService.findAll();
    }

    @SuppressWarnings("SameParameterValue")
    public void setMessage(HttpSession httpSession, String message){
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
    }

    @ModelAttribute("message")
    String getMessage(HttpSession httpSession) {
        String message = (String) httpSession.getAttribute(MESSAGE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_SESSION_KEY);
        return message;
    }
}
