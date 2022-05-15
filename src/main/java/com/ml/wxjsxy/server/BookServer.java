package com.ml.wxjsxy.server;

import com.ml.wxjsxy.dao.BookDao;
import com.ml.wxjsxy.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bookserver")
public class BookServer {

    @Autowired
    private BookDao bookDao;

    public List<Book> selectBooks()
    {
        List<Book> books = bookDao.bookSelect();
        return books;
    }

    public int insert(Book book)
    {
        return bookDao.InsertBook(book);
    }

    public int delete(Book book)
    {
        return bookDao.DeleteBook(book);
    }

    public int update(Book book)
    {
        return bookDao.UpdateBook(book);
    }
}
