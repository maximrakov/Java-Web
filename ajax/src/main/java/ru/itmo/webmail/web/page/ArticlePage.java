package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends Page{
    private void action(HttpServletRequest request, Map<String, Object> view){
    }
    private void send(HttpServletRequest request, Map<String, Object> view){
        ArticleService articleService = getArticleService();
        String text = request.getParameter("text");
        long sourceId = (long) request.getSession().getAttribute("userId");
        String title = request.getParameter("title");
        articleService.addArticle(sourceId, title, text);
    }
}
