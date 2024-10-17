package com.graphql.learn.graphql_project.services;

import java.util.List;

import com.graphql.learn.graphql_project.entities.Author;

public interface AuthorService {

 Author create(Author author);
 Author findByName(String name);
 List<Author> getAll();
 Author get (String string);
}