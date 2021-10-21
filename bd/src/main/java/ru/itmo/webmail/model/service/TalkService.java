package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.repository.TalkRepository;
import ru.itmo.webmail.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

public class TalkService {
    private static long numberOfTalk = 0;
    TalkRepository talkRepository = new TalkRepositoryImpl();
    public void addTalk(long sourceUserId, long targetUserId, String message){
        talkRepository.addMessage(numberOfTalk++, message, sourceUserId, targetUserId);
    }
    public List<Talk> getMessages(long targetUserId){
        return talkRepository.getMessagesById(targetUserId);
    }
}
