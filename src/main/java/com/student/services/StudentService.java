package com.student.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.config.ResponseStructure;
import com.student.dao.StudentDao;
import com.student.dto.Student;

import com.student.exception.StudentNotFoundByIdException;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentdao;

	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {
		Student student1 = studentdao.saveStudent(student);
		ResponseStructure<Student> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());// value() =>it gives the http status code.
		responseStructure.setMessage("Student Created Successfully!!");
		responseStructure.setData(student1);
		return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Student>> findStudent(int existingStudentId) {
		Optional<Student> optional = studentdao.findStudentById(existingStudentId);
		if (optional.isEmpty()) {
			throw new StudentNotFoundByIdException("Student Not Found");
			
		} else {
			Student student = optional.get();
			ResponseStructure<Student> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Student found successfully");
			responseStructure.setData(student);

			return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Student>> updateStudent(int existingStudentId, Student updateStudent) {
		Optional<Student> optional = studentdao.findStudentById(existingStudentId);
		if (optional.isEmpty()) {
			throw new StudentNotFoundByIdException("Student Not Found");
		} else {
			updateStudent.setStudentId(existingStudentId);
			Student student = studentdao.saveStudent(updateStudent);
			ResponseStructure<Student> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("student Updated successfully");
			responseStructure.setData(student);

			return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int existingStudentId) {
		Optional<Student> optional = studentdao.findStudentById(existingStudentId);
		if (optional.isEmpty()) {
			throw new StudentNotFoundByIdException("Student Not Found");
		} else {
			Student student = optional.get();
			studentdao.deleteStudent(student);
			ResponseStructure<Student> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Student Data Deleted!!");
			responseStructure.setData(student);
			return new ResponseEntity<ResponseStructure<Student>>(responseStructure, HttpStatus.OK);
		}
	}
}
