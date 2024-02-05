package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryRepositoryTest {

    private InMemoryRepository inMemoryRepository;
    private List<Employee> employees;
    public static List<Employee> populateRepo() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("1", 1000));
        employees.add(new Employee("2", 2000));
        employees.add(new Employee("3", 3000));

        return employees;
    }
    @BeforeEach
    void setUp(){
        employees = populateRepo();
        inMemoryRepository = new InMemoryRepository(employees);
    }

  @Test
  @DisplayName("When creating repository with a list of employees it is not empty")
  void whenCreatingRepositoryWithAListOfEmployeesItIsNotEmpty() {

      assertThat(inMemoryRepository.getEmployees()).isNotEmpty();
  }

  @Test
  @DisplayName("When creating a new repository it is empty")
  void whenCreatingANewRepositoryItIsEmpty() {
      var newRepo = new InMemoryRepository();

      assertThat(newRepo.getEmployees()).isEmpty();
  }


}