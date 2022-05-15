package com.yjxy.wxjsxy.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.yjxy.wxjsxy.idao.IStudentDao;
import com.yjxy.wxjsxy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("studentdao")
public class StudentDao implements IStudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int InsertStudent(Student student) {
        String sql = "insert into t values("+student.getId()+",'"+student.getName()+"','"+student.getSex()+"')";
        int insert = jdbcTemplate.update(sql);
        return insert;
    }

    public int DeleteStudent(Student student) {
        String sql = "delete from t where tname = '"+student.getName()+"'";
        int delete = jdbcTemplate.update(sql);
        return delete;
    }

    public int UpdateStudent(Student student) {
        String sql = "update t set tid = '"+student.getId()+"' , tsex = '"+student.getSex()+"' where tname = '"+student.getName()+"'";
        int update = jdbcTemplate.update(sql);
        return update;
    }

    public List<Map<String, Object>> SelectStudent() {
        String sql = "select * from t";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
