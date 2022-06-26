package com.greatlearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.studentManagement.model.Student;
import com.greatlearning.studentManagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/list")
	public String getAllBooks(Model theModel) {
		List<Student> res = studentService.getAllStudent();
		theModel.addAttribute("studentModel", res);
		return "studentlist";
	}
	
	@RequestMapping("/add")
	public String addBook(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("student", student);
		return "savestudent";
	}
	
	@RequestMapping("/update")
	public String updateBook(@RequestParam("id") int id,Model theModel) {
		Student student = studentService.getStudentById(id);
		theModel.addAttribute("student", student);
		return "savestudent";
	}
	
	@RequestMapping("/delete")
	public String deleteBook(@RequestParam("id") int id) {
		Student student = studentService.delete(id);
		System.out.println("Deleted student Id" + student.getStudentId());
		return "redirect:/students/list";
	}
	
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {
		
		Student student;
		if(id !=0) {
			student = studentService.getStudentById(id);
			student.setStudentId(id);
			
		}else {
			student = new Student();
		}
		student.setName(name);
		student.setDepartment(department);
		student.setCountry(country);
		studentService.save(student);
			
		
		return "redirect:/students/list";
	}
}
	


