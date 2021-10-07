package ru.itmo.webmail.web.page;

import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogoutPage extends Page{
    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        session.removeAttribute("curUser");
        throw new RedirectException("/index", "logoutDone");
    }
}
