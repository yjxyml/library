package com.wxjsxy.view;

import com.wxjsxy.model.Student;
import com.wxjsxy.server.StudentServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.wxjsxy")
public class Text1 {
    public static void main(String[] args) {
        //写的代码 都是.class--->遍历---->动态加载到内存中---->创建对象---->map集合里面
        Student student = new Student(1, 23, "张三");
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(Text1.class);

        StudentServer studentserver = (StudentServer) acac.getBean("studentserver");

        studentserver.addStudent(student);
    }
}
