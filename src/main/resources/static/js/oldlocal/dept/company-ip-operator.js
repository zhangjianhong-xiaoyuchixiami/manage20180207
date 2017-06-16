

var oTableEditIp = null;

/*显示Ip*/
function showIp(companyId) {

    $("#error_alert_customer_ip").empty();

    $("#simple_customer_ip_1 tbody").empty();

    $('#simple_customer_ip_1').dataTable().fnClearTable();

    $('#simple_customer_ip_1').dataTable().fnDestroy();

    var width =  $('#form_modal_customer_ip_list').width();

    $('#simple_customer_ip_1').width(width-35);

    $.ajax({
        type: "post",
        url: "/company/customer/find-ip",
        data: {"companyId": companyId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='2'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>"
            $("#simple_customer_ip_1 tbody").append(myContent);
        },
        success: function (data) {

            $('#ip_customerId').html(companyId);

            $("#simple_customer_ip_1 tbody").empty();

            if (data != null){
                for (var i = 0; i < data.length; i++) {
                    var myContent = "<tr>" +
                        "<td>" + data[i].beginIpRaw + "</td>" +
                        "<td>" + data[i].endIpRaw + "</td>" +
                        "</tr>"
                    $("#simple_customer_ip_1 tbody").append(myContent);
                }
            }

            oTableEditIp = $('#simple_customer_ip_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    { "bSortable": false}
                ],
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

        }
    })
}


