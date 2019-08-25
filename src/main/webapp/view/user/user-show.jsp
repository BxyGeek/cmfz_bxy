<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<table id="user-table"></table>
<div id="user-pager"></div>

<script>

    $("#user-table").jqGrid({
        url: '${app}/user/selectAll',
        editurl: '${app}/user/edit',
        datatype: "json",
        colNames: ['ID', '姓名', '头像', '性别', '法名', '省', '市', '手机号', '密码'],
        colModel: [
            {name: 'id', hidden: true},
            {name: 'username', editable: true},
            {
                name: 'photo',
                formatter: function (value, option, rows) {
                    return "<img style='width:100px;height:20%' src='${app}/view/user/image/" + value + "'>";
                }
            },
            {
                name: 'sex', editable: true, edittype: 'select', editoptions: {value: "1:男;0:女"},
                formatter: function (value, option, rows) {
                    if (value == 1) return "男";
                    else return "女";
                }
            },
            {name: 'dharma', editable: true},
            {name: 'province', editable: true},
            {name: 'city', editable: true},
            {name: 'phone', editable: true},
            {name: 'password', editable: true}
        ],
        rowNum: 3,
        styleUI: 'Bootstrap',
        height: '280px',
        autowidth: true,
        rowList: [3, 5, 10, 20, 30],
        pager: '#user-pager',
        viewrecords: true,
        caption: "用户详细信息"
    }).jqGrid('navGrid', '#user-pager', {edit: true, add: true, del: true},
        {
            //控制修改操作
            closeAfterEdit: true
        }, {
            //控制添加操作
            closeAfterAdd: true,
            afterSubmit: function (response) {
                $("#user-table").trigger("reloadGrid");
                /*
                        var id = response.responseJSON.data;
                        var code = response.responseJSON.code;
                        if (code == 200){
                            $.ajaxFileUpload( {
                                url : "$ {app}/user/upload",//用于文件上传的服务器端请求地址
                                fileElementId : 'phone',    //文件上传空间的id属性  <input type="file" id="file" name="file" />
                                dataType : 'json',       //返回值类型 一般设置为json
                                type:'POST',
                                data:{id:id},
                                success : function() {
                                    $("#user-table").trigger("reloadGrid");
                                }
                            })
                            return "true";
                        }*/
            }
        }, {
            //控制删除操作
        });
</script>

