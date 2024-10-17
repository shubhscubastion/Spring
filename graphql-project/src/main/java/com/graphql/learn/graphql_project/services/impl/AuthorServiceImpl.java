package com.graphql.learn.graphql_project.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql.learn.graphql_project.entities.Author;
import com.graphql.learn.graphql_project.repositories.AuthorRepository;
import com.graphql.learn.graphql_project.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    public Author get(Long authorId) {
        return this.authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("The Author you are looking for does not exist."));
    }

    @Override
    public List<Author> getAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author get(String name) {
        return this.authorRepository.findByName(name);
    }
}
