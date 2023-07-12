package com.student.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.config.ResponseStructure;
import com.student.dao.StudentDao;
import com.student.dto.Student;
import com.student.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@Valid @RequestBody Student student) {
		return studentService.saveStudent(student);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Student>> findStudent(@RequestParam int studentId) {
		return studentService.findStudent(studentId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestParam int studentId, @Valid @RequestBody Student student) {
		return studentService.updateStudent(studentId, student);

	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(@RequestParam int studentId) {
		return studentService.deleteStudent(studentId);
	}
}
