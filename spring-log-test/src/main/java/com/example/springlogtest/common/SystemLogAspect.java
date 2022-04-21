package com.example.springlogtest.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
@Slf4j
public class SystemLogAspect {

    ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(com.example.springlogtest.common.SystemLog)")
    public void logPointCut() {

    }

    //环绕注入
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();//记录目标方法的开始执行时间
        Object result = point.proceed();
        long methodExecuteTime = System.currentTimeMillis() - startTime;

        //日志信息记录

        try {
            MethodSignature methodSignature = (MethodSignature)point.getSignature();
            Method method = methodSignature.getMethod();
            SystemLog systemLog = method.getDeclaredAnnotation(SystemLog.class);
            String className = point.getTarget().getClass().getName();//类名
            String methodName = method.getName();//方法名

            //入参信息
            Object[] args = point.getArgs();
            List<String> list = new ArrayList<>();
            for (Object obj : args) {
                list.add(objectMapper.writeValueAsString(obj));
            }
            log.info(className+"."+methodName+"，"+systemLog.methoddesc()+",入参："+list.toString());
            log.info(className+"."+methodName+"，"+systemLog.methoddesc()+"，耗时："+methodExecuteTime+"ms");
        }catch(Exception e){
            log.error("日志切入出错");
        }

        return result;
    }
}
