package com.graphql.learn.graphql_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.graphql.learn.graphql_project.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Custom query methods can be defined here
    Author findByName(String name);
}
