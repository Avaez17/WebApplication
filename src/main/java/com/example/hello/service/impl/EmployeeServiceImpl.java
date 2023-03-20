package com.example.hello.service.impl;

import com.example.hello.service.EmployeeService;
import com.example.hello.exception.ResourceNotFound;
import com.example.hello.repository.EmployeeRespository;

import com.example.hello.module.Employee;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRespository employeeRepository;
	public EmployeeServiceImpl(EmployeeRespository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmployeeById(long id) {
	
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else{
//			throw new ResourceNotFoundException("Employee", "Id" ,id);
//		}
		
		
//		lamda Expression 
		return employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFound("Employee", "ID", id));
		
		
	}
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
//		we need to check whether given employee ID is present in DB or not
		Employee existingEmployee = employeeRepository
				.findById(id)
				.orElseThrow(()->
		 
				new ResourceNotFound("Employee","Id",id));
		
		existingEmployee.setFirstname(employee.getFirstname());
		existingEmployee.setLastname(employee.getLastname());
		existingEmployee.setEmail(employee.getEmail());
		
//		Save existing employee to DB
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}
	
	
	@Override
	public void deleteEmployee(long id) {
		
//		check whether employee exist in DB or not
		employeeRepository.findById(id).orElseThrow(()->
					 new ResourceNotFound("Employee","Id",id));
		employeeRepository.deleteById(id );
		 
	}
}
