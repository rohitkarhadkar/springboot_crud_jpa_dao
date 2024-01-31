package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		//create query
		//Query theQuery = entityManager.createQuery("from Employee");
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
		
		//execute query
		List<Employee> employees = theQuery.getResultList();
		
		//return list of employees
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		//entityManager.find
		Employee employee = entityManager.find(Employee.class, theId);
		
		//return employee
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//entityManager.merge
		Employee dbEmployee =  entityManager.merge(theEmployee);
		
		//return employee
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void delete(int theId) {
		
		//entityManager.createQuery
		Query theQuery = entityManager.createQuery("delete from Employee where id=:empId");
		theQuery.setParameter("empId", theId);
		theQuery.executeUpdate();
	}

}
