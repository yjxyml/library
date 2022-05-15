package com.ml.wxjsxy.dao;

import com.ml.wxjsxy.iserver.IBookDao;
import com.ml.wxjsxy.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("bookdao")
public class BookDao implements IBookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> bookSelect() {
        String sql = "select * from book";
        List<Book> books = jdbcTemplate.query(sql, new MyRowMapper());
        return books;
    }

    @Override
    public int InsertBook(Book book) {
        String sql = "insert into book values ('"+book.getId()+"','"+book.getName()+"','"+book.getDesc()+"','"+book.getAuthor()+"','"+book.getPrice()+"','"+book.getStock()+"')";
        int insert = jdbcTemplate.update(sql);
        return insert;
    }

    @Override
    public int DeleteBook(Book book) {
        String sql = "delete from book where id = '"+book.getId()+"'";
        int delete = jdbcTemplate.update(sql);
        return delete;
    }

    @Override
    public int UpdateBook(Book book) {
        String sql = "update book set id = '"+book.getId()+"' , name = '"+book.getName()+"' , description = '"+book.getDesc()+"' , author = '"+book.getAuthor()+"' , price = '"+book.getPrice()+"' , stock = '"+book.getStock()+"' where id = '"+book.getUpdateId()+"'";
        int update = jdbcTemplate.update(sql);
        return update;
    }

    class MyRowMapper implements RowMapper<Book>
    {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String author = rs.getString("author");
            double price = rs.getDouble("price");
            int stock = rs.getInt("stock");
            book.setId(id);
            book.setName(name);
            book.setDesc(description);
            book.setAuthor(author);
            book.setPrice(price);
            book.setStock(stock);
            return book;
        }
    }
}
