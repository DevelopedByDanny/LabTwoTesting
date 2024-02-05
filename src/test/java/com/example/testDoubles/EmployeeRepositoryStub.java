package com.example.testDoubles;

import com.example.Employee;
import com.example.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryStub implements EmployeeRepository {

    @Override
    public List<Employee> findAll() {
        return List.of(
                new Employee("Anders Andersson",1000),
                new Employee("Bengt Bengtsson", 2000),
                new Employee("Ceasar Ceasarsson", 3000)
        );
    }

    @Override
    public Employee save(Employee e) {
        return null;
    }
}
