package com.baizhi.controller;

import com.baizhi.api.BaseApiService;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController extends BaseApiService {

    @Autowired
    private ChapterService chapterService;


    @RequestMapping("selectAllByAlbumId")
    public Map<String, Object> selectAllByAlbumId(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = chapterService.selectAllByAlbumId(page, rows, albumId);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Chapter chapter, String albumId) {
        if ("add".equals(oper)) {
            chapter.setAlbumId(albumId);
            System.out.println("albumId:       " + albumId);
            String id = chapterService.add(chapter);
            return setResultSuccessData(id);
        }
        if ("edit".equals(oper)) {
            chapterService.edit(chapter);
            return setResultSuccess();
        }
        if ("del".equals(oper)) {
            chapterService.del(chapter);
        }
        return setResultSuccess();
    }


    @RequestMapping("upload")
    public void upload(MultipartFile audioPath, String id, HttpSession session) throws Exception {
        //文件上传
        File file = new File(session.getServletContext().getRealPath("/view/chapter/audio"), audioPath.getOriginalFilename());
        audioPath.transferTo(file);
        //获取文件大小
        long audioPathSize = audioPath.getSize();
        System.out.println("1      " + audioPathSize);
        BigDecimal bigDecimal = new BigDecimal(audioPathSize);
        BigDecimal decimal = new BigDecimal(1024);
        BigDecimal scale = bigDecimal.divide(decimal).divide(decimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("2      " + scale);
        //获取文件时长
        Encoder encoder = new Encoder();
        long duration = encoder.getInfo(file).getDuration();
        System.out.println("3      " + duration);
        String s = duration / 1000 / 60 + ":" + duration / 1000 % 60;
        System.out.println("4      " + s);
        //修改数据库中的图片路径
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setAudioPath(audioPath.getOriginalFilename());
        chapter.setSize(scale + "MB");
        chapter.setDuration(s);
        chapterService.edit(chapter);
    }
}