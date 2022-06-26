package com.greatlearning.studentManagement.service;

import java.util.List;


import com.greatlearning.studentManagement.model.Student;

public interface StudentService {
	
	public List<Student> getAllStudent();
	public void save(Student student);
	public Student delete(int id);
	public Student getStudentById(int id);


}
