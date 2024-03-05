package net.codefusionhub.employeeservice.service;


import net.codefusionhub.employeeservice.dto.APIResponseDto;
import net.codefusionhub.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployee(Long employeeId);
}
