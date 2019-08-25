package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.util.Md5UUIDSaltUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        User user = new User();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDAO.selectByRowBounds(user, rowBounds);
        int count = userDAO.selectCount(user);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", users);
        map.put("total", count / rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public void add(User user) {
        user.setCreateDate(new Date());
        user.setGuruId("123123");
        user.setSalt(Md5UUIDSaltUtil.getSalt());
        user.setStatus(1);
        user.setSign("默认签名");
        user.setPhoto("默认图片.jpg");
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        userDAO.insertSelective(user);
    }

    @Override
    public Map<String, Object> selectByWeek() {
        Map<String, Object> map = new HashMap<>();
        Integer week1 = userDAO.selectByWeek(7);
        Integer week2 = userDAO.selectByWeek(21);
        Integer week3 = userDAO.selectByWeek(28);
        map.put("week1", week1);
        map.put("week2", week2);
        map.put("week3", week3);
        return map;
    }

    @Override
    public User login(String phone, String password, String code) {
        if (!"code".equals(code)) {
            throw new RuntimeException("验证码错误");
        }
        User user = new User();
        user.setPhone(phone);
        User selectOne = userDAO.selectOne(user);
        if (selectOne == null) throw new RuntimeException("用户不存在");
        if (!password.equals(selectOne.getPassword())) throw new RuntimeException("密码错误");
        return selectOne;
    }

}
