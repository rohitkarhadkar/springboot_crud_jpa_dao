package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	//inject employeeDAO using constructor injection
	public EmployeeRestController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll()
	{
		return employeeService.findAll();
	}
	
	//expose "/employees/{employeeId}" and return specific employee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId)
	{
		Employee theEmployee = employeeService.findById(employeeId);
		
//		if(theEmployee == null)
//		{
//			throw new RunTimeException("Employee not found for id"+employeeId);
//		}
		
		return theEmployee;
	}
	
	//expose "/employees/{employeeId}" and return added employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//expose "/employees" and return updated employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee)
	{
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//expose "/employees/{employeeId}" and delete the employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId)
	{
		employeeService.delete(employeeId);
		return "delete Complete";
	}
}












