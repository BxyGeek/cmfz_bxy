package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("selectAll")
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        try {
            Map<String, Object> map = userService.selectAll(page, rows);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("code", 500);
            map.put("msg", "查询所有用户失败");
            return map;
        }
    }

    @RequestMapping("edit")
    public void edit(User user, String oper) {
        if ("add".equals(oper)) {
            userService.add(user);
        }
    }

    @RequestMapping("selectByWeek")
    public Map<String, Object> selectByWeek() {
        Map<String, Object> weeks = userService.selectByWeek();
        return weeks;
    }

    @RequestMapping("add")
    public void add(User user) {
        userService.add(user);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-80db1671f5804e0d8dcef8c3c070a396");
        goEasy.publish("buxy", "用户注册啦!");
    }

}
