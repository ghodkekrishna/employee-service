package net.codefusionhub.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.codefusionhub.employeeservice.dto.APIResponseDto;
import net.codefusionhub.employeeservice.dto.EmployeeDto;
import net.codefusionhub.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@Tag(
        name = "Employee Service - Employee Controller",
        description = "Employee Controller Exposes REST APIs for Employee-Service"
)
public class EmployeeController {
    private EmployeeService employeeService;
    
    @PostMapping
    @Operation(
            summary = "Save Employee REST API",
            description = "Save employee REST API is used to save employee object in database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{employee-id}")
    @Operation(
            summary = "Get Employee REST API",
            description = "Get employee REST API is used to get employee details in database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("employee-id") Long employeeId){
        APIResponseDto employeeDto = employeeService.getEmployee(employeeId);

       return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
