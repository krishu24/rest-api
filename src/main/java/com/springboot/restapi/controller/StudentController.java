package com.springboot.restapi.controller;

import com.springboot.restapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.SocketOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
             "Krishna",
            "Desai"
        );
        return ResponseEntity.ok().header("custom-header","Shivam").body(student);
        //return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Krishna","Desai"));
        students.add(new Student(2,"Shivam","Desai"));
        students.add(new Student(3,"Jay","Desai"));
        return ResponseEntity.ok(students);
    }

    //Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/krishna/desai
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student= new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring BOOT REST API with request param
    // http://localhost:8080/students/query?id=1&firstName=Krishna&lastName=Desai
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student= new Student(id,firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP POST Request
    //@PostMapping and @RequestBody
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    // @PutMapping and @RequestBody
    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Spring boot REST API that handles HTTP DELETE Request - deleting existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
