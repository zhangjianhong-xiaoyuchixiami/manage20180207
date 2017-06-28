

function currDayCacheApiTypeConsume(customerId) {

    $("#simple_customer_curr_day_api_type_consume tbody").empty();

    /*加载公司名称*/
    $.ajax({
        type: "post",
        url: "/cache/find-all-customer/company-name",
        data:{"customerId":customerId},
        dataType: "json",
        success:function (result) {
            if (result != null && result.companyName != null){
                $('#customer_company_name').html(result.companyName)
            }
        }
    });

    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/cache/find-all-customer/curr-day-api-type-consume",
        data:{"customerId":customerId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='2'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#simple_customer_curr_day_api_type_consume tbody").append(myContent);
        },
        success:function (result) {

            $("#simple_customer_curr_day_api_type_consume tbody").empty();

            if (result != null && result.customerCacheApiTypeConsumeList != null){
                var data = result.customerCacheApiTypeConsumeList;
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var myContent;
                        myContent = "<tr>" +
                            "<td>" + data[i].apiTypeName_stidName + "</td>" +
                            "<td>" + data[i].cacheCount + "</td>" +
                            "</tr>";

                        $("#simple_customer_curr_day_api_type_consume tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='2'>" + '无记录' + "</td>" +
                        "</tr>";
                    $("#simple_customer_curr_day_api_type_consume tbody").append(myContent);
                }
            }

        }
    });

}