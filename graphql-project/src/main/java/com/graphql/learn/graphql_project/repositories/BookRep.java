package com.graphql.learn.graphql_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graphql.learn.graphql_project.entities.Book;

public interface BookRep extends JpaRepository<Book, Integer> {
    
}
