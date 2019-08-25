<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/boot/js/jquery-2.2.1.min.js"></script>

<script>

    function userAdd() {
        $.ajax({
            type: 'POST',
            url: '${app}/user/add',
            data: $("#user-form").serialize(),
            dataType: 'json',
            success: function (data) {

            }
        })
    }

</script>
<form id="user-form">

    <input type="text" name="username"><br/>
    <input type="text" name="province"><br/>
    <input type="submit" onclick="userAdd()">

</form>
