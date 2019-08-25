package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_banner")
@ExcelTarget(value = "banner")
public class Banner implements Serializable {
    @Id
    @Excel(name = "编号")
    private String id;

    @Excel(name = "封面", type = 2, width = 20, height = 15)
    private String cover;

    @Excel(name = "标题")
    private String title;

    @Excel(name = "状态")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//后台Util Date  转换成json字符串
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前台日期转换为Util Date
    @Excel(name = "创建日期", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Excel(name = "描述")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后一次修改日期", format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

}
