<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<table id="album-table"></table>
<div id="album-pager"></div>

<script>

    $("#album-table").jqGrid({
        url: '${app}/banner/selectAllBanners',
        editurl: '${app}/banner/edit',
        datatype: "json",
        colNames: ['ID', '标题', '封面', '作者', '创建时间', '集数'],
        colModel: [
            {name: 'id', hidden: true},
            {name: 'title', editable: true},
            {
                name: 'cover', editable: true, edittype: 'file',
                formatter: function (value, option, rows) {
                    return "<img style='width:100px;height:20%' src='${app}/view/banner/image/" + value + "'>";
                }
            },
            {name: 'author', editable: true},
            {name: 'createDate'},
            {name: 'amount', editable: true}
        ],
        styleUI: 'Bootstrap',
        autowidth: true,
        height: '300px',
        rowNum: 3,
        rowList: [3, 8, 10, 20, 30],
        pager: '#album-pager',
        viewrecords: true,
        subGrid: true,
        caption: "专辑详细信息",

        subGridRowExpanded: function (subgrid_id, id) {  //1. 子表格的id   2

            var subgrid_table_id = subgrid_id + "_t";  //子表格的table id

            var pager_id = "p_" + subgrid_table_id;    //子表格的page id


            $("#" + subgrid_id).html(
                "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                "<div id='" + pager_id + "' class='scroll'></div>");

            $("#" + subgrid_table_id).jqGrid(
                {
                    url: "${app}/chapter/selectAllByAlbumId?albumId=" + id,
                    editurl: "${app}/chapter/edit?albumId=" + id,
                    datatype: "json",
                    colNames: ['ID', '章节名称', '章节大小', '章节时长', '音频文件'],
                    colModel: [
                        {name: "id", width: '30'},
                        {name: "title", width: '50', editable: true},
                        {name: "size", width: '30'},
                        {name: "duration", width: '30'},
                        {
                            name: "audioPath",
                            editable: true,
                            edittype: 'file',
                            formatter: function (value, option, rows) {
                                return "<audio controls loop>\n" +
                                    "  <source src='${app}/view/chapter/audio/" + value + "' type=\"audio/mpeg\">\n" +
                                    "</audio>";
                            }
                        },
                    ],
                    rowNum: 20,
                    pager: pager_id,
                    height: '230px',
                    styleUI: 'Bootstrap',
                    autowidth: true,
                }).jqGrid('navGrid', "#" + pager_id, {edit: true, add: true, del: true, refresh: true},
                {
                    //控制子表的修改
                    closeAfterEdit: true,
                    afterSubmit: function (response) {
                        $("#" + subgrid_table_id).trigger("reloadGrid");
                    }
                }, {
                    //控制子表的添加
                    closeAfterAdd: true,
                    afterSubmit: function (response) {
                        var id = response.responseJSON.data;
                        var code = response.responseJSON.code;
                        console.log("id:    " + id);
                        console.log("code:    " + code);
                        if (code == 200) {
                            $.ajaxFileUpload({
                                url: "${app}/chapter/upload",//用于文件上传的服务器端请求地址
                                fileElementId: 'audioPath',    //文件上传空间的id属性  <input type="file" id="file" name="file" />
                                dataType: 'json',       //返回值类型 一般设置为json
                                type: 'POST',
                                data: {id: id},
                                success: function () {
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            })
                            return "123123";
                        }

                    }
                }, {
                    //控制子表的删除
                    afterComplete: function () {
                        alert("2345435")
                        $("#album-table").trigger("reloadGrid");
                    }

                });
        }
    }).jqGrid('navGrid', '#album-pager', {edit: true, add: true, del: true}, {}, {}, {})


</script>

