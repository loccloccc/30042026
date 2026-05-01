package re.cntt4.employeeanddepartment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.cntt4.employeeanddepartment.model.Department;
import re.cntt4.employeeanddepartment.repository.DepartmentRepository;
import re.cntt4.employeeanddepartment.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceIMPL implements DepartmentService {
    public final DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
}
