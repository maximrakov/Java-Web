package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.repository.ArticleRepository;
import ru.itmo.webmail.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private static int globalId = 0;
    ArticleRepository articleRepository = new ArticleRepositoryImpl();
    public void addArticle (long userId, String title, String text){
        articleRepository.addArticle(globalId, userId, title, text);
        globalId++;
    }
    public Article findById(int articleId){
        return articleRepository.findById(articleId);
    }
    public List<Article> findAll(){
        return articleRepository.findAll();
    }
    public List<Article> ArticleFindByOwnerId(int ownerId){
        return articleRepository.findByOwnerId(ownerId);
    }
    public void changeVisibility(int articleId){
        articleRepository.changeVisibility(articleId);
    }
    public int findOwnerById(int articleId){
        return articleRepository.findOwnerById(articleId);
    }
}
