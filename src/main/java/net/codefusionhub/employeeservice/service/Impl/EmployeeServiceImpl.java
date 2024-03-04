package net.codefusionhub.employeeservice.service.Impl;

import lombok.AllArgsConstructor;
import net.codefusionhub.employeeservice.Dto.EmployeeDto;
import net.codefusionhub.employeeservice.entity.Employee;
import net.codefusionhub.employeeservice.repository.EmployeeRepository;
import net.codefusionhub.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );
    }

    @Override
    public EmployeeDto getEmployee(Long employeeId) {
        Optional<Employee> employeeObj = employeeRepository.findById(employeeId);
        Employee employee = employeeObj.get();

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
}
