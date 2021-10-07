package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MakeNoticePage extends Page{
    NewsService newsService = new NewsService();
    void makeNotice(HttpServletRequest request, Map<String, Object> view){
        String news = request.getParameter("message");
        User curUser = (User) request.getSession().getAttribute("curUser");
        News curNews = new News(curUser.getId(), news);
        newsService.makeNews(curNews);
        throw new RedirectException("/MakeNotice");
    }
    void action(HttpServletRequest request, Map<String, Object> view){
        //request.getParameter("message");
    }
}
