package ru.itmo.webmail.model.repository.impl;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.repository.ArticleRepository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    public Article findById(int articleId) {
        Article article = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM article WHERE id=?");
            preparedStatement.setInt(1, (int)articleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                article = new Article(resultSet.getInt("id"), resultSet.getInt("userid"), resultSet.getString("title"), resultSet.getString("text"), resultSet.getDate("creationtime"), resultSet.getBoolean("hide"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return article;
    }
    public void addArticle(int id, long userid, String title, String text) {
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("INSERT INTO Article VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, FALSE)");
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, userid);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, text);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Article> findAll(){
        List<Article> articles = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM article");
            ResultSet resultSet = preparedStatement.executeQuery();
            articles = new ArrayList<>();
            while(resultSet.next()){
                if(resultSet.getBoolean("hide") == false) {
                    articles.add(new Article(resultSet.getInt("id"), resultSet.getInt("userid"), resultSet.getString("title"), resultSet.getString("text"), resultSet.getDate("creationtime"), resultSet.getBoolean("hide")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return articles;
    }
    public List<Article> findByOwnerId(int ownerId) {
        List<Article> articles = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM article WHERE userid=?");
            preparedStatement.setInt(1, ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            articles = new ArrayList<>();
            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getInt("userid"), resultSet.getString("title"), resultSet.getString("text"), resultSet.getDate("creationtime"), resultSet.getBoolean("hide")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return articles;
    }

    public void changeVisibility(int articleId) {
        Article currentArticle = findById(articleId);
        PreparedStatement preparedStatement = null;
        try {
            if(currentArticle.isHide()) {
                preparedStatement = DatabaseUtils.getDataSource().prepareStatement("UPDATE article SET hide=false WHERE id=?");
            }else{
                preparedStatement = DatabaseUtils.getDataSource().prepareStatement("UPDATE article SET hide=true WHERE id=?");
            }
            preparedStatement.setInt(1, articleId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int findOwnerById(int articleId){
        PreparedStatement preparedStatement = null;
        int userid = 0;
        try {
            preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM articles WHERE id=?");
            preparedStatement.setInt(1, articleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userid = resultSet.getInt("userid");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userid;
    }
}
