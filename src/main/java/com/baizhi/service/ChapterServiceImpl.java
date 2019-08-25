package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;


    @Override
    public Map<String, Object> selectAllByAlbumId(Integer page, Integer rows, String albumId) {
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        RowBounds rowBounds = new RowBounds((page - 1) * page, rows);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        int count = chapterDAO.selectCount(chapter);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("page", page);
        map.put("rows", chapters);
        map.put("total", count / rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public String add(Chapter chapter) {
        String id = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(id);
        chapter.setCreateDate(new Date());
        int i = chapterDAO.insertSelective(chapter);
        if (i == 0) throw new RuntimeException("章节添加失败");
        return id;
    }

    @Override
    public void edit(Chapter chapter) {
        int i = chapterDAO.updateByPrimaryKeySelective(chapter);
        if (i == 0) throw new RuntimeException("章节添加失败");
    }

    @Override
    public void del(Chapter chapter) {
        chapterDAO.deleteByPrimaryKey(chapter);
    }
}
