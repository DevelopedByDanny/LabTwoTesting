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
import static org.mockito.Mockito.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Donald Trump", 1000000);
    }

    @Test
    @DisplayName("When creating an employee it gets the correct name")
    void whenCreatingAnEmployeeItGetsTheCorrectName() {

        assertThat(employee.getId()).isEqualTo("Donald Trump");
    }

    @Test
    @DisplayName("When creating an employee it gets the correct salary")
    void whenCreatingAnEmployeeItGetsTheCorrectSalary() {

        assertThat(employee.getSalary()).isEqualTo(1000000);
    }

    @Test
    @DisplayName("Name changing when setting a new name")
    void nameChangingWhenSettingANewName() {
        employee.setId("Not Trump");

        assertThat(employee.getId()).isEqualTo("Not Trump");
    }

    @Test
    @DisplayName("Salary changing when setting new salary")
    void salaryChangingWhenSettingNewSalary() {
        employee.setSalary(2000000);

        assertThat(employee.getSalary()).isEqualTo(2000000);
    }

    @Test
    @DisplayName("When creating employee paid is false")
    void whenCreatingEmployeePaidIsFalse() {

        assertThat(employee.isPaid()).isFalse();

    }

    @Test
    @DisplayName("When employee is paid paid is true")
    void whenEmployeeIsPaidPaidIsTrue() {

        employee.setPaid(true);

        assertThat(employee.isPaid()).isTrue();
    }

    @Test
    @DisplayName("When calling toString it prints the correct string")
    void whenCallingToStringItPrintsTheCorrectString() {

        assertThat(employee.toString()).hasToString("Employee [id=Donald Trump, salary=1000000.0]");

    }

    @ExtendWith(MockitoExtension.class)
    static
    class EmployeesTestMockito {


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
            when(employeeRepositoryMockito.findAll()).thenReturn(populateEmployees());
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
}