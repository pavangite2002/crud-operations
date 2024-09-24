package com.crude.CrudOperation.controller;

import com.crude.CrudOperation.model.Employee;
import com.crude.CrudOperation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> emps = employeeService.getAllEmployees();
        if (emps.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(emps, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        if (emp.isPresent()) {
            return new ResponseEntity<>(emp.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }
    @PostMapping("/list")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
        List<Employee> createdEmployees = employeeService.saveEmployees(employees);
        return new ResponseEntity<>(createdEmployees, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateemployee(@PathVariable long id, @RequestBody Employee employee) {
        try {
            Employee emp = employeeService.updateEmployee(id, employee);
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
        try {
            Employee deletedEmployee = employeeService.deleteEmployee(id);
            return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
