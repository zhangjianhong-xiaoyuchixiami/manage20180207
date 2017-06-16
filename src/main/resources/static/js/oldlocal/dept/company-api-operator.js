
var oTableEdit = null;

/*产品权限管理*/
function findCompanyApi(companyId) {

    $("#error_alert_company_api").empty();

    $("#simple_company_api_1 tbody").empty();

    $('#simple_company_api_1').dataTable().fnClearTable();

    $('#simple_company_api_1').dataTable().fnDestroy();

    var width =  $('#form_modal_company_api_status').width();

    $('#simple_company_api_1').width(width-35);

    $.ajax({
        type: "post",
        url: "/company/find-company-api",
        data: {"companyId": companyId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='3'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>"
            $("#simple_company_api_1 tbody").append(myContent);
        },
        success: function (data) {

            $('#apiType_companyId').html(companyId);

            $("#simple_company_api_1 tbody").empty();

            if (data != null){
                for (var i = 0; i < data.length; i++) {
                    var myContent;
                    if (data[i].enabled != 0) {
                        if (data[i].mobileOperator == null) {
                            myContent = "<tr>" +
                                "<td style='width: 40%'>" + data[i].apiType.name + "</td>" +
                                "<td style='width: 25%'>" + (data[i].price / 100.0) + "</td>" +
                                "<td style='width: 15%'>正在使用</td>" +
                                "</tr>"
                        } else {
                            myContent = "<tr>" +
                                "<td style='width: 40%'>" + data[i].apiType.name + "--" + data[i].mobileOperator.name + "</td>" +
                                "<td style='width: 25%'>" + (data[i].price / 100.0) + "</td>" +
                                "<td style='width: 15%'>正在使用</td>" +
                                "</tr>"
                        }
                        $("#simple_company_api_1 tbody").append(myContent);
                    }

                }
            }

            oTableEdit = $('#simple_company_api_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
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






