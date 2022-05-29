package hu.nye.progkorny.emsys.service.impl;

import hu.nye.progkorny.emsys.model.Employee;
import hu.nye.progkorny.emsys.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @Rollback(false)
    public void AsaveEmployeeTest(){

        Employee employee = Employee.builder()
                .firstName("Bence")
                .lastName("Szuhár")
                .email("benceszuhar@gmail.com")
                .build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);

    }

    @Test
    public void BgetEmployeeByIdTest(){

        Employee employee = employeeRepository.findById(1L).get();

        Assertions.assertThat(employee.getId()).isEqualTo(1L);

    }

    @Test
    public void CgetAllEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }


    @Test
    @Rollback(value = false)
    public void DupdateEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        employee.setEmail("bence@gmail.com");

        Employee employeeUpdated =  employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("bence@gmail.com");

    }


}