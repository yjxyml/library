package com.yjxy.wxjsxy.idao;

import com.yjxy.wxjsxy.model.Student;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    int InsertStudent(Student student);        //增加学生信息
    int DeleteStudent(Student student);        //删除学生信息
    int UpdateStudent(Student student);        //修改学生信息
    List<Map<String, Object>> SelectStudent();        //查看学生信息
}
