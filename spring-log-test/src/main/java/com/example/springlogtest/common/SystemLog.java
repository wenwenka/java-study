package com.example.springlogtest.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//生命周期
@Target(ElementType.METHOD)//作用范围
public @interface SystemLog {
    String methoddesc() default "";//方法描述
}
