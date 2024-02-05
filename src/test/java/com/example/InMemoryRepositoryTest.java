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

  @Test
  @DisplayName("When initializing a repository with a list of employees findAll returns the same amount as the list")
  void whenInitializingARepositoryWithAListOfEmployeesFindAllReturnsTheSameAmountAsTheList() {
      assertThat(inMemoryRepository.getEmployees()).hasSize(3);
  }

  @Test
  @DisplayName("When saving an employee the amount of employees increases")
  void whenSavingAnEmployeeTheAmountOfEmployeesIncreases() {
      inMemoryRepository.save(new Employee("Don Corleone", 1000000));

      assertThat(inMemoryRepository.findAll()).hasSize(4);
  }

  @Test
  @DisplayName("When saving an employee with the same id it replaces the existing employee")
  void whenSavingAnEmployeeWithTheSameIdItReplacesTheExistingEmployee() {

      inMemoryRepository.save(new Employee("4", 4000));
      inMemoryRepository.save(new Employee("4", 1000000)); //adding a duplicate id

      var newEmployee = employees.stream().filter(employee -> employee.getId().equals("4"));
      assertThat(newEmployee.findFirst().get().getSalary()).isNotEqualTo(4000);
  }
}