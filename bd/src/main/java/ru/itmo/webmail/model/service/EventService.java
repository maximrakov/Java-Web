package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;
import ru.itmo.webmail.model.state.StateOfUser;

public class EventService {
    public static long countOfEvents = 0;

    public EventService() {
    }

    EventRepository eventRepository = new EventRepositoryImpl();
    public void EnterUser(long studentId) {
        eventRepository.SetState(countOfEvents, studentId, StateOfUser.ENTER);
        countOfEvents++;
    }
    public void ExitUser(long studentId) {
        eventRepository.SetState(countOfEvents, studentId, StateOfUser.LOGOUT);
        countOfEvents++;
    }
}
