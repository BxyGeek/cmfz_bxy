package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;


    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Article article = new Article();
        List<Article> articles = articleDAO.selectByRowBounds(article, rowBounds);
        int count = articleDAO.selectCount(article);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", articles);
        map.put("total", count / rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString().replace("-", ""));
        article.setPublishDate(new Date());
        article.setGuruId("123123123");
        int i = articleDAO.insertSelective(article);
        if (i == 0) throw new RuntimeException("文章添加失败");
    }

    @Override
    public void edit(Article article) {
        int i = articleDAO.updateByPrimaryKeySelective(article);
        if (i == 0) throw new RuntimeException("文章修改失败");

    }

    @Override
    public void del(Article article) {
        int i = articleDAO.deleteByPrimaryKey(article.getId());
        if (i == 0) throw new RuntimeException("文章删除失败");

    }
}
