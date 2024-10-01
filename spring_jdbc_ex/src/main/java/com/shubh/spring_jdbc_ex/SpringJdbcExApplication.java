package com.shubh.spring_jdbc_ex;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.shubh.spring_jdbc_ex.model.Student;
import com.shubh.spring_jdbc_ex.service.StudentService;

@SpringBootApplication
public class SpringJdbcExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcExApplication.class, args);

		// Student s = new Student();
		Student s = context.getBean(Student.class);

		s.setMarks(20);
		s.setName("Shubh");
		s.setRollNo(1);

		StudentService service = context.getBean(StudentService.class);

		service.addStudent(s);

		List<Student> students = service.getStudents();
		System.out.println("check service get student");
		
	}

}
