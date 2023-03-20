package com.example.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hello.module.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {

}
