package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.TalkRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalkRepositoryImpl implements TalkRepository {
    @Override
    public void addMessage(long id, long from, long to, String message) {
        Statement statement = null;
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("INSERT INTO Talk VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP)");
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, from);
            preparedStatement.setLong(3, to);
            preparedStatement.setString(4, message);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Talk> getMessagesById(long id) {
        List<Talk> talks = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("SELECT * FROM Talk WHERE targetuserid=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Talk talk = new Talk();
                talk.setId(resultSet.getInt("id"));
                talk.setSourceUserId(resultSet.getInt("sourceuserid"));
                talk.setTargetUserId(resultSet.getInt("targetuserid"));
                talk.setText(resultSet.getString("text"));
                talk.setCreationTime(resultSet.getDate("creationtime"));
                talks.add(talk);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return talks;
    }
}
