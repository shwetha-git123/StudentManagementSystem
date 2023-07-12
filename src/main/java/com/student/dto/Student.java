package com.student.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Student {
	@Id


	private int studentId;
	
	
	@NotNull(message = "Student cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String studentName;

	
	@NotNull(message = "Student cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[g][m][a][i][l]+.[c][o][m]", message = "invalid email--Should be in 	the extension of '@gmail.com' ")
	private String studentEmail;

	
	@NotNull(message = "Student cannot be null")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "8 	characters mandatory(1 	upperCase,1  	lowerCase,1 special Character,1 number)")
	private String studentPassword;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentPassword=" + studentPassword + "]";
	}

}
