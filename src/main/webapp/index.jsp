<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<!doctype html>
<html lang="en">
<head>
    <title>持明法洲后台管理首页</title>
    <%--引入bootstrap的样式--%>
    <link rel="stylesheet" href="statics/boot/css/bootstrap.min.css">
    <%--引入bootstrap和jqgrid的整合样式--%>
    <link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

    <%--引入js文件--%>
    <script src="statics/boot/js/jquery-2.2.1.min.js"></script>
    <%--引入bootstrap的js文件--%>
    <script src="statics/boot/js/bootstrap.min.js"></script>

    <%--引入jqgrid的js文件--%>
    <script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="statics/jqgrid/js/ajaxfileupload.js"></script>

    <script charset="utf-8" src="${app}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${app}/kindeditor/lang/zh-CN.js"></script>

</head>
<body>
<%--顶部导航条--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎：${adminName}</a></li>
                <li><a href="${app}/admin/logOut">安全退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<%--中间--%>
<div class="row">
    <%--左侧栅格--%>
    <div class="col-xs-2">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title text-center">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="true" aria-controls="collapseOne">
                            <h4>轮播图管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/view/banner/banner-show.jsp')"
                           class="btn btn-default">轮播图详情</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title text-center">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <h4>专辑管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/view/album/album-show.jsp')"
                           class="btn btn-default">专辑详情</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title text-center">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            <h4>文章管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/view/article/article-show.jsp')"
                           class="btn btn-default">文章详情</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title text-center">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            <h4>用户管理</h4>
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body text-center">
                        <a href="javascript:$('#contentLayout').load('${app}/view/user/user-show.jsp')"
                           class="btn btn-default">查询用户</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--右侧--%>
    <div class="col-xs-10" id="contentLayout">
        <div class="jumbotron" style="padding-left: 80px">
            <h3>持明法洲后台管理系统</h3>
        </div>
        <img src="image/shouye.png" alt=""
             style="width: 100%;height: 230px">
    </div>
</div>

<%--页脚--%>
<div class="panel panel-footer text-center">
    <h4>持明法洲后台管理系统@百知教育 2019-08-14</h4>
</div>


</body>
</html>