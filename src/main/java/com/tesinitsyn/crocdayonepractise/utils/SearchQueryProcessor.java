package com.tesinitsyn.crocdayonepractise.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.tesinitsyn.crocdayonepractise.entity.Employee;
import com.tesinitsyn.crocdayonepractise.entity.SearchCriteria;
import com.tesinitsyn.crocdayonepractise.entity.SearchOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

@Component
public class SearchQueryProcessor {

    private static final String FULL_NAME_PATTERN = "(^[А-Яа-я]{3,16})([ ]{0,1})([А-Яа-я]{3,16})?([ ]{0,1})?([А-Яа-я]{3,16})?([ ]{0,1})?([А-Яа-я]{3,16})";

    private static final String PHONE_PATTERN = "(^8|7|\\+7)((\\d{10})|[\\s-][(]\\d{3}[)][\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2})";

    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


    public BooleanExpression processQuery(String query) {
        var attribute = identifySearchAttribute(query);
        return switch (attribute) {
            case Employee.FULL_NAME_FIELD -> {
                var criteriaList = new ArrayList<SearchCriteria>();
                Arrays.stream(query.split("\\s"))
                        .forEach(str -> {
                            criteriaList.add(
                                    new SearchCriteria(
                                            Employee.Fields.firstName,
                                            SearchOperation.CONTAINS.getOperation(),
                                            str.toLowerCase()));
                            criteriaList.add(
                                    new SearchCriteria(
                                            Employee.Fields.lastName,
                                            SearchOperation.CONTAINS.getOperation(),
                                            str.toLowerCase()));
                            criteriaList.add(
                                    new SearchCriteria(
                                            Employee.Fields.firstName,
                                            SearchOperation.CONTAINS.getOperation(),
                                            str.toLowerCase()));
                        });
                var predicates = criteriaList
                        .stream()
                        .map(criteria -> new EmployeePredicate(criteria)
                                .getPredicate())
                        .toList();

                var result = Expressions.FALSE.isTrue();
                for (var predicate : predicates) {
                    result = result.or(predicate);
                }
                yield result;
            }
            case Employee.Fields.email -> new EmployeePredicate(
                    new SearchCriteria(
                            attribute,
                            SearchOperation.EQUALS.getOperation(),
                            query.toLowerCase()))
                    .getPredicate();
            case Employee.Fields.phone -> {
                // выполняем нормализацию номера телефона к формату 7CCCXXXXXXX
                var phone = query
                        .replaceAll("\\s", "")
                        .replaceAll("[+-]", "")
                        .replaceAll("[()]", "");
                if (phone.startsWith("8")) {
                    phone = "7".concat(phone.substring(1));
                }
                yield new EmployeePredicate(
                        new SearchCriteria(
                                attribute,
                                SearchOperation.EQUALS.getOperation(),
                                phone))
                        .getPredicate();
            }
            default -> null;
        };
    }

    public String identifySearchAttribute(String query) {
        switch (query) {
            case String s
                    when Pattern.compile(FULL_NAME_PATTERN).matcher(s).matches() -> {
                return Employee.FULL_NAME_FIELD;
            }
            case String s
                    when Pattern.compile(PHONE_PATTERN).matcher(s).matches() -> {
                return Employee.Fields.phone;
            }
            case String s
                    when Pattern.compile(EMAIL_PATTERN).matcher(s).matches() -> {
                return Employee.Fields.email;
            }
            default -> throw new IllegalArgumentException("Incorrect query format");
        }
    }
}
