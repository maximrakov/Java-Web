package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Talk;

import java.util.List;

public interface TalkRepository {
    void addMessage(long id, long from, long to, String message);
    List<Talk> getMessagesById(long id);
}
