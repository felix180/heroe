package com.felix180.person.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${timer.enabled}")
public class TimeAdvice {
  @Around("@annotation(timerLog)")
  public Object executionTime(ProceedingJoinPoint point, TimerLog timerLog) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object object = point.proceed();
    long endtime = System.currentTimeMillis();

    log.info(
        " Method Name: "
            + point.getSignature().getName()
            + ". Time taken for Execution is : "
            + (endtime - startTime)
                + "ms");
    return object;
  }
}
