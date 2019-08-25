package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("selectAll")
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public void edit(String oper, Article article) {
        System.out.println("oper:     " + oper);
        try {
            if ("add".equals(oper)) {
                articleService.add(article);
            }
            if ("edit".equals(oper)) {
                articleService.edit(article);
            }
            if ("del".equals(oper)) {
                articleService.del(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("browser")
    public Map<String, Object> browser(HttpServletRequest request) {
        //获取当前项目图片文件夹的路径
        File file = new File(request.getSession().getServletContext().getRealPath("image"));
        File[] files = file.listFiles();
        //获取当前文件夹图片数量  files.length

        //http://localhost:8989/cmfz/image/
        String current_url = "http://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath() + "/image/";

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("is_dir", false);
            map.put("has_file", false);
            map.put("filesize", files[i].length());
            map.put("is_photo", true);
            map.put("filetype", FilenameUtils.getExtension(files[i].getName()));
            map.put("filename", files[i].getName());
            map.put("datetime", new Date());
            list.add(map);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("current_url", current_url);
        map.put("total_count", files.length);
        map.put("file_list", list);
        return map;
    }

    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile image, HttpServletRequest request) {
        System.out.println("image:   " + image.getOriginalFilename());
        Map<String, Object> map = new HashMap<>();
        try {
            //文件上传
            image.transferTo(new File(request.getSession().getServletContext().getRealPath("view/article/image"), image.getOriginalFilename()));
            //给返回值  {"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
            String url = "http://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath() + "/view/article/image/" + image.getOriginalFilename();
            map.put("error", 0);
            map.put("url", url);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            return map;
        }
    }


}
