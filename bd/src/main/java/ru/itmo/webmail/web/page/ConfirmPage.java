package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.EmailConfirmationService;
import ru.itmo.webmail.model.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class ConfirmPage extends Page{
    public void action(HttpServletRequest request, Map<String, Object> view){
        String secret = request.getParameter("secret");
        EmailConfirmationService emailConfirmationService = getEmailConfirmationService();
        if(emailConfirmationService.ContainsSecret(secret) != -1){
            User user = getUserService().find(emailConfirmationService.ContainsSecret(secret));
            getUserService().ConfirmUser((int) user.getId());
        }
    }
}
