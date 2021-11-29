package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.state.StateOfUser;

import java.sql.*;

public class EventRepositoryImpl implements EventRepository {

    public void SetState(long eventId,long studentId, StateOfUser stateOfUser){
        try {
            PreparedStatement preparedStatement = DatabaseUtils.getDataSource().prepareStatement("INSERT INTO event VALUES(?, ?, ?, CURRENT_TIMESTAMP )");
            preparedStatement.setLong(1, eventId);
            preparedStatement.setLong(2, studentId);
            preparedStatement.setObject(3, stateOfUser, Types.OTHER);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
