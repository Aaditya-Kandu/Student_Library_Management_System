package com.example.Student_Library_Management_System.Controller;

import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController  {

    @Autowired
    StudentService studentService;

    @PostMapping("/add_student")
    public String createStudent(@RequestBody() Student student)
    {
        return studentService.createStudent(student);
    }

    @GetMapping("/get_student")
    public String getStudentByemail(@RequestParam("email") String email){

        return studentService.findNameByemail(email);


    }

    @PutMapping("/update_mobileNo")
    public String updateMobileNo(@RequestBody() Student student){

        return studentService.updateMobNo(student);
    }
}

