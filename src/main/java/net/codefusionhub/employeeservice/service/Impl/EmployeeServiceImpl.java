package net.codefusionhub.employeeservice.service.Impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.codefusionhub.employeeservice.dto.APIResponseDto;
import net.codefusionhub.employeeservice.dto.DepartmentDto;
import net.codefusionhub.employeeservice.dto.EmployeeDto;
import net.codefusionhub.employeeservice.entity.Employee;
import net.codefusionhub.employeeservice.mapper.EmployeeMapper;
import net.codefusionhub.employeeservice.repository.EmployeeRepository;
import net.codefusionhub.employeeservice.service.APIClient;
import net.codefusionhub.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployeeDto(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployee(savedEmployee);
    }

    @Override
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployee(Long employeeId) {

        LOGGER.info("inside getEmployee() method");
        Optional<Employee> employeeObj = employeeRepository.findById(employeeId);
        Employee employee = employeeObj.get();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" +employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        ResponseEntity<DepartmentDto>  responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"
//                        +employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployee(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("inside getDefaultDepartment() method");
        Optional<Employee> employeeObj = employeeRepository.findById(employeeId);
        Employee employee = employeeObj.get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployee(employee);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
