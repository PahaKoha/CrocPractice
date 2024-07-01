package com.tesinitsyn.crocdayonepractise.utils;

import com.tesinitsyn.crocdayonepractise.dto.EmployeeDto;
import com.tesinitsyn.crocdayonepractise.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface EmployeeMapper {

    @Mapping(target = "position", expression = "java(employee.getPosition().getRussianName())")
    @Mapping(target = "department", expression = "java(employee.getDepartment().getName())")
    EmployeeDto employeeToEmployeeDto(Employee employee);
}
