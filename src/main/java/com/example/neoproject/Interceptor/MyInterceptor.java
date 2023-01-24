/*package com.example.neoproject.Interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import java.util.Date;

public class MyInterceptor extends AbstractMonitoringInterceptor {

    public MyInterceptor() {
    }

    public MyInterceptor(boolean useDynamicLogger) {
        setUseDynamicLogger(useDynamicLogger);
    }

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
        String name = createInvocationTraceName(invocation);
        long start = System.currentTimeMillis();
        logger.info("Method " + name + " execution started at:" + new Date());
        try {
            return invocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.info("Method " + name + " execution lasted:" + time + " ms");
            logger.info("Method " + name + " execution ended at:" + new Date());
        }
    }

}*/
