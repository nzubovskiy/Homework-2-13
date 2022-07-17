package com.example.homework213;

import com.example.homework213.exceptions.EmployeeNotFoundException;
import com.example.homework213.model.Employee;
import com.example.homework213.service.DepartmentService;
import com.example.homework213.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee ("Кира", "Левина", 2, 32_000),
                new Employee ("Виктория", "Александрова",2, 37_000),
                new Employee ("Мария", "Кравцова", 2, 34_000),
                new Employee ("Тимур", "Громов", 1, 37_000),
                new Employee ("Даниил", "Андреев", 1, 34_000),
                new Employee ("Тимофей", "Капустин",  1, 38_000)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("maxSalaryParams")
    public void maxSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.maxSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void maxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.maxSalary(3));
    }

    @ParameterizedTest
    @MethodSource("minSalaryParams")
    public void minSalaryPositiveTest(int departmentId, Employee expected) {
        assertThat(departmentService.minSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void minSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.minSalary(3));
    }

    @ParameterizedTest
    @MethodSource("employeesByDepartmentParams")
    public void employeesByDepartmentPositiveTest(int departmentId, List<Employee> expected) {
        assertThat(departmentService.getEmployeeByDepartment(departmentId)).containsExactlyElementsOf(expected);
    }

    @ParameterizedTest
    @MethodSource("employeesByDepartmentParams")
    public void employeesByDepartmentNegativeTest(int departmentId, List<Employee> expected) {
        assertThat(departmentService.getEmployeeByDepartment(3)).isEmpty();
    }

    public static Stream<Arguments> maxSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Тимофей", "Капустин",  1, 38_000)),
                Arguments.of(2, new Employee("Виктория", "Александрова",2, 37_000))
        );
    }

    public static Stream<Arguments> minSalaryParams() {
        return Stream.of(
            Arguments.of(1, new Employee("Даниил", "Андреев", 1, 34_000)),
            Arguments.of(2, new Employee("Кира", "Левина", 2, 32_000))
        );
    }

    public static Stream<Arguments> employeesByDepartmentParams() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Тимур", "Громов", 1, 37_000),
                                new Employee("Даниил", "Андреев", 1, 34_000),
                                new Employee("Тимофей", "Капустин", 1, 38_000)),
                        Arguments.of(2, List.of(new Employee("Кира", "Левина", 2, 32_000),
                                new Employee("Виктория", "Александрова", 2, 37_000),
                                new Employee("Мария", "Кравцова", 2, 34_000)
                        ))));
    }

    @Test
    public void allEmployeesGroupByDepartmentTest() {
        assertThat(departmentService.allEmployee()).containsAllEntriesOf(
                Map.of(1, List.of(new Employee("Тимур", "Громов", 1, 37_000),
                                new Employee("Даниил", "Андреев", 1, 34_000),
                                new Employee("Тимофей", "Капустин", 1, 38_000)),
                        2, List.of(new Employee("Кира", "Левина", 2, 32_000),
                                new Employee("Виктория", "Александрова", 2, 37_000),
                                new Employee("Мария", "Кравцова", 2, 34_000))
                ));
    }
}
