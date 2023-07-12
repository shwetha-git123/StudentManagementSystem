package com.student.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.student.dto.Student;
import com.student.repo.StudentRepo;

@Repository
public class StudentDao {
	@Autowired
	private StudentRepo studentRepo;
	

	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public Optional<Student> findStudentById(int studentId){
		return studentRepo.findById(studentId);
	}
	public Student deleteStudent(Student student) {
		studentRepo.delete(student);
		return student;
	}

}
