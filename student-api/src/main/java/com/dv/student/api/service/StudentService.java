package com.dv.student.api.service;

import com.dv.student.api.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAutoComplete(String searchParam);
}
