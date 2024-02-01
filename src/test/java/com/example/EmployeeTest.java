package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}