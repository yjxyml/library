package com.ml.wxjsxy.dao;

import com.ml.wxjsxy.iserver.IUserDao;
import com.ml.wxjsxy.model.Privilege;
import com.ml.wxjsxy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("userdao")
public class UserDao implements IUserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> userLogin(User user) {
        String sql = "select username,userpassword from user where user.username = '"+user.getName()+"' and user.userpassword = '"+user.getPassword()+"'";
        String sql2 = "select username,userpassword,rname from user inner join role on role.rid = user.userrole where user.username = '"+user.getName()+"' and user.userpassword = '"+user.getPassword()+"'";
        try{
            List<User> users = jdbcTemplate.query(sql2,new MyRowMapper());
            //如果数组中不存在任何元素，则返回 true。 然后为null查询不到数据
            if(users.isEmpty() == true)
            {
                return null;
            }
            return users;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    class MyRowMapper implements RowMapper<User>
    {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = null;
                String username = rs.getString("username");
                String userpassword = rs.getString("userpassword");
                String role = rs.getString("rname");
                user = new User();
                user.setName(username);
                user.setPassword(userpassword);
                user.setRole(role);
                return user;
            }
    }

    @Override
    public int InsertUser(User user) {
        String sql = "insert into user values(null,'"+user.getName()+"','"+user.getPassword()+"','"+user.getSex()+"','"+user.getTelephone()+"','"+0+"')";
        int insert = jdbcTemplate.update(sql);
        return insert;
    }

    @Override
    public User Privilege(final User user) {
        try {
            String sql = "select a.posts,b.posts \n" +
                    "from user inner join role on role.rid = user.userrole \n" +
                    "inner join urp on role.rid = urp.rid \n" +
                    "inner join permissions a on a.pid = urp.pid\n" +
                    "inner join permissions b on a.pid = b.ppid \n" +
                    "where user.username = '"+user.getName()+"'\n";
            jdbcTemplate.query(sql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    String posts = "";
                    Privilege p = null;
                    posts = rs.getString(1);
                    p = new Privilege(posts);
                    user.setList(p);
                    p.setList(new Privilege(rs.getString(2)));
                    while (rs.next())
                    {
                        System.out.println(rs.getString(1));
                        if(!posts.equals(rs.getString(1)))
                        {
                            posts = rs.getString(1);
                            p = new Privilege(posts);
                            user.setList(p);
                        }
                        System.out.println(rs.getString(2));
                        p.setList(new Privilege(rs.getString(2)));
                    }
                    return user;
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }

}
