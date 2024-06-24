package com.github.thundermarket.thundermarket.aspect;

import com.github.thundermarket.thundermarket.exception.ErrorMessage;
import com.github.thundermarket.thundermarket.exception.UnauthenticatedException;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SessionCheckAspect {

    private final HttpSession session;

    public SessionCheckAspect(HttpSession session) {
        this.session = session;
    }

    @Before("execution(* com.github.thundermarket.thundermarket.controller.*.*(..)) && !@annotation(com.github.thundermarket.thundermarket.aspect.Authenticated)")
    public void checkSession() {
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            throw new UnauthenticatedException(ErrorMessage.FORBIDDEN.toString());
        }
    }
}
