package com.example;

import com.example.testDoubles.BankServiceSpy;
import com.example.testDoubles.EmployeeRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeesTest {


    private Employees employees;
    private EmployeeRepositoryStub employeeRepository;
    private BankServiceSpy bankService;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepositoryStub();
        bankService = new BankServiceSpy();
        employees = new Employees(employeeRepository, bankService);

    }

    @Test
    @DisplayName("When Employees is payed with a list of three the count should be three")
    void whenEmployeesIsPayedWithAListOfThreeTheCountShouldBeThree() {
        assertThat(employees.payEmployees()).isEqualTo(3);
    }
}