package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Article findById(int articleId);
    void addArticle(int id, long userid, String title, String text);
    List<Article> findAll();
    List<Article> findByOwnerId(int ownerId);
    void changeVisibility(int articleId);
    int findOwnerById(int articleId);
}
