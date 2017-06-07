

function currDayApiTypeConsume(customerId) {

    $("#simple_customer_curr_day_api_type_consume tbody").empty();

    /*加载公司名称*/
    $.ajax({
        type: "post",
        url: "/finance/find-all-customer/company-name",
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
        url: "/finance/find-all-customer/curr-day-api-type-consume",
        data:{"customerId":customerId},
        dataType: "json",
        beforeSend:function () {
                var myContent = "<tr>" +
                    "<td rowspan='5'>" + '正在加载，请稍后...' + "</td>" +
                    "</tr>";
                $("#simple_customer_curr_day_api_type_consume tbody").append(myContent);
        },
        success:function (result) {

            $("#simple_customer_curr_day_api_type_consume tbody").empty();

            if (result != null && result.customerApiTypeConsumeList != null){
                var data = result.customerApiTypeConsumeList;
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var myContent;
                        if (data[i].subTypeName == null) {
                            if (data[i].price == null) {
                                myContent = "<tr>" +
                                    "<td>" + data[i].apiTypeName + "</td>" +
                                    "<td>" + '未知' + "</td>" +
                                    "<td>" + -data[i].sumAmount / 100.0 + "</td>" +
                                    "<td>" + data[i].countTotle + "</td>" +
                                    "<td>" + data[i].countSuccess + "</td>" +
                                    "</tr>";
                            } else {
                                myContent = "<tr>" +
                                    "<td>" + data[i].apiTypeName + "</td>" +
                                    "<td>" + data[i].price / 100.0 + "</td>" +
                                    "<td>" + -data[i].sumAmount / 100.0 + "</td>" +
                                    "<td>" + data[i].countTotle + "</td>" +
                                    "<td>" + data[i].countSuccess + "</td>" +
                                    "</tr>";
                            }
                        } else {
                            if (data[i].price == null) {
                                myContent = "<tr>" +
                                    "<td>" + data[i].apiTypeName + "--" + data[i].subTypeName + "</td>" +
                                    "<td>" + '未知' + "</td>" +
                                    "<td>" + -data[i].sumAmount / 100.0 + "</td>" +
                                    "<td>" + data[i].countTotle + "</td>" +
                                    "<td>" + data[i].countSuccess + "</td>" +
                                    "</tr>";
                            } else {
                                myContent = "<tr>" +
                                    "<td>" + data[i].apiTypeName + "--" + data[i].subTypeName + "</td>" +
                                    "<td>" + data[i].price / 100.0 + "</td>" +
                                    "<td>" + -data[i].sumAmount / 100.0 + "</td>" +
                                    "<td>" + data[i].countTotle + "</td>" +
                                    "<td>" + data[i].countSuccess + "</td>" +
                                    "</tr>";
                            }
                        }
                        $("#simple_customer_curr_day_api_type_consume tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='5'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#simple_customer_curr_day_api_type_consume tbody").append(myContent);
                }
            }

        }
    });

}