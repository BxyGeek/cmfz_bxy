package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baizhi.dao.AdminDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Student;
import com.baizhi.entity.Teacher;
import com.baizhi.entity.User;
import io.goeasy.GoEasy;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzBxyApplicationTests {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Test
    public void testUser() {
        /*List<User> users = userDAO.selectAll();
        for (User user : users) {
            System.out.println(user);
        }*/
        /*User user = new User();
        int count = userDAO.selectCount(user);
        System.out.println(count);*/
        Admin admin = new Admin();
        /*admin.setUsername("zhangsan");
        admin.setId("1");
        List<Admin> admins = adminDAO.select(admin);
        for (Admin admin1 : admins) {
            System.out.println(admin1);
        }*/
        /*admin.setId("1");
        Admin admin1 = adminDAO.selectByPrimaryKey(admin);
        System.out.println(admin1);*/
        //admin.setId("2");
        //admin.setUsername("wangqi");

        //int i = adminDAO.updateByPrimaryKey(admin);
        //int i = adminDAO.updateByPrimaryKeySelective(admin);
        //int i = adminDAO.delete(admin);
        //System.out.println(i);


        //Admin admin1 = adminDAO.selectByPrimaryKey("1");
        Example example = new Example(Admin.class);
        //example.createCriteria().andEqualTo("username","wangqi");
        /*example.createCriteria().andBetween("id",3,7);

        List<Admin> admins = adminDAO.selectByExample(example);
        for (Admin admin1 : admins) {
            System.out.println(admin1);
        }*/

        //第一个参数 offset 下标    limmit 查询每页条数
        RowBounds rowBounds = new RowBounds(0, 3);
        //example.createCriteria().andLike("username","__ng%");

        //List<Admin> admins = adminDAO.selectByRowBounds(admin, rowBounds);
        List<Admin> admins = adminDAO.selectByExampleAndRowBounds(example, rowBounds);

        for (Admin admin1 : admins) {
            System.out.println(admin1);
        }
    }

    @Test
    public void testDouble() {
        double a = 1.0;
        double b = 0.9;
        System.out.println(a - b);
    }

    @Test
    public void testBig() {
        BigDecimal bigDecimal1 = new BigDecimal(1231323);
        BigDecimal bigDecimal2 = new BigDecimal(5);
        System.out.println(bigDecimal1.add(bigDecimal2));
        System.out.println(bigDecimal1.subtract(bigDecimal2));
        System.out.println(bigDecimal1.multiply(bigDecimal2));
        System.out.println(bigDecimal1.divide(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void testPOI() {
        List<User> users = userDAO.selectAll();
        //创建工作薄
        Workbook workbook = new HSSFWorkbook();
        //创建字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("楷体");
        font.setItalic(true);
        font.setStrikeout(true);
        //创建样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        //创建日期格式的样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        //创建日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        cellStyle1.setDataFormat(format);
        //创建工作表
        Sheet sheet = workbook.createSheet("user");
        //第一个参数 是要给那一列设置宽度 下标    第二个参数 宽度  *256
        sheet.setColumnWidth(3, 20 * 256);
        //创建行  参数为下标
        Row row = sheet.createRow(0);
        String[] strings = {"编号", "姓名", "性别", "生日"};
        for (int i = 0; i < strings.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);
            //给单元格设置样式
            cell.setCellStyle(cellStyle);
            //给单元格赋值
            cell.setCellValue(strings[i]);
        }
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getUsername());
            row1.createCell(2).setCellValue(users.get(i).getSex());
            Cell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(users.get(i).getCreateDate());
        }
        try {
            workbook.write(new FileOutputStream(new File("F:/user.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPOIImport() {
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("F:/user.xls")));
            Sheet sheet = workbook.getSheet("user");
            Row row = sheet.getRow(0);
            String id = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String sex = row.getCell(2).getStringCellValue();
            String bir = row.getCell(3).getStringCellValue();
            System.out.println(id + "=====" + name + "======" + sex + "======" + bir);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                User user = new User();
                Row row1 = sheet.getRow(i);
                user.setId(row1.getCell(0).getStringCellValue());
                user.setUsername(row1.getCell(1).getStringCellValue());
                user.setSex(row1.getCell(2).getStringCellValue());
                user.setCreateDate(row1.getCell(3).getDateCellValue());
                System.out.println("user:             " + user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testEasypoi1() {

        List<Student> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Student student = new Student(i + "", "小花花" + i, 10 + i, new Date(), "哈哈哈.jpg");
            list.add(student);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"),
                Student.class, list);
        try {
            workbook.write(new FileOutputStream(new File("F:/student.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEasypoi2() {

        //学生集合
        List<Student> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Student student = new Student(i + "", "小花花" + i, 10 + i, new Date(), "F:\\framework\\cmfz\\cmfz_bxy\\src\\main\\webapp\\image\\shouye.png");
            list.add(student);
        }

        //教师集合
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher1 = new Teacher("123123", "suns", "男", list);
        Teacher teacher2 = new Teacher("123123", "liucy", "男", list);
        teachers.add(teacher1);
        teachers.add(teacher2);

        //工作表
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"),
                Teacher.class, teachers);
        try {
            workbook.write(new FileOutputStream(new File("F:/student.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGoEasy() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-80db1671f5804e0d8dcef8c3c070a396");
        goEasy.publish("buxy", "Hello, GoEasy!");
    }


    @Test
    public void testMessage() throws ClientException {
        /*//设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAIgMBmSXEZn92j";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "3ybLrjCrt3O2tRbRe5L60xOVgyeWuA";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("15039116335,15670862253,18839127225,15081597878,15639176821,17721314223");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("艾小飞鱼");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_160301573");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        String salt = Md5UUIDSaltUtil.getSalt();
        request.setTemplateParam("{ \"code\":\"12341\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
            System.out.println("请求成功了  发送成功了");
        }*/
    }


    @Test
    public void testMessage1() {

        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIgMBmSXEZn92j", "3ybLrjCrt3O2tRbRe5L60xOVgyeWuA");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", "15039116335");
        request.putQueryParameter("SignName", "艾小飞鱼");
        request.putQueryParameter("TemplateCode", "SMS_164100021");
        request.putQueryParameter("TemplateParam", "{\"code\":\"654654\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

}