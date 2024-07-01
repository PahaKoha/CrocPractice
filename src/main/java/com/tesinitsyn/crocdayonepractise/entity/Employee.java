package com.tesinitsyn.crocdayonepractise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity
@Getter
@Setter
@FieldNameConstants
public class Employee {

    public static final String FULL_NAME_FIELD = "fullName";

    /**
     * Идентификатор.
     */
    @Id
    @UuidGenerator
    private UUID id;

    /**
     * Фамилия.
     */
    @Column(nullable = false, length = 32)
    private String lastName;

    /**
     * Имя.
     */
    @Column(nullable = false, length = 32)
    private String firstName;

    /**
     * Отчество.
     */
    @Column(length = 32)
    private String patronymic;

    /**
     * Номер мобильного телефона (79991234567).
     */
    @Column(nullable = false, length = 11)
    private String phone;

    /**
     * Email.
     */
    @Column(length = 32)
    private String email;

    /**
     * Идентификатор в Telegram.
     */
    private String tgId;

    /**
     * Отдел.
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * Должность.
     */
    @Enumerated(EnumType.STRING)
    private Position position;

    /**
     * Непосредственный руководитель.
     */
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
}
