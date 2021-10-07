package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class RegisterPage extends Page{
    private UserService userService = new UserService();

    private void register(HttpServletRequest request, Map<String, Object> view) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String email = request.getParameter("email");

        try {
            userService.validateRegistration(user, password, passwordConfirm, email);
        } catch (ValidationException e) {
            view.put("login", user.getLogin());
            view.put("error", e.getMessage());
            view.put("email", user.getEmail());
            return;
        }
        userService.register(user, password, email);
        HttpSession session = request.getSession();
        session.setAttribute("curUser", user);
        throw new RedirectException("/index", "registrationDone");
    }

    private void action() {
        // No operations.
    }
}
