package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget(value = "teacher")
public class Teacher implements Serializable {
    @ExcelIgnore
    private String id;
    @Excel(name = "教师姓名", needMerge = true)
    private String name;
    @Excel(name = "性别")
    private String sex;
    @ExcelCollection(name = "java152班学员")
    private List<Student> children;
}
