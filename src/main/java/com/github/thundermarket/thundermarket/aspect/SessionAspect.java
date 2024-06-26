package com.github.thundermarket.thundermarket.aspect;

import com.github.thundermarket.thundermarket.constant.SessionConst;
import com.github.thundermarket.thundermarket.domain.SessionUser;
import com.github.thundermarket.thundermarket.exception.ErrorMessage;
import com.github.thundermarket.thundermarket.exception.UnauthenticatedException;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class SessionAspect {

    private final HttpSession session;

    public SessionAspect(HttpSession session) {
        this.session = session;
    }

    @Before("execution(* com.github.thundermarket.thundermarket.controller.*.*(..)) && !@annotation(com.github.thundermarket.thundermarket.aspect.Authenticated)")
    public void checkSession() {
        SessionUser savedSessionUser = (SessionUser) session.getAttribute(SessionConst.SESSION_USER);
        if (savedSessionUser == null) {
            throw new UnauthenticatedException(ErrorMessage.FORBIDDEN.toString());
        }
    }

    /**
     * 세션에 있는 사용자 정보를 컨트롤러 파라미터로 사용할 수 있게 해줍니다.
     *
     * 예시
     * @GetMapping("/users")
     * public String getUser(@SessionUserParam SessionUser sessionUser) {
     * ...
     */
    @Around("execution(* com.github.thundermarket.thundermarket.controller..*.*(..)) && !@annotation(com.github.thundermarket.thundermarket.aspect.Authenticated)")
    public Object setSessionUserParam(ProceedingJoinPoint joinPoint) throws Throwable {
        SessionUser sessionUser = (SessionUser) session.getAttribute(SessionConst.SESSION_USER);

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameters.length; i++) {
            Annotation[] annotations = parameters[i].getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof SessionUserParam) {
                    args[i] = sessionUser;
                }
            }
        }

        return joinPoint.proceed(args);
    }
}
