package com.baizhi.service;

import com.baizhi.api.BaseApiService;
import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseApiService implements AdminService {

    @Autowired
    private AdminDAO adminDAO;


    @Override
    public Map<String, Object> login(String adminName, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Admin admin = new Admin();
        admin.setUsername(adminName);
        Admin loginAdmin = adminDAO.selectOne(admin);
        if (loginAdmin == null) throw new RuntimeException("用户不存在");
        if (!password.equals(loginAdmin.getPass())) throw new RuntimeException("密码错误");
        /*map.put("code",200);
        //404     400    500    405    302
        map.put("msg","登录成功");*/
        session.setAttribute("adminName", adminName);
        return setResultSuccessMsg("登录成功");
        //return map;
    }
}
