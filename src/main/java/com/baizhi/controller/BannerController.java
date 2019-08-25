package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.api.BaseApiService;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController extends BaseApiService {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAllBanners")
    public Map<String, Object> selectAllBanners(Integer page, Integer rows) {
        try {
            Map<String, Object> map = bannerService.selectAllBannersByPage(page, rows);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return setResultErrorMsg("查询轮播图失败");
        }
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Banner banner) {
        if ("add".equals(oper)) {
            String id = bannerService.add(banner);
            return setResultSuccessData(id);
        }
        if ("edit".equals(oper)) {
            String id = bannerService.edit(banner);
            return setResultSuccessData(id);
        }
        if ("del".equals(oper)) {
            bannerService.del(banner);
        }
        return setResultSuccess();
    }


    @RequestMapping("upload")
    public String upload(MultipartFile cover, String id, HttpSession session) throws IOException {
        System.out.println("id :   " + id);
        System.out.println("cover:   " + cover.getOriginalFilename());
        //文件上传
        cover.transferTo(new File(session.getServletContext().getRealPath("/view/banner/image"), cover.getOriginalFilename()));

        //修改数据库中的属性 音频地址  大小  时长
        Banner banner = new Banner();
        banner.setId(id);
        banner.setCover(cover.getOriginalFilename());
        return bannerService.edit(banner);
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response, HttpServletRequest request) {
        //
        String filePath = request.getSession().getServletContext().getRealPath("view/banner/image/");
        System.out.println(filePath);

        List<Banner> banners = bannerService.selectAll();
        ArrayList<Banner> list = new ArrayList<>();

        for (Banner banner : banners) {
            banner.setCover(filePath + banner.getCover());
            list.add(banner);
        }
        //工作表
        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("驰明法洲轮播图信息", "轮播图"),
                Banner.class, list);
        try {
            workbook.write(response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment;fileName=banner.xls");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}