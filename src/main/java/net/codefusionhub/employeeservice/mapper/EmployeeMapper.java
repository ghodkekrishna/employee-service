package net.codefusionhub.employeeservice.mapper;

import net.codefusionhub.employeeservice.dto.EmployeeDto;
import net.codefusionhub.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployee(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );
    }

    public static Employee mapToEmployeeDto(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
    }
}
