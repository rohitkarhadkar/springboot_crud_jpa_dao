package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//declare field for EntityManager
	private EntityManager entityManager;
	
	//Constructor Injection of EntityManager
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		 
		//create query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		//execute query
		List<Employee> employees = theQuery.getResultList();
		
		//return
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//get the Employee
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save the Employee
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void delete(int theId) {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//delete the Employee
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

}











