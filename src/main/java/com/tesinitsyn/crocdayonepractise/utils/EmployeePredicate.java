package com.tesinitsyn.crocdayonepractise.utils;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.tesinitsyn.crocdayonepractise.entity.Employee;
import com.tesinitsyn.crocdayonepractise.entity.SearchCriteria;
import com.tesinitsyn.crocdayonepractise.entity.SearchOperation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EmployeePredicate {

    private final SearchCriteria searchCriteria;

    public BooleanExpression getPredicate() {
        var entityPath = new PathBuilder<>(Employee.class, "employee");

        // в зависимости от типа искомого значения (числовой, строковый, etc)
        // формируем условие с доступным перечнем операций
        switch (searchCriteria.getValue()) {
            case String value -> {
                var path = entityPath.getString(searchCriteria.getKey());
                if (searchCriteria.getOperation()
                        .equalsIgnoreCase(
                                SearchOperation.EQUALS.getOperation())) {
                    return path.equalsIgnoreCase(value);
                } else if (searchCriteria.getOperation()
                        .equalsIgnoreCase(
                                SearchOperation.CONTAINS.getOperation())) {
                    return path.containsIgnoreCase(value);
                }
            }
            default -> throw new IllegalArgumentException("Данный тип не поддерживается");
        }
        return null;
    }
}
