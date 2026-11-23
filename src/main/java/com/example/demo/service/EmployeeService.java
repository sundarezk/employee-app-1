package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;




public interface EmployeeService{
	Employee saveEmployee(Employee employee);
	Employee findById(int id);
	List<Employee> findAll();
	Employee findByFirstName(String firstName);
	Employee updateEmployee(int id, Employee employee);
	void deleteEmployee(int id);
}



