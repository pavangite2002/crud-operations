package com.crude.CrudOperation.service;

import com.crude.CrudOperation.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Optional<Employee> getEmployeeById(long employeeId);

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(long employeeId, Employee employee );

    public Employee deleteEmployee(long employeeId);

    public List<Employee> saveEmployees(List<Employee> employees);

}
