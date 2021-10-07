package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.NewsRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
    private static final File tmpDir = new File(System.getProperty("java.io.tmpdir"));

    private static List<News> news;
    {
        news = new ArrayList<>();
    }
    public NewsRepositoryImpl(){
//        news = new ArrayList<>();
//        news.add(new News(10, "wefwefokowlefwoef"));
        /*try {
            news = (List<News>) new ObjectInputStream(
                    new FileInputStream(new File(tmpDir, getClass().getSimpleName()))).readObject();
        } catch (Exception e){
            news = new ArrayList<>();
        }*/
    }

    @Override
    public void save(News newss) {
        news.add(newss);

       /* try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(new File(tmpDir, getClass().getSimpleName())));
            objectOutputStream.writeObject(newss);
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't save user.", e);
        }*/
    }

    @Override
    public List<News> findAll() {
        return new ArrayList<>(news);
    }
}
