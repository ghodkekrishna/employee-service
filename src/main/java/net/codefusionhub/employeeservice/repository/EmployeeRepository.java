package net.codefusionhub.employeeservice.repository;

import net.codefusionhub.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
