package com.example;

import java.util.List;

public class InMemoryRepository implements EmployeeRepository {
    private final List<Employee> employees;

    public InMemoryRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee save(Employee newEmployee) {
        employees.removeIf(employee -> employee.getId().equals(newEmployee.getId()));
        employees.add(newEmployee);
        return newEmployee;
    }
}
