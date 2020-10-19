package com.dv.student.api.service.impl;

import com.dv.student.api.controller.StudentRestApiController;
import com.dv.student.api.model.Student;
import com.dv.student.api.service.StudentDAO;
import com.dv.student.api.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<Student> getAutoComplete(String searchParam) {
        logger.debug("About to execute students query with paramerer {}", searchParam);
        List<Student> studentList =studentDAO.findByNameStartingWith(searchParam);
        logger.debug("Records number found ={}", studentList !=null ? studentList.size() : null);
        return studentList;
    }
}
