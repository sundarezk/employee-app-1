package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("shyam");
        employee.setLastName("sundar");
        employee.setEmailId("ss@gmail.com");


        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/api/v1/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("shyam"))
                .andExpect(jsonPath("$.lastName").value("sundar"));
    }

    @Test
    void testGetEmployeeById() throws Exception {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("shyam");
        employee.setLastName("sundar");
        employee.setEmailId("ss@gmail.com");
      

        when(employeeService.findById(1))
                .thenReturn(employee);

        mockMvc.perform(get("/api/v1/emp/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("shyam"))
                .andExpect(jsonPath("$.lastName").value("sundar"));

    }

    @Test
    void testGetAllEmployees() throws Exception {

        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setFirstName("shyam");
        emp1.setLastName("sundar");
        emp1.setEmailId("ss@gmail.com");


        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setFirstName("madan");
        emp2.setLastName("kumar");
        emp2.setEmailId("mk@gmail.com");

        List<Employee> employees = Arrays.asList(emp1, emp2);

        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/emp")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("shyam"))
                .andExpect(jsonPath("$[0].lastName").value("sundar"))
                .andExpect(jsonPath("$[0].emailId").value("ss@gmail.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("madan"))
                .andExpect(jsonPath("$[1].lastName").value("kumar"))
                .andExpect(jsonPath("$[1].emailId").value("mk@gmail.com"));

    }
}