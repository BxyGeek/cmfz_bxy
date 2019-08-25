package com.baizhi.debug;

import java.util.HashMap;
import java.util.Map;

public class TestDeBug {
    public static void main(String[] args) {
        //进入断点  F6 下一步
        int a = 10;
        HashMap<String, Integer> map = new HashMap<>();
        //variables  变量选项卡     监测变量
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 4);

        if (a > 5) {
            Cart cart = new Cart();
            //进入项目源码  F5
            cart.driver();
            //进入底层代码  ， jar  源码  依赖源码   Alt + Shift + F7
            System.out.println("OK");
        }

        //debug过程当中修改变量值
        if (a < 5) {
            System.out.println("NO");
        }

        //if 条件断点
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.equals("b")) {
                System.out.println(key + "----" + entry.getValue());
                break;
            }
        }

        //异常    自动断点
        map = null;
        System.out.println(map.size());

        System.out.println("测试结束啦");
    }

}

class Cart {

    public void driver() {
        System.out.println("我是Cart对象");

        System.out.println("我是Cart对象");

        System.out.println("我是Cart对象");
    }
}
