package re.cntt4.employeeanddepartment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.cntt4.employeeanddepartment.repository.EmployeeRepositor;
import re.cntt4.employeeanddepartment.service.impl.EmployeeServiceIMPL;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceIMPL employeeServiceIMPL;

    @GetMapping("/employees")
    public String homeEmployees(Model model){
        model.addAttribute("employees",employeeServiceIMPL.getAllEmployee());
        return "home-employee";
    }




}
