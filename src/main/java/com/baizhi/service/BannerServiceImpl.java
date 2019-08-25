package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;


    @Override
    public Map<String, Object> selectAllBannersByPage(Integer page, Integer rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Banner banner = new Banner();
        //分页以后的查询结果
        List<Banner> banners = bannerDAO.selectByRowBounds(banner, rowBounds);
        int count = bannerDAO.selectCount(banner);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("page", page); //当前页码
        map.put("rows", banners); //查询到的分页以后的数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);//总页数
        map.put("records", count);//总条数
        return map;
    }

    @Override
    public String add(Banner banner) {
        String id = UUID.randomUUID().toString().replace("-", "");
        banner.setId(id);
        banner.setCreateDate(new Date());
        banner.setLastUpdateDate(new Date());
        int i = bannerDAO.insertSelective(banner);
        if (i == 0) throw new RuntimeException("轮播图添加失败");
        return id;
    }

    @Override
    public String edit(Banner banner) {
        if ("".equals(banner.getCover())) {
            banner.setCover(null);
        }
        banner.setLastUpdateDate(new Date());
        int i = bannerDAO.updateByPrimaryKeySelective(banner);
        if (i == 0) throw new RuntimeException("轮播图修改失败");
        return banner.getId();
    }

    @Override
    public void del(Banner banner) {
        int i = bannerDAO.deleteByPrimaryKey(banner);
        if (i == 0) throw new RuntimeException("轮播图删除失败");
    }

    @Override
    public List<Banner> selectAll() {
        return bannerDAO.selectAll();
    }
}
