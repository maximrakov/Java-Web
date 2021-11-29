package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.state.StateOfUser;

public interface EventRepository {

    void SetState(long eventId,long studentId, StateOfUser enter);
}
