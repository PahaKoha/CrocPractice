package com.tesinitsyn.crocdayonepractise.entity;

import lombok.Getter;

/**
 * Должность.
 *
 * @author vkhlybov
 */
@Getter
public enum Position {

    /**
     * Генеральный директор.
     */
    CEO("Генеральный директор"),

    /**
     * Руководитель отдела.
     */
    HEAD_OF_DEPARTMENT("Руководитель отдела"),

    /**
     * Разработчик.
     */
    DEVELOPER("Разработчик"),

    /**
     * Менеджер проектов.
     */
    PROJECT_MANAGER("Менеджер проектов"),

    /**
     * Бухгалтер.
     */
    ACCOUNTANT("Бухгалтер"),

    /**
     * Юрист.
     */
    LAWYER("Юрист"),

    /**
     * HR-менеджер.
     */
    HR_MANAGER("HR-менеджер"),

    /**
     * Кадровик.
     */
    PERSONNEL_OFFICER("Кадровик");

    private final String russianName;

    Position(String russianName) {
        this.russianName = russianName;
    }
}