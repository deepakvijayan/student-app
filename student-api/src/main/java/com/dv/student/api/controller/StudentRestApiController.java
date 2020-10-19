package com.dv.student.api.controller;

import com.dv.student.api.exception.BadRequestException;
import com.dv.student.api.model.Student;
import com.dv.student.api.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class StudentRestApiController {
    private static final Logger logger = LoggerFactory.getLogger(StudentRestApiController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/students/{searchParam}")
    public List<Student> autoSuggest(@PathVariable("searchParam") @NotBlank String searchParam){
        if(searchParam.length() > 0){
            logger.debug("Starting search by using parameter :{}", searchParam);
            return studentService.getAutoComplete(searchParam);
        }
        else {
            logger.debug("Null search parameter :{}", searchParam);
            throw new BadRequestException("Search parameter is invalid");
        }
    }
}
