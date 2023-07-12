package com.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
