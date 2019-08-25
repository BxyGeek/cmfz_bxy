package com.baizhi.controller;

import com.baizhi.util.CreateValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("code")
public class SecurityCode {
    @RequestMapping("getCode")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        CreateValidateCode createValidateCode = new CreateValidateCode();
        //获取验证码
        String code = createValidateCode.getString();
        session.setAttribute("code", code);
        log.info("验证码为:  " + code);
        BufferedImage image = createValidateCode.getImage();
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }
}
