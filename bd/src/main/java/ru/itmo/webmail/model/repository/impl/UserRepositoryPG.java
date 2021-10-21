package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.UserRepository;

import java.sql.*;
import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepositoryPG implements UserRepository {
    @Override
    public User find(long userId) {
        User resUser = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM person WHERE id=?");

            preparedStatement.setInt(1, (int)userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            resUser = new User();

            resUser.setId(resultSet.getInt("id"));
            resUser.setLogin(resultSet.getString("login"));
            resUser.setEmail(resultSet.getString("email"));
            resUser.setConfirmed(resultSet.getBoolean("confirmed"));
            resUser.setCreationTime(resultSet.getDate("creationtime"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resUser;
    }

    @Override
    public User findByLogin(String login) {
        User resUser = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM person WHERE login=?");

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {

                resUser = new User();

                resUser.setId(resultSet.getInt("id"));
                resUser.setLogin(resultSet.getString("login"));
                resUser.setConfirmed(resultSet.getBoolean("confirmed"));
                resUser.setCreationTime(resultSet.getDate("creationtime"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resUser;
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        User resUser = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM person WHERE login=? and passwordsha=?");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, passwordSha);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            resUser = new User();

            resUser.setId(resultSet.getInt("id"));
            resUser.setLogin(resultSet.getString("login"));
            resUser.setEmail(resultSet.getString("email"));
            resUser.setConfirmed(resultSet.getBoolean("confirmed"));
            resUser.setCreationTime(resultSet.getDate("creationtime"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resUser;
    }

    @Override
    public List<User> findAll() {
        List<User> resUsers = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM person");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setConfirmed(resultSet.getBoolean("confirmed"));
                user.setCreationTime(resultSet.getDate("creationtime"));
                resUsers.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resUsers;
    }

    @Override
    public User findByEmail(String email, String passwordSha){
        User resUser = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM person WHERE email=? and passwordsha=?");

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordSha);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            resUser = new User();

            resUser.setId(resultSet.getInt("id"));
            resUser.setLogin(resultSet.getString("login"));
            resUser.setEmail(resultSet.getString("email"));
            resUser.setConfirmed(resultSet.getBoolean("confirmed"));
            resUser.setCreationTime(resultSet.getDate("creationtime"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resUser;
    }
    @Override
    public void save(User user, String passwordSha) {
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP )");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, passwordSha);
            preparedStatement.setBoolean(5, user.isConfirmed());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void confirmUser(long userId){
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("UPDATE person SET confirmed=true WHERE id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
