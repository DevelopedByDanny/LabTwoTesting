package com.example;

import com.example.testDoubles.BankServiceSpy;
import com.example.testDoubles.EmployeeRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeesTest {


    private Employees employees;
    private EmployeeRepositoryStub employeeRepository;
    private BankServiceSpy bankService;

    @Mock
    private EmployeeRepository employeeRepositoryMockito;

    @Mock
    private BankService bankServiceMockito;

    @InjectMocks
    private Employees employeesMockito;


    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepositoryStub();
        bankService = new BankServiceSpy();
        employees = new Employees(employeeRepository, bankService);


        //For Mockito
        //Used lenient method to prevent unnecessary stubbing exception
        lenient().when(employeeRepositoryMockito.findAll()).thenReturn(populateEmployees());
    }

    private static ArrayList<Employee> populateEmployees() {
        var employees = new ArrayList<Employee>();
        employees.add(new Employee("Anders Andersson", 1000));
        employees.add(new Employee("Bengt Bengtsson", 2000));
        employees.add(new Employee("Ceasar Ceasarsson", 3000));
        return employees;
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

    @Test
    @DisplayName("Verify that findAll method is called")
    void verifyThatFindAllMethodIsCalled() {

        var payments = employeesMockito.payEmployees();

        verify(employeeRepositoryMockito).findAll();
    }

    @Test
    @DisplayName("When employees is payed number of payments is correct with mockito")
    void whenEmployeesIsPayedNumberOfPaymentsIsCorrectWithMockito() {

        var payments = employeesMockito.payEmployees();

        assertThat(payments).isEqualTo(3);
    }
    

    @Test
    @DisplayName("When payments fail the payments is zero with mockito")
    void whenPaymentsFailThePaymentsIsZeroWithMockito() {
        doThrow(new RuntimeException()).when(bankServiceMockito).pay(anyString(), anyDouble());

        var payments = employeesMockito.payEmployees();

        assertThat(payments).isZero();
    }
}