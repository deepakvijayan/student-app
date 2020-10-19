package com.dv.student.api.service;

import com.dv.student.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentDAO extends JpaRepository<Student, Long> {

    List<Student> findByNameStartingWith(String name);

}
