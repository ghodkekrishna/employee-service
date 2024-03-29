package net.codefusionhub.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {
    DepartmentDto department;
    EmployeeDto employee;
    OrganizationDto organization;
}
