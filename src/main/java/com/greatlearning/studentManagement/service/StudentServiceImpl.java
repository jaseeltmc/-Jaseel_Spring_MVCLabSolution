package com.greatlearning.studentManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.greatlearning.studentManagement.model.Student;

@Repository
public class StudentServiceImpl implements StudentService {
	
	SessionFactory sessionFactory;
	Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
		
		try {
		this.session = sessionFactory.getCurrentSession();
		}catch (HibernateException e) {
			this.session = sessionFactory.openSession();
		}
	}
	@Override
	@Transactional
	public List<Student> getAllStudent() {
		@SuppressWarnings("unchecked")
		List<Student> list = (List <Student>)session.createQuery("from Student").list();
		return list;
	}
	@Override
	public void save(Student student) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
		
	}
	@Override
	@Transactional
	public Student delete(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

		tx.commit();

	
		return student;
	}
	@Override
	public Student getStudentById(int id) {
		
		return session.get(Student.class, id);
	}

}
