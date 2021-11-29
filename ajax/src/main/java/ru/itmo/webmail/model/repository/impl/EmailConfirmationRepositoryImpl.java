package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.User;

import java.sql.*;

public class EmailConfirmationRepositoryImpl {
    public int ContainsSecret(String secret){
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM emailConfirmition WHERE secret=?");

            preparedStatement.setString(1, secret);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("userId");
            }else{
                return -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public void addSecretKey(long id, int userId, String secret) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("INSERT INTO emailConfirmition VALUES(?, ?, ?)");
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, userId);
            preparedStatement.setString(3, secret);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
