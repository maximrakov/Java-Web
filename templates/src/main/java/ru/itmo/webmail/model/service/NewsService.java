package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.repository.NewsRepository;
import ru.itmo.webmail.model.repository.impl.NewsRepositoryImpl;

import java.util.List;

public class NewsService {
    private int newsId = 1;
    private static NewsRepository newsRepository = new NewsRepositoryImpl();
    public  void makeNews(News news){
        newsRepository.save(news);
    }
    public List<News> finaAll(){
        return newsRepository.findAll();
    }
}
