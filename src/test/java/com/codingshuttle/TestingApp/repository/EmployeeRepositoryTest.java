package com.codingshuttle.TestingApp.repository;

import com.codingshuttle.TestingApp.Configuration.TestContainerConfiguration;
import com.codingshuttle.TestingApp.entities.Employee;
import com.codingshuttle.TestingApp.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Import(TestContainerConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp(){
        employee = Employee.builder()
                .id(1L)
                .name("Yogesh")
                .email("yogesh@gmail.com")
                .salary(600L)
                .build();
    }

    @Test
    void testFindByEmail_WhereEmailIsPresent_ThenReturnEmployee(){

        employeeRepository.save(employee);

        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_WhenEmailIsNotFound_ThenReturnEmptyEmpList(){

        String email = "sample@Gmail.com";

        List<Employee> employeeList = employeeRepository.findByEmail(email);

        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList).isEmpty();
    }
}
