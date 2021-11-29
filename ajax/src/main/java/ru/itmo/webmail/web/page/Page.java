package ru.itmo.webmail.web.page;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    static final String USER_ID_SESSION_KEY = "userId";

    private UserService userService = new UserService();
    private EventService eventService = new EventService();
    private EmailConfirmationService emailConfirmationService = new EmailConfirmationService();
    private TalkService talkService =  new TalkService();
    private ArticleService articleService = new ArticleService();
    private User user;

    public ArticleService getArticleService() {
        return articleService;
    }

    UserService getUserService() {
        return userService;
    }
    TalkService getTalkService(){
        return talkService;
    }
    EmailConfirmationService getEmailConfirmationService(){
        return emailConfirmationService;
    }
    public EventService getEventService() {
        return eventService;
    }

    public User getUser() {
        return user;
    }

    public void before(HttpServletRequest request, Map<String, Object> view) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID_SESSION_KEY);
        if (userId != null) {
            user = userService.find(userId);
            view.put("user", user);
        }
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
