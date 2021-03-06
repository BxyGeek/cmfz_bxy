package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String, Object> selectAllByAlbumId(Integer page, Integer rows, String albumId);

    String add(Chapter chapter);

    void edit(Chapter chapter);

    void del(Chapter chapter);
}
