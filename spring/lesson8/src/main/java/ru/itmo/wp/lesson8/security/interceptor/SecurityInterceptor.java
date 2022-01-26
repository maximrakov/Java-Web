package ru.itmo.wp.lesson8.security.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itmo.wp.lesson8.controller.IndexPage;
import ru.itmo.wp.lesson8.controller.Page;
import ru.itmo.wp.lesson8.domain.Person;
import ru.itmo.wp.lesson8.domain.Role;
import ru.itmo.wp.lesson8.security.AnyRole;
import ru.itmo.wp.lesson8.security.Guest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private final IndexPage indexPage;

    public SecurityInterceptor(IndexPage indexPage) {
        this.indexPage = indexPage;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (Page.class.isAssignableFrom(method.getDeclaringClass())) {
                if (method.getAnnotation(Guest.class) == null) {
                    return true;
                }

                Person person = indexPage.getUser(request.getSession());
                if (person != null) {
                    AnyRole anyRole = method.getAnnotation(AnyRole.class);
                    if(anyRole == null) {
                        return true;
                    }

                    for (Role.Name name: anyRole.value()) {
                        for(Role role: person.getRoles()){
                            if(role.getName().equals(name)){
                                return true;
                            }
                        }
                    }
//                    return true;
                }

                indexPage.setMessage(request.getSession(), "Access is denied ");
                response.sendRedirect(person == null ? "/enter" : "/");
                return false;
            }
        }

        return true;
    }
}
