package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class EnterPage extends Page {
    UserService userService = new UserService();
    public void enter(HttpServletRequest request, Map<String, Object> view){
        User curUser = userService.findByLogin(request.getParameter("login"));
        try {
            userService.validateEnter(curUser, request.getParameter("password"));
            HttpSession session = request.getSession();
            session.setAttribute("curUser", curUser);
            throw new RedirectException("/index", "enterDone");
        } catch (ValidationException e) {
            view.put("error", e.getMessage());
        }
    }
    private void action(HttpServletRequest request, Map<String, Object> view) {
    }
}
