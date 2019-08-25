<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>GoEasy使用</title>

    <script type="text/javascript" src="../statics/boot/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
</head>

<body>

<script type="text/javascript">
    $(function () {
        var goEasy = new GoEasy({
            appkey: "BC-80db1671f5804e0d8dcef8c3c070a396"
        });
        //GoEasy-OTP可以对appkey进行有效保护,详情请参考​ ​
        goEasy.publish({
            channel: "buxy",
            message: "11111111111111111!"
        });
    })
</script>


</body>
</html>