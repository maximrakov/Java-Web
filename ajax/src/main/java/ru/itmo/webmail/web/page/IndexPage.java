package ru.itmo.webmail.web.page;

import ru.itmo.webmail.web.annotation.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void accountIsNotConfirmed(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "Your account is not confirmed");
    }

    @Json
    private void findAllArticles(HttpServletRequest request, Map<String, Object> view){
        view.put("articles", getArticleService().findAll());
    }
}
