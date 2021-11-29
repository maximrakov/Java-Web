package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;
import ru.itmo.webmail.model.service.EventService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LogoutPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        getEventService().ExitUser((Long) request.getSession().getAttribute(USER_ID_SESSION_KEY));
        request.getSession().removeAttribute(USER_ID_SESSION_KEY);
        throw new RedirectException("/index");
    }
}
