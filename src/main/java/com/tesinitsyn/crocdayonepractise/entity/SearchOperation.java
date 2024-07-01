package com.tesinitsyn.crocdayonepractise.entity;


import lombok.Getter;

@Getter
public enum SearchOperation {
    /**
     * Равенство.
     */
    EQUALS("="),

    /**
     * Вхождение в (для строковых значений).
     */
    CONTAINS("LIKE");

    private String operation;

    private SearchOperation(String operation) {
        this.operation = operation;
    }

}
