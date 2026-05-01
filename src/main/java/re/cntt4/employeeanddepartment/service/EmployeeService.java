package re.cntt4.employeeanddepartment.service;

import org.springframework.stereotype.Service;
import re.cntt4.employeeanddepartment.model.Employee;

import java.util.List;


public interface EmployeeService {
    public List<Employee> getAllEmployee();
    void save(Employee employee);
}
