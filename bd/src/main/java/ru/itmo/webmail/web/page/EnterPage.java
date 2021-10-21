package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.EventService;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class EnterPage extends Page {
    private void enter(HttpServletRequest request, Map<String, Object> view) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean isMail= false;
        for(int i = 0;i < login.length();i++){
            if(login.charAt(i) == '@'){
                isMail = true;
            }
        }
        try {
            getUserService().validateEnter(login, password, isMail);
        } catch (ValidationException e) {
            view.put("login", login);
            view.put("password", password);
            view.put("error", e.getMessage());
            return;
        }
        User user = null;
        if(isMail){
            user = getUserService().authorizeByEmail(login, password);
        }else {
            user = getUserService().authorizeByLogin(login, password);
        }
        if(!user.isConfirmed()) {
            throw new RedirectException("/index", "accountIsNotConfirmed");
        }
        getEventService().EnterUser(user.getId());

        request.getSession(true).setAttribute(USER_ID_SESSION_KEY, user.getId());

        throw new RedirectException("/index");
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
