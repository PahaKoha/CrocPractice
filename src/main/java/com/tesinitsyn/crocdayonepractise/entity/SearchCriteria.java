package com.tesinitsyn.crocdayonepractise.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SearchCriteria {

    /**
     * Поисковый атрибут.
     */
    private final String key;

    /**
     * Операция сравнения.
     */
    private final String operation;

    /**
     * Значение поиска.
     */
    private final Object value;
}
