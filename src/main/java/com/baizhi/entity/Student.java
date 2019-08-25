package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget(value = "student")
public class Student implements Serializable {
    @Excel(name = "编号")
    private String id;

    @Excel(name = "姓名")

    private String name;

    @Excel(name = "年龄")

    private Integer age;

    @Excel(name = "生日", format = "yyyy年MM月dd日 HH时mm分ss秒", width = 30)
    private Date bir;

    @Excel(name = "头像", type = 2, width = 40, height = 20)
    private String image;

}
