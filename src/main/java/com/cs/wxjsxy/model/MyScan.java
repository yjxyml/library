package com.cs.wxjsxy.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解是干嘛用的 注解只有在反射的时候才起作用
//注解可以给类和方法以及属性提供数据
@Target(ElementType.TYPE)//只能给类提供信息
@Retention(RetentionPolicy.RUNTIME)
public @interface MyScan {
    String value();
}
