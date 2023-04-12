package com.example.practice2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //aop 클래스 선언 : 부가기능 주입
@Component
@Slf4j
public class DebuggingAspect {

    //api package all method
    @Pointcut("execution(* com.example.practice2.api.*.*(..))")
    private void cut(){}

    //실행 시점 cut 수행 이전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint){   //cut 대상 메소드
        //입력값 가져요기
        Object[] args = joinPoint.getArgs();
        //classname
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //methodname
        String methodName = joinPoint.getSignature().getName();
        //input logging
        for(Object obj: args){
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj){  //cut 대상 메소드, 리턴값
        //classname
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //methodname
        String methodName = joinPoint.getSignature().getName();
        //반환값 로깅
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
