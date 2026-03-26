package com.digitalskies.demo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    List<Course> getCourses(){

       return Arrays.asList(
               new Course(1,"Learn AWS", "DigitalSkies"),
               new Course(1,"Learn Azure", "DigitalSkies"),
               new Course(1,"Learn GCP", "DigitalSkies")
       );

    }

}
