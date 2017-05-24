

function currDayApiTypeConsume(customerId) {

    $("#simple_customer_curr_day_api_type_consume tbody").empty();

    $.ajax({
        type: "post",
        url: "/finance/find-all-customer/curr-day-api-type-consume",
        data:{"customerId":customerId},
        dataType: "json",
        success:function (result) {

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

            if (result != null && result.companyName != null){
                $('#customer_company_name').html(result.companyName)
            }

            /*$('#simple_customer_curr_day_api_type_consume').dataTable().fnClearTable();

            $('#simple_customer_curr_day_api_type_consume').dataTable().fnDestroy();

            var oTable = $('#simple_customer_curr_day_api_type_consume').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[4, 'desc']],
                "bFilter" : false,
                "bPaginate" : false,
                "bLengthChange" : false,
                "sDom": "rt<'row-fluid'<'span6'il><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "全文搜索：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                }
            });

            var width =  $('#form_modal_customer_curr_day_api_type_consume').width();

            $('#simple_customer_curr_day_api_type_consume').width(width-35);
*/
        }
    })
}