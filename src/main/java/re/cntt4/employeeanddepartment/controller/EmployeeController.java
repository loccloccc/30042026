package re.cntt4.employeeanddepartment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import re.cntt4.employeeanddepartment.dto.EmployeeDTO;
import re.cntt4.employeeanddepartment.model.Department;
import re.cntt4.employeeanddepartment.model.Employee;
import re.cntt4.employeeanddepartment.repository.EmployeeRepositor;
import re.cntt4.employeeanddepartment.service.impl.DepartmentServiceIMPL;
import re.cntt4.employeeanddepartment.service.impl.EmployeeServiceIMPL;
import re.cntt4.employeeanddepartment.service.uploadFile.UploadService;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceIMPL employeeServiceIMPL;
    private final DepartmentServiceIMPL departmentServiceIMPL;
    private final UploadService uploadService;

    @GetMapping("/employees")
    public String homeEmployees(Model model){
        model.addAttribute("employees",employeeServiceIMPL.getAllEmployee());
        return "home-employee";
    }

    @GetMapping("/add")
    public String addEmployee(Model model){
        model.addAttribute("employeeDTO",new EmployeeDTO());
        model.addAttribute("department",departmentServiceIMPL.getAllDepartment());
        return "add-employee";
    }

    @PostMapping("/handleAdd")
    public String handleAdd(
            @Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
            BindingResult br,
            Model model
    ){
        if (br.hasErrors()){
            model.addAttribute("department", departmentServiceIMPL.getAllDepartment());
            return "add-employee";
        }


        String url = null;
        if (employeeDTO.getAvatar() != null && !employeeDTO.getAvatar().isEmpty()) {
            url = uploadService.uploadFile(employeeDTO.getAvatar());
        }


        Department dept = departmentServiceIMPL
                .findById(employeeDTO.getDepartmentId());


        Employee newEmployee = Employee.builder()
                .name(employeeDTO.getName())
                .age(employeeDTO.getAge())
                .avatar(url)
                .status(employeeDTO.getStatus())
                .department(dept)
                .build();


        employeeServiceIMPL.save(newEmployee);

        return "redirect:/employees";
    }

}
