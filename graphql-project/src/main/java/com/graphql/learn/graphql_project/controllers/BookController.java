package com.graphql.learn.graphql_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.graphql.learn.graphql_project.entities.Book;
import com.graphql.learn.graphql_project.entities.Author;
import com.graphql.learn.graphql_project.services.BookService;
import com.graphql.learn.graphql_project.services.AuthorService;
import lombok.Getter;
import lombok.Setter;

@Controller
public class BookController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @MutationMapping("createBook")
    public Book createBook(@Argument BookInput bookInput) {
        // Validate input (consider using a validation framework)

        Book book = new Book();
        book.setTitle(bookInput.getTitle());
        book.setDesc(bookInput.getDesc());
        book.setPrice(bookInput.getPrice());
        book.setPages(bookInput.getPages());

        // Handle author
        Author author = authorService.get(bookInput.getAuthor());
        if (author == null) {
            author = new Author();
            author.setName(bookInput.getAuthor());
            authorService.create(author);
        }
        book.setAuthor(author);

        return bookService.create(book);
    }

    @QueryMapping("allBooks")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @QueryMapping("getBook")
    public Book getBook(@Argument int bookId) {
        return bookService.get(bookId);
    }
}

/**
 * InnerBookController
 */

// @Getter
// @Setter
class BookInput {
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    private String desc;
    private String author;
    private double price;
    private int pages;
}
