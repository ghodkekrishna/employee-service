package net.codefusionhub.employeeservice.service.Impl;

import lombok.AllArgsConstructor;
import net.codefusionhub.employeeservice.dto.APIResponseDto;
import net.codefusionhub.employeeservice.dto.DepartmentDto;
import net.codefusionhub.employeeservice.dto.EmployeeDto;
import net.codefusionhub.employeeservice.entity.Employee;
import net.codefusionhub.employeeservice.repository.EmployeeRepository;
import net.codefusionhub.employeeservice.service.APIClient;
import net.codefusionhub.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
    }

    @Override
    public APIResponseDto getEmployee(Long employeeId) {
        Optional<Employee> employeeObj = employeeRepository.findById(employeeId);
        Employee employee = employeeObj.get();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" +employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

//        ResponseEntity<DepartmentDto>  responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"
//                        +employee.getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        EmployeeDto employeeDto =  new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
