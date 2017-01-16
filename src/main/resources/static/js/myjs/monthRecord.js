/**
 * Created by jonhn on 2017/1/16.
 */




/*下拉框*/
jQuery(document).ready(function() {

    /*下拉框*/
    $("#years").change(function () {
        var param = $("#years").val();
        var param1 = $("#customerId").val();
        var param2 = $("#typeId").val();
        if (param !=null) {
            $.ajax({
                url: '/finance/find-company-customer-month-uplink-months-by-customer-id',
                data: {"years": param, "customerId": param1, "typeId": param2},
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if(data != null){
                        $("#months ").empty();
                        $("#months").append("<option value=''>请选择...</option>");
                        for (var i=0; i<data.length; i++){
                            var op=document.createElement("option");
                            op.value=data[i];
                            op.innerHTML='第'+data[i]+'月';
                            $("#months").append(op);
                        }
                    }
                }
            });
        }
    });

});

/*折线图*/
$(document).ready(function () {
    var param1 = $("#customerId").val();
    var param2 = $("#typeId").val();
    var param3 = $("#years").val();
    var param4 = $("#months").val();
    $('#months-account').on('click',function () {
        $.ajax({
            type: "post",
            url: "/finance/months-charge-consume-toward",
            data: {"years": param3,"months": param4, "customerId": param1, "typeId": param2},
            dataType: 'json',
            success: function (result) {
                var json = result;
                $('#months-container').highcharts({
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: ''
                    },
                    exporting: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    xAxis: {
                        categories: json.xList
                    },
                    yAxis: {
                        title: {
                            text: '金额 (单位：元)'
                        }
                    },
                    plotOptions: {
                        line: {
                            dataLabels: {
                                enabled: true
                            },
                            enableMouseTracking: false
                        }
                    },
                    series: json.yList
                });
            }
        });
    });
});