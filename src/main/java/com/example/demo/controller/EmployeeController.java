package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE EMPLOYEE
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> saveEmployee(
            @RequestBody Employee employee) {

        Employee savedEmployee =
                employeeService.saveEmployee(employee);

        return new ResponseEntity<>(
                savedEmployee,
                HttpStatus.CREATED);
    }

    // SECURITY TEST ENDPOINT
    @GetMapping("/security")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getUserInfo(Authentication authentication) {

        return "User : "
                + authentication.getName()
                + " | Roles : "
                + authentication.getAuthorities()
                + " | You Received All Employees List";
    }

    // GET ALL EMPLOYEES
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> getAllEmployee() {

        List<Employee> employees =
                employeeService.findAll();

        return new ResponseEntity<>(
                employees,
                HttpStatus.OK);
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable int id) {

        Employee employee =
                employeeService.findById(id);

        return new ResponseEntity<>(
                employee,
                HttpStatus.OK);
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {

        Employee updatedEmployee =
                employeeService.updateEmployee(id, employee);

        return new ResponseEntity<>(
                updatedEmployee,
                HttpStatus.OK);
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id) {

        employeeService.deleteEmployee(id);

        return new ResponseEntity<>(
                "Employee Deleted Successfully",
                HttpStatus.OK);
    }
}