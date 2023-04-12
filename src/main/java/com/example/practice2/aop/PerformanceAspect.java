package com.example.practice2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    //특정 어노테이션 대상 지정
    @Pointcut("@annotation(com.example.practice2.annotation.RunningTime)")
    private void enableRunningTime() {}

    //기본 패키지 모든 메소드
    @Pointcut("execution(* com.example.practice2..*.*(..))")
    private void cut(){}

    //실행 시점 설정 : 두 조건을 모두 만족하는 대상을 전후로 부가 기능 삽입
    @Around("cut() && enableRunningTime()")
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //method 전 측정 시작
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //method proceed
        Object returningObj = joinPoint.proceed();

        stopWatch.stop();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }

}
