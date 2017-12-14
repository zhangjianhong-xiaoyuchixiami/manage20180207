

function currDayApiTypeConsume(vendorId) {

    $("#simple_vendor_curr_day_api_type_consume tbody").empty();

    /*加载公司名称*/
    $.ajax({
        type: "post",
        url: "/api/find-all-vendor/company-name",
        data:{"vendorId":vendorId},
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
        url: "/api/find-all-vendor/curr-day-api-type-consume",
        data:{"vendorId":vendorId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='5'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#simple_vendor_curr_day_api_type_consume tbody").append(myContent);
        },
        success:function (result) {

            $("#simple_vendor_curr_day_api_type_consume tbody").empty();

            if (result != null && result.consumeList != null){
                var data = result.consumeList;
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var myContent;

                        if (data[i].price == null) {
                            myContent = "<tr>" +
                                "<td>" +
                                "<a href='javaScript:;' onclick='currDayDetail("+ data[i].vendorId +','+ data[i].apiTypeId +','+ data[i].stid +")'>" + data[i].apiTypeName + "</a>" +
                                "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + data[i].sumAmount + "</td>" +
                                /*"<td>" + data[i].countTotle + "</td>" +*/
                                "<td>" + data[i].countSuccess + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" +
                                "<a href='javaScript:;' onclick='currDayDetail("+ data[i].vendorId +','+ data[i].apiTypeId +','+ data[i].stid +")'>" + data[i].apiTypeName + "</a>" +
                                "</td>" +
                                "<td>" + data[i].price + "</td>" +
                                "<td>" + data[i].sumAmount + "</td>" +
                                /*"<td>" + data[i].countTotle + "</td>" +*/
                                "<td>" + data[i].countSuccess + "</td>" +
                                "</tr>";
                        }
                        $("#simple_vendor_curr_day_api_type_consume tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#simple_vendor_curr_day_api_type_consume tbody").append(myContent);
                }
            }else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#simple_vendor_curr_day_api_type_consume tbody").append(myContent);
            }

        }
    });
}

function currDayDetail(cid,tid,stid){

    swal({
        title: '详情',
        html:
        '<table class="table table-hover table-condensed" id="simple_1_detail">' +
        '<thead>' +
        '<tr>' +
        '<th>供应商</th>' +
        '<th>上游扣费量</th>' +
        '<th>调用缓存量</th>' +
        '</tr>' +
        '</thead>'+
        '<tbody>' +
        '</tbody>'+
        '</table>',
        showCloseButton: true,
        showCancelButton: false,
        showConfirmButton: false
    });

    $("#simple_1_detail tbody").empty();

    $.ajax({
        type: "post",
        url: "/finance/find-all-customer/curr-day-api-type-consume-detail",
        data:{"cid":cid,"tid":tid,"stid":stid},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='3'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#simple_1_detail tbody").append(myContent);
        },
        success:function (result) {
            $("#simple_1_detail tbody").empty();
            if (result != null && result.consumeList != null){
                var data = result.consumeList;
                if (data.length > 0){
                    for (var i = 0; i < data.length; i++) {
                        var myContent = "<tr>" +
                            "<td>" + data[i].vendorName + "</td>" +
                            "<td>" + data[i].resultCostCount + "</td>" +
                            "<td>" + data[i].cacheCount + "</td>" +
                            "</tr>";
                        $("#simple_1_detail tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='3'>" + '无记录' + "</td>" +
                        "</tr>";
                    $("#simple_1_detail tbody").append(myContent);
                }
            }else {
                var myContent = "<tr>" +
                    "<td rowspan='3'>" + '无记录' + "</td>" +
                    "</tr>";
                $("#simple_1_detail tbody").append(myContent);
            }
        }
    });
}

