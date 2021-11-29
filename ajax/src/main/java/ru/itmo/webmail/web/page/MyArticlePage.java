package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.web.annotation.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyArticlePage extends Page{
    private void action(HttpServletRequest request, Map<String, Object> view){
        List<Article> articles = getArticleService().ArticleFindByOwnerId(Integer.parseInt(String.valueOf((Long) request.getSession().getAttribute(USER_ID_SESSION_KEY))));
        view.put("articles", articles);
    }
    @Json
    private void findAllArticles(HttpServletRequest request, Map<String, Object> view) {
        view.put("articless", getArticleService().ArticleFindByOwnerId(Integer.parseInt(String.valueOf((Long) request.getSession().getAttribute(USER_ID_SESSION_KEY)))));
    }
    @Json
    private void changeVisibility(HttpServletRequest request, Map<String, Object> view) {
        if(getArticleService().findOwnerById((int) Long.parseLong(request.getParameter("articleId"))) == (Long) request.getSession().getAttribute(USER_ID_SESSION_KEY)) {
            getArticleService().changeVisibility((int) Long.parseLong(request.getParameter("articleId")));
        }
    }
}
