package com.example.Student_Library_Management_System.Controller;

import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class StudentController  {

    @Autowired
    StudentService studentService;

    @PostMapping("/add_student")
    public String createStudent(@RequestBody() Student student)
    {
        return studentService.createStudent(student);
    }
}

