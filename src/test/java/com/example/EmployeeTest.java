package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    @DisplayName("When creating an employee it gets the correct name")
    void whenCreatingAnEmployeeItGetsTheCorrectName() {
        Employee employee = new Employee("Donald Trump", 1000000);

        assertThat(employee.getId()).isEqualTo("Donald Trump");
    }

    @Test
    @DisplayName("When creating an employee it gets the correct salary")
    void whenCreatingAnEmployeeItGetsTheCorrectSalary() {
        Employee employee = new Employee("Homeless Guy", 10);

        assertThat(employee.getSalary()).isEqualTo(10);
    }
}