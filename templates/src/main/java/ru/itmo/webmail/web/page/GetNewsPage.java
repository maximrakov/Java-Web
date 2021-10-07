package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GetNewsPage extends Page{
    NewsService newsService = new NewsService();
    public void action(HttpServletRequest request, Map<String, Object>view){
        view.put("curNews", newsService.finaAll());
    }
}
