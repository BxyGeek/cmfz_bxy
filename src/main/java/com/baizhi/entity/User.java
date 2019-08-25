package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_user")

public class User implements Serializable {

    @Id
    private String id;

    private String phone;

    private String password;

    private String salt;

    private String photo;

    private String dharma;

    private String username;

    private String sex;

    private String province;

    private String city;

    private String sign;

    private String guruId;

    private Integer status;

    private Date createDate;
}