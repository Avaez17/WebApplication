package com.example.hello.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.module.*;
import com.example.hello.service.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200/")
public class EmployeeController {

	private EmployeeService employeeService;

	
	
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	//	build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
	}
	
//	build all employees REST API
	@GetMapping()
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
//	build get employee by id REST API
//	localhost:8080/api/employees/1
	 
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}
	
//	build update employee REST api
//	localhost:8080/api/employees/1
	
	@PutMapping("{Id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("Id") long id
													,@RequestBody Employee employee){
			return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);						
		}
	
//	build delete employee RESt api
//	localhost:8080/api/employees/1
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
//		delete from DB
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee Deleted Successfully!.",HttpStatus.OK);
	}
}
