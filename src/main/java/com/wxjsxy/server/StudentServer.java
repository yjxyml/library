package com.wxjsxy.server;

import com.wxjsxy.factory.Factory;
import com.wxjsxy.idao.IServer;
import com.wxjsxy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("studentserver")
public class StudentServer {

    @Autowired
    IServer studentDao; //= Factory.addStudent();

    public void addStudent(Student student)
    {
        studentDao.addStudent(student);
    }

}
