package com.tesinitsyn.crocdayonepractise.controller;

import com.tesinitsyn.crocdayonepractise.dto.EmployeeDto;
import com.tesinitsyn.crocdayonepractise.entity.Employee;
import com.tesinitsyn.crocdayonepractise.repository.EmployeeRepository;
import com.tesinitsyn.crocdayonepractise.utils.EmployeeMapper;
import com.tesinitsyn.crocdayonepractise.utils.EmployeeMapperImpl;
import com.tesinitsyn.crocdayonepractise.utils.SearchQueryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final EmployeeMapper employeeMapper;


    /**
     * To import EmployeeMapperImpl try to
     * 1. Maven reload
     * 2. Maven package
     * 3. Maven clean
     * and retry in every order if you can't import it :)
     * <p>
     * <p>
     * TODO: find @annotation for employeeMapper to delete @Autowired
     */
    @Autowired
    public SearchEmployeeController(SearchQueryProcessor queryProcessor, EmployeeRepository employeeRepository) {
        this.queryProcessor = queryProcessor;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = new EmployeeMapperImpl();
    }

    /**
     * to perform search:
     * use bruno or postman and send this: http://localhost:8080/employee?search=john.doe@example.com
     *
     * @param search
     * @return
     */
    @GetMapping()
    public ResponseEntity<EmployeeDto> search(@RequestParam(value = "search", required = false) String search) {
        var employee = (Employee) null;
        if (search != null) {
            var predicate = queryProcessor.processQuery(search);
            employee = employeeRepository.findOne(predicate).orElse(null);
        }
        if (Objects.isNull(employee)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeMapper.employeeToEmployeeDto(employee));
        }
    }
}
