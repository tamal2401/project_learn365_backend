package org.learn365.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_At", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_At", updatable = true, nullable = false)
    private LocalDateTime updatedAt;
}
