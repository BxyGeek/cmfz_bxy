package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_admin")
public class Admin {
    @Id
    private String id;

    private String username;

    @Column(name = "password")
    private String pass;
}
