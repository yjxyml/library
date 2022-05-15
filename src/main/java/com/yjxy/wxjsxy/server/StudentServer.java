package com.yjxy.wxjsxy.server;

import com.yjxy.wxjsxy.dao.StudentDao;
import com.yjxy.wxjsxy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("studentserver")
public class StudentServer {

    @Autowired
    private StudentDao studentDao;

    public int insert(Student student)
    {
        return studentDao.InsertStudent(student);
    }

    public int delete(Student student)
    {
        return studentDao.DeleteStudent(student);
    }

    public int update(Student student)
    {
        return studentDao.UpdateStudent(student);
    }

    public List<Map<String, Object>> select()
    {
        return studentDao.SelectStudent();
    }
}
