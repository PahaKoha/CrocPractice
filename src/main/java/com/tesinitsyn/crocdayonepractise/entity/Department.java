package com.tesinitsyn.crocdayonepractise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Department {

    /**
     * Идентификатор.
     */
    @Id
    @UuidGenerator
    private UUID id;

    /**
     * Наименование.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Вышестоящий отдел.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "higher_department_id")
    private Department higherDepartment;
}
