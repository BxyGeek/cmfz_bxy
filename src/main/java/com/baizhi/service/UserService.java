package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.Map;

public interface UserService {
    Map<String, Object> selectAll(Integer page, Integer rows);

    void add(User user);

    Map<String, Object> selectByWeek();

    User login(String phone, String password, String code);

}
