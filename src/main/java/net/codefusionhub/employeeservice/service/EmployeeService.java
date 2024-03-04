package net.codefusionhub.employeeservice.service;


import net.codefusionhub.employeeservice.Dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Long employeeId);
}
