package com.yjxy.wxjsxy.conflg;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@PropertySource("classpath:jdbc.properties")            //引用resources文件下jdbc.properties
public class Dbconifg {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.jdbcurl}")
    private String url;

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name="dataSource")
    public DataSource createDataSource()
    {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(user);
        return druidDataSource;
    }

    @Bean(name="jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource source)
    {
        return new JdbcTemplate(source);
    }
}
