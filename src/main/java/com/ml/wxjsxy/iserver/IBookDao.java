package com.ml.wxjsxy.iserver;

import com.ml.wxjsxy.model.Book;

import java.util.List;

public interface IBookDao {
    List<Book> bookSelect();
    int InsertBook(Book book);
    int DeleteBook(Book book);
    int UpdateBook(Book book);
}
