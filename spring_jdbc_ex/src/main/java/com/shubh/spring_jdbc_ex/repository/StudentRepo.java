package com.shubh.spring_jdbc_ex.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.shubh.spring_jdbc_ex.model.Student;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String sql = "INSERT INTO student (rollno, name, marks) VALUES (?, ?, ?)";
        jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks());
        System.out.println("Added!");
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM student";

        RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setRollNo(rs.getInt("rollno"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        };

        List<Student> students = jdbc.query(sql, mapper);
        System.out.println("Retrieved " + students.size() + " students.");
        return students;
    }
}
