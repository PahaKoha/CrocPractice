package com.tesinitsyn.crocdayonepractise.controller;

import com.tesinitsyn.crocdayonepractise.entity.Employee;
import com.tesinitsyn.crocdayonepractise.repository.EmployeeRepository;
import com.tesinitsyn.crocdayonepractise.utils.SearchQueryProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("employee")
public class SearchEmployeeController {

    private final SearchQueryProcessor queryProcessor;

    private final EmployeeRepository employeeRepository;

    public SearchEmployeeController(SearchQueryProcessor queryProcessor, EmployeeRepository employeeRepository) {
        this.queryProcessor = queryProcessor;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<Employee> search(@RequestParam(value = "search", required = false) String search) {
        var employee = (Employee) null;
        if (search != null) {
            var predicate = queryProcessor.processQuery(search);
            employee = employeeRepository.findOne(predicate).orElse(null);
        }
        if (Objects.isNull(employee)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employee);
        }
    }
}
