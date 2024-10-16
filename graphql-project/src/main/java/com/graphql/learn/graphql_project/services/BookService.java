package com.graphql.learn.graphql_project.services;


import java.util.List;

import com.graphql.learn.graphql_project.entities.Book;

public interface BookService {
    Book create(Book book);

    List<Book> getAll();

    Book get(int bookId);
}
