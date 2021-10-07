package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class Page {
    private UserService userService = new UserService();
    private NewsService newsService = new NewsService();
    void before(HttpServletRequest request, Map<String, Object> view){
        view.put("curNews", newsService.finaAll());
        HttpSession session = request.getSession();
        if(session.getAttribute("curUser") != null){
            User curUser = (User) session.getAttribute("curUser");
            view.put("userName", curUser.getLogin());
        }
        view.put("userCount", userService.findCount());
    }
    void after(HttpServletRequest request, Map<String, Object> view){

    }
}
