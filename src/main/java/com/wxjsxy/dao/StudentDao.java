package com.wxjsxy.dao;

import com.wxjsxy.idao.IServer;
import com.wxjsxy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("studentdao")
public class StudentDao implements IServer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addStudent(Student student) {
        System.out.println("in java");

        String sql = "insert into t values(11112,'书店非','男')";
        jdbcTemplate.update(sql);
    }
}
