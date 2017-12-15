

function currDayApiTypeConsume(vendorId) {
    $("#simple_curr_day_api_type_consume tbody").empty();

    /*加载供应商的名称*/
    $.ajax({
        type: "post",
        url: "/api/find-all-vendor/vendor-name",
        data:{"vendorId":vendorId},
        dataType: "json",
        success:function (result) {
            if (result != null && result.vendorName != null){
                $('#vendor_name').html(result.vendorName)
            }
        }
    });

    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/api/find-all-vendor/curr-day-api-type-consume",
        data:{"vendorId":vendorId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#simple_curr_day_api_type_consume tbody").append(myContent);
        },
        success:function (result) {

            $("#simple_curr_day_api_type_consume tbody").empty();

            if (result != null && result.consumeList != null){
                var data = result.consumeList;
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var myContent;

                        if (data[i].cost == null) {
                            myContent = "<tr>" +
                                "<td>" + data[i].apiTypeName + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + data[i].consumeAccount + "</td>" +
                                /*"<td>" + data[i].countTotle + "</td>" +*/
                                "<td>" + data[i].apiTypeConsume + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + data[i].apiTypeName + "</td>" +
                                "<td>" + data[i].cost + "</td>" +
                                "<td>" + data[i].consumeAccount + "</td>" +
                                /*"<td>" + data[i].countTotle + "</td>" +*/
                                "<td>" + data[i].apiTypeConsume + "</td>" +
                                "</tr>";
                        }
                        $("#simple_curr_day_api_type_consume tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#simple_curr_day_api_type_consume tbody").append(myContent);
                }
            }else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#simple_curr_day_api_type_consume tbody").append(myContent);
            }

        }
    });
}


