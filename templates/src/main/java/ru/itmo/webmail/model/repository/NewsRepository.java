package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;

import java.util.List;

public interface NewsRepository {
    void save(News newss);
    List<News> findAll();
}
