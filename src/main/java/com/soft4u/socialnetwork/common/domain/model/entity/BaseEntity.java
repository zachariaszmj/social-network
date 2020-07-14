package com.soft4u.socialnetwork.common.domain.model.entity;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "uuid")
public abstract class BaseEntity {

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();
}
