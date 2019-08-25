<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<!doctype html>
<html lang="en">
<head>
    <title>KindEditor使用</title>
    <script charset="utf-8" src="kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>

    <script>
        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id', {
                width: '800px',
                height: '400px',
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
            });
        });
    </script>
</head>
<body>


<textarea id="editor_id" name="content">
        &lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>

</body>
</html>