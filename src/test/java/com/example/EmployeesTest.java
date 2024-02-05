package com.example;

import com.example.testDoubles.BankServiceSpy;
import com.example.testDoubles.EmployeeRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        var payedEmployees = employees.payEmployees();

        assertThat(payedEmployees).isEqualTo(3);
    }

    @Test
    @DisplayName("When Employees is payed the bankService pay method is called the correct number of times")
    void whenEmployeesIsPayedTheBankServicePayMethodIsCalledTheCorrectNumberOfTimes() {
        employees.payEmployees();

        assertThat(bankService.numOfPayments).isEqualTo(3);
    }

    @Test
    @DisplayName("When payments fail no one should be payed")
    void whenPaymentsFailNoOneShouldBePayed() {

        bankService.payFailure = true; // Setup failure i bankService
        var payedEmployees = employees.payEmployees();

        assertThat(payedEmployees).isZero();
    }
}