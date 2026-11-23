package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import com.example.demo.entity.Employee;
import com.example.demo.exception.*;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
private EmployeeRepository employeeRepository;
public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository)
{
	super();
	this.employeeRepository=employeeRepository;
	
}
@Override
public Employee saveEmployee(Employee employee)
{
	return employeeRepository.save(employee);
}
@Override
public Employee findById(int id)
{
	return employeeRepository.findById(id).orElseThrow(()->new EmployeeException("Employee Not Found"));
}

@Override
public List<Employee>  findAll()
{
	return employeeRepository.findAll();
}
@Override
public Employee findByFirstName(String firstName)
{
	return employeeRepository.findByFirstName(firstName);
	

}




//update Employee
@Override
public Employee updateEmployee(int id, Employee employee)
{
	Employee existingEmployee= employeeRepository.findById(id).orElseThrow(()->new EmployeeException("Employee Not Found with Id:" +id));
	existingEmployee.setFirstName(employee .getFirstName());
	existingEmployee.setLastName(employee .getLastName());
	existingEmployee.setEmailId(employee .getEmailId());
	return employeeRepository.save(existingEmployee);

}


//Delete Employee
@Override
public void deleteEmployee(int id)
{
	Employee employee= employeeRepository.findById(id).orElseThrow(()->new EmployeeException("Employee Not Found with Id:" +id));
	
	employeeRepository.delete(employee);

}


}
