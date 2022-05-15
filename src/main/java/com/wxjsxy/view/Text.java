package com.wxjsxy.view;

import com.wxjsxy.factory.Factory;
import com.wxjsxy.model.Student;
import com.wxjsxy.server.StudentServer;

public class Text {
    public static void main(String[] args) {
        Student student = new Student(1, 23, "张三");
        StudentServer studentServer = Factory.addStudentServer();
        studentServer.addStudent(student);
    }
}
