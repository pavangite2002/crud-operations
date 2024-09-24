package com.crude.CrudOperation.service;

import com.crude.CrudOperation.model.Employee;
import com.crude.CrudOperation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Optional<Employee> getEmployeeById(long employeeId) {
        return  employeeRepository.findById(employeeId); // Optional.empty();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public Employee updateEmployee(long employeeId, Employee employee) {
        Optional<Employee> oldEmpOpt = employeeRepository.findById(employeeId);
        if (oldEmpOpt.isPresent()) {
            Employee oldEmp = oldEmpOpt.get();
            employee.setFirstName(oldEmp.getFirstName());
            employee.setLastname(oldEmp.getLastname());
            oldEmp.setDepartment(employee.getDepartment());
            oldEmp.setCity(employee.getCity());
            oldEmp.setState(employee.getState());
            oldEmp.setEmail(employee.getEmail());
            oldEmp.setPhone(employee.getPhone());
            return employeeRepository.save(oldEmp);
        }
        else {
            throw new RuntimeException("Employee Not Found with given Id : "+ employeeId);
        }
    }
    @Override
    public Employee deleteEmployee(long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employeeRepository.deleteById(employeeId);
            return employee;
        } else {
            throw new RuntimeException("Employee not found");
        }
    }


}
