<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>

<script>
    $("#article-table").jqGrid({
        url: '${app}/article/selectAll',
        editurl: '${app}/article/edit',
        datatype: "json",
        colNames: ['ID', '标题', '作者', '内容', '发布时间', '操作'],
        colModel: [
            {name: 'id', hidden: true},
            {name: 'title', width: 90, editable: true},
            {name: 'author', width: 50, editable: true},
            {name: 'content', height: 800, width: 150, align: "center", editable: true},
            {name: 'publishDate', width: 80, align: "center"},
            {
                name: 'option', width: 80, formatter: function (value, option, rows) {
                    return "<a class='btn btn-primary' onclick=\"openModal('edit','" + rows.id + "')\">修改</a>";
                }
            },
        ],
        styleUI: 'Bootstrap',
        autowidth: true,
        height: '300px',
        rowNum: 3,
        rowList: [3, 5, 10, 20, 30],
        pager: '#article-pager',
        mtype: "post",
        viewrecords: true,
        //caption : "展示文章详情"
    }).jqGrid('navGrid', '#article-pager', {edit: false, add: false, del: true, search: false},
        {}, {
            closeAfterAdd: true,
        }, {});


    //打开模态框
    function openModal(oper, id) {
        KindEditor.html("#editor_id", "");
        var article = $("#article-table").jqGrid("getRowData", id);
        $("#article-id").val(article.id);
        $("#article-oper").val(oper);
        $("#article-title").val(article.title);
        $("#article-author").val(article.author);
        KindEditor.html("#editor_id", article.content);
        $("#article-modal").modal("show");
    }

    //设置kindeditor相关属性
    KindEditor.create('#editor_id', {
        width: '100%',
        height: '350px',
        //minWidth:700,
        //filterMode:false
        resizeType: 0,
        //langType : 'en'
        //显示图片空间按钮
        allowFileManager: true,
        // 图片空间按钮的URL
        fileManagerJson: '${app}/article/browser',
        //文件上传的url
        uploadJson: '${app}/article/upload',
        //指定后台接受的图片的名称
        filePostName: 'image',

        afterBlur: function () {
            this.sync();
        }

    });

    function saveArticle() {
        $.ajax({
            url: '${app}/article/edit',
            type: 'post',
            data: $("#article-form").serialize(),
            datatype: 'json',
            success: function () {
                //关闭模态框
                $("#article-modal").modal("hide");
                //刷新jqgrid
                $("#article-table").trigger("reloadGrid")
            }
        })
    }


</script>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">展示所有文章</a></li>
    <li role="presentation"><a onclick="openModal('add')">添加文章</a></li>
</ul>


<div class="modal fade" role="dialog" id="article-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">我的文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline" id="article-form">
                    <input type="hidden" id="article-oper" name="oper">
                    <input type="hidden" id="article-id" name="id">
                    <div class="form-group">
                        <label for="article-title">文章标题:</label>
                        <input type="text" name="title" class="form-control" id="article-title" placeholder="请输入标题...">
                    </div>
                    <div class="form-group">
                        <label for="article-author">文章作者:</label>
                        <input type="email" name="author" class="form-control" id="article-author"
                               placeholder="请输入作者...">
                    </div>
                    <div class="form-group">
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                        </textarea>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveArticle()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="article-table"></table>
<div id="article-pager"></div>