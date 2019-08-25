<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/view/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/boot/js/jquery-2.2.1.min.js"></script>

<script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="statistics_main" style="width: 600px;height: 400px;margin-top: 30px;margin-left: 30px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('statistics_main'));


    $(function () {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/user/selectByWeek',
            dataType: 'json',
            success: function (data) {
                //使用刚指定的配置项和数据显示图表。
                myChart.setOption({
                    title: {
                        text: '持名法州App活跃用户'
                    },
                    tooltip: {},
                    legend: {
                        data: ['活跃用户']
                    },
                    xAxis: {
                        data: ["近一周", "近二周", "近三周"]
                    },
                    yAxis: {},
                    series: [{
                        name: '活跃用户',
                        type: 'bar',
                        data: [data.week1, data.week2, data.week3]
                    }]
                })
            }
        })
    })
    var goEasy = new GoEasy({
        appkey: "BC-80db1671f5804e0d8dcef8c3c070a396"
    });
    goEasy.subscribe({
        channel: "buxy",
        onMessage: function (message) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/user/selectByWeek',
                dataType: 'json',
                success: function (data) {
                    myChart.setOption({
                        series: [{
                            data: [data.week1, data.week2, data.week3]
                        }]
                    })
                }
            })
        }
    });
</script>