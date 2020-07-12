package org.bstu.fit.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAspect {
    public void beforeServiceMethodInvocation(JoinPoint jp)
    {
        System.out.println("Invocation of method "+jp.getSignature());
    }
}
