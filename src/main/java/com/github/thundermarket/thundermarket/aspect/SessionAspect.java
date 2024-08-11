package com.github.thundermarket.thundermarket.aspect;

import com.github.thundermarket.thundermarket.constant.SessionConst;
import com.github.thundermarket.thundermarket.domain.SessionUser;
import com.github.thundermarket.thundermarket.exception.ErrorMessage;
import com.github.thundermarket.thundermarket.exception.UnauthenticatedException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Optional;

@Component
@Aspect
@Slf4j
public class SessionAspect {

    public static final String CONTROLLER_POINTCUT = "execution(* com.github.thundermarket.thundermarket.controller.*.*(..)) && !@annotation(com.github.thundermarket.thundermarket.aspect.Authenticated)";
    private final HttpSession session;

    public SessionAspect(HttpSession session) {
        this.session = session;
    }

    /**
     * 전체 컨트롤러 메서드를 대상으로 사용자 세션을 체크합니다.
     * @Authenticated 애노테이션으로 세션 체크를 통과할 메서드를 설정할 수 있습니다. (회원가입, 로그인 등)
     */
    @Before(CONTROLLER_POINTCUT)
    public void checkSession() {
        getSessionUser();
    }

    /**
     * 세션에 있는 사용자 정보를 컨트롤러 파라미터로 사용할 수 있게 해줍니다.
     * @SessionUserParam 애노테이션을 파라미터에 추가해서 사용합니다.
     *
     * 예시
     * @GetMapping("/users")
     * public String getUser(@SessionUserParam SessionUser sessionUser) {
     */
    @Around(CONTROLLER_POINTCUT)
    public Object setSessionUserParam(ProceedingJoinPoint joinPoint) throws Throwable {
        SessionUser sessionUser = getSessionUser();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(SessionUserParam.class)) {
                args[i] = sessionUser;
                break;
            }
        }

        return joinPoint.proceed(args);
    }

    /**
     *  세션에 저장된 사용자 정보를 가져옵니다.
     *  가져오는데 실패했다면 예외를 발생시킵니다.
     */
    private SessionUser getSessionUser() {
        return Optional.ofNullable((SessionUser) session.getAttribute(SessionConst.SESSION_USER)).orElseThrow(() -> {
            log.warn("Unauthenticated access attempt");
            return new UnauthenticatedException(ErrorMessage.FORBIDDEN.toString());
        });
    }

}
