package net.codefusionhub.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.codefusionhub.employeeservice.Dto.EmployeeDto;
import net.codefusionhub.employeeservice.entity.Employee;
import net.codefusionhub.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{employee-id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employee-id") Long employeeId){
       EmployeeDto employeeDto = employeeService.getEmployee(employeeId);

       return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
