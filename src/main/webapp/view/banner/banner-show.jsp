<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>

<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="${app}/banner/export">导出轮播图</a></li>
</ul>

<table id="banner-table"></table>
<div id="banner-pager"></div>


<script>
    $("#banner-table").jqGrid({
        url: '${app}/banner/selectAllBanners',
        editurl: '${app}/banner/edit',
        datatype: "json",
        colNames: ['ID', '标题', '封面', '状态', '创建时间', '描述'],
        colModel: [
            {name: 'id', hidden: true},
            {name: 'title', editable: true},
            {
                name: 'cover', editable: true, edittype: 'file',
                formatter: function (value, option, rows) {
                    return "<img style='width:100px;height:20%' src='${app}/view/banner/image/" + value + "'>";
                }
            },
            {
                name: 'status', editable: true, edittype: 'select', editoptions: {value: "1:正常;0:冻结"},
                formatter: function (value, option, rows) {
                    if (value == 1) return "正常";
                    else return "冻结";
                }
            },
            {name: 'createDate'},
            {name: 'description', editable: true}
        ],
        rowNum: 3,
        styleUI: 'Bootstrap',
        height: '280px',
        autowidth: true,
        rowList: [3, 5, 10, 20, 30],
        pager: '#banner-pager',
        viewrecords: true,
        caption: "轮播图详细信息"
    }).jqGrid('navGrid', '#banner-pager', {edit: true, add: true, del: true},
        {
            //控制修改操作
            closeAfterEdit: true
        }, {
            //控制添加操作
            closeAfterAdd: true,
            afterSubmit: function (response) {
                var id = response.responseJSON.data;
                var code = response.responseJSON.code;
                if (code == 200) {
                    $.ajaxFileUpload({
                        url: "${app}/banner/upload",//用于文件上传的服务器端请求地址
                        fileElementId: 'cover',    //文件上传空间的id属性  <input type="file" id="file" name="file" />
                        dataType: 'json',       //返回值类型 一般设置为json
                        type: 'POST',
                        data: {id: id},
                        success: function () {
                            $("#banner-table").trigger("reloadGrid");
                        }
                    })
                    return "true";
                }
            }
        }, {
            //控制删除操作
            afterComplete: function () {
                alert("哈哈哈");
            }
        });
</script>

