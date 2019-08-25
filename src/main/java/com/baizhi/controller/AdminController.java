package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseApiService {
    @Autowired
    private AdminService adminService;


    //这个是同事的提交
    //这个是我做的修改
    //这是同事第二次提交





    @RequestMapping("login")
    public Map<String, Object> login(String adminName, String password, String enCode, HttpSession session) {
        String code = (String) session.getAttribute("code");
        if (!code.equals(enCode)) {
            return setResultErrorMsg("验证码错误");
        }
        try {
            return adminService.login(adminName, password, session);
        } catch (Exception e) {
            return setResultErrorMsg(e.getMessage());

        }
    }

}
