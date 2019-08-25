<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>GoEasy使用</title>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
</head>


<body>

<script type="text/javascript">

    var goEasy = new GoEasy({
        appkey: "BC-80db1671f5804e0d8dcef8c3c070a396"
    });

    //订阅
    goEasy.subscribe({
        channel: "buxy",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });

</script>


</body>
</html>