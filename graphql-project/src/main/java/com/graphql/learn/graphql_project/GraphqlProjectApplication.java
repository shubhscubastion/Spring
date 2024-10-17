package com.graphql.learn.graphql_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.graphql.learn.graphql_project.entities.Author;
import com.graphql.learn.graphql_project.entities.Book;
import com.graphql.learn.graphql_project.services.AuthorService;
import com.graphql.learn.graphql_project.services.BookService;

@SpringBootApplication
public class GraphqlProjectApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Author author1 = new Author();
		author1.setName("Hero Shero");

		Author author2 = new Author();
		author2.setName("ZeroSher");

		author1 = authorService.create(author1);
		author2 = authorService.create(author2);

		Book b1 = new Book();
		b1.setTitle("Shubhs Book");
		b1.setDesc("Good book");
		b1.setPages(2000);
		b1.setPrice(1230);
		b1.setAuthor(author1);

		Book b2 = new Book();
		b2.setTitle("Shubhs 2nd Book");
		b2.setDesc("Good and better book");
		b2.setPages(500);
		b2.setPrice(100);
		b2.setAuthor(author2);

		Book b3 = new Book();
		b3.setTitle("Head First book");
		b3.setDesc("For starting with JAVA");
		b3.setPages(6000);
		b3.setPrice(3000);
		b3.setAuthor(author1);

		this.bookService.create(b1);
		this.bookService.create(b2);
		this.bookService.create(b3);

	}

}
