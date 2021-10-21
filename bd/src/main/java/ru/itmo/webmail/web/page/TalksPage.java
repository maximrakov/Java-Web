package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.MessageView;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page{
    private void action(HttpServletRequest request, Map<String, Object> view) {
        long id = (long) request.getSession().getAttribute("userId");
        User user = getUserService().find(id);
        List<MessageView> targetNames = new ArrayList<>();
        for(int i = 0;i < getTalkService().getMessages(id).size();i++){
            long sourceId = getTalkService().getMessages(id).get(i).getSourceUserId();
            targetNames.add(new MessageView(getUserService().find(sourceId).getLogin(), getTalkService().getMessages(id).get(i).getText()));
        }
        view.put("trg", targetNames);
    }
    private void send(HttpServletRequest request, Map<String, Object> view){
        String message = request.getParameter("message");
        long sourceId = (long) request.getSession().getAttribute("userId");
        long targetId =  (getUserService().findByLogin(request.getParameter("recipient"))).getId();
        getTalkService().addTalk(sourceId, targetId, message);
        throw new RedirectException("/talks");
    }
}
