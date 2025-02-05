package com.pragma.user.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;


}