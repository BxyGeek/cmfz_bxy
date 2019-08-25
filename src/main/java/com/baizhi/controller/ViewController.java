package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("account")
public class ViewController {

    @Autowired
    private UserService userService;


    @RequestMapping("login")
    public Map<String, Object> selectFirstPage(String phone, String password, String code) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.login(phone, password, code);
            map.put("code", 200);
            map.put("data", user);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-200");
            map.put("errmsg", e.getMessage());
            return map;
        }
    }


}
