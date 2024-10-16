package com.graphql.learn.graphql_project.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql.learn.graphql_project.entities.Book;
import com.graphql.learn.graphql_project.repositories.BookRep;
import com.graphql.learn.graphql_project.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRep bookRep;

    @Autowired
    public BookServiceImpl(BookRep bookRep){
        this.bookRep = bookRep;
    }

    @Override
    public Book create(Book book) {
        // TODO Auto-generated method stub
        return this.bookRep.save(book);

    }

    @Override
    public List<Book> getAll() {
        // TODO Auto-generated method stub
        return this.bookRep.findAll();
    }

    @Override
    public Book get(int bookId) {
        // TODO Auto-generated method stub
        return this.bookRep.findById(bookId).orElseThrow(()-> new RuntimeException("The Book you are looking for, does not exist!!"));
    }

}
