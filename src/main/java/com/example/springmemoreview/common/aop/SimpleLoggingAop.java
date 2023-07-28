package com.example.springmemoreview.common.aop;

import com.example.springmemoreview.security.user.UserDetailsImpl;
import com.example.springmemoreview.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SimpleLoggingAop {

    @Pointcut("execution(* com.example.springmemoreview.auth.controller.AuthController.*(..))")
    private void authMethods() {
    }

    @Pointcut("execution(* com.example.springmemoreview.memo.controller.MemoController.*(..))")
    private void memoMethods() {
    }

    @Pointcut("execution(* com.example.springmemoreview.comment.controller.CommentController.*(..))")
    private void commentMethods() {
    }

    @Before("authMethods() || memoMethods() || commentMethods()")
    public void methodBeforeLogging(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            User loginUser = userDetails.getUser();

            System.out.println("==========================================");
            System.out.println("메서드 실행 요청자 : " + loginUser.getNickname());
            System.out.println("실행 메서드 : " + getMethodName(joinPoint));
        } else {
            System.out.println("==========================================");
            System.out.println("실행 메서드 : " + getMethodName(joinPoint));
        }
        printParameters(joinPoint);
    }

    @AfterReturning(value = "authMethods() || memoMethods() || commentMethods()", returning = "obj")
    public void methodAfterLogging(JoinPoint joinPoint, Object obj) {
        System.out.println("종료 메서드 : " + getMethodName(joinPoint));
        System.out.println("반환 타입 : " + obj.getClass().getSimpleName());
        System.out.println("==========================================");
    }

    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getName();
    }

    private void printParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        int i = 1;
        if (args.length <= 0) {
            System.out.println("변수 없음");
        }
        for (Object arg : args) {
            System.out.println("변수 타입" + i + " : " + arg.getClass().getSimpleName());
            i++;
        }
    }
}
