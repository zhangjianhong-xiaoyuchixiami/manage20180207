

var oTableEditIp = null;

/*显示Ip*/
function showIp(customerId) {

    $("#error_alert_customer_ip").empty();

    $("#simple_customer_ip_1 tbody").empty();

    $('#simple_customer_ip_1').dataTable().fnClearTable();

    $('#simple_customer_ip_1').dataTable().fnDestroy();

    var width =  $('#form_modal_customer_ip_list').width();

    $('#simple_customer_ip_1').width(width-35);

    $.ajax({
        type: "post",
        url: "/company/customer/find-ip",
        data: {"customerId": customerId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='2'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>"
            $("#simple_customer_ip_1 tbody").append(myContent);
        },
        success: function (data) {

            $('#ip_customerId').html(customerId);

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


var nEditingIp = null;

/*新增*/
$('#simple_customer_ip_1_new').live('click',function (e) {

    if ($("#simple_customer_ip_1 input").attr('id')){
        alert("请先完成当前操作！");
        return;
    }else {
        e.preventDefault();
        var aiNew = oTableEditIp.fnAddData(['', '', '']);
        var nRow = oTableEditIp.fnGetNodes(aiNew[0]);
        editRowIp(oTableEditIp, nRow);
        nEditingIp = nRow;
    }

});

/*编辑*/
function editRowIp(oTableEditIp,nRow) {
    var jqTds = $('>td', nRow);
    jqTds[0].innerHTML = '<input type="text" class="m-wrap small" id="ip_beginIp" name="ip_beginIp" placeholder="请输入起始Ip">';
    jqTds[1].innerHTML = '<input type="text" class="m-wrap small" id="ip_endIp" name="ip_endIp" placeholder="请输入结束Ip">';
    jqTds[2].innerHTML = '<a class="edit" href="javaScript:;">保存</a>|<a class="cancel" href="javaScript:;">取消</a>';
    $('#ip_beginIp').ipAddress();
    $('#ip_endIp').ipAddress();
}

/*取消*/
$('#simple_customer_ip_1 a.cancel').live('click', function (e) {
    e.preventDefault();
    oTableEditIp.fnDeleteRow(nEditingIp);
});

/*保存*/
$('#simple_customer_ip_1 a.edit').live('click', function (e) {

    if (confirm("确定要执行当前操作吗？")) {
        var ip_customerId = $('#ip_customerId').html();
        var ip_beginIp = $('#ip_beginIp').val();
        var ip_endIp = $('#ip_endIp').val();
        $.ajax({
            type: "post",
            url: "/company/customer/add/ip",
            data: {"customerId": ip_customerId, "beginIp": ip_beginIp, "endIp": ip_endIp},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data.beginIpMessage != null) {
                    $("#error_alert_customer_ip").empty();
                    $("#error_alert_customer_ip").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.beginIpMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.endIpMessage != null) {
                    $("#error_alert_customer_ip").empty();
                    $("#error_alert_customer_ip").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.endIpMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.fail != null) {
                    $("#error_alert_customer_ip").empty();
                    $("#error_alert_customer_ip").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.fail + '</span>' +
                        '</div>');
                    return;
                }
                if (data.success != null) {
                    alert("操作成功");
                    $("#error_alert_customer_ip").empty();
                    showIp(ip_customerId);
                }
            }
        })
    }

});


/*删除Ip*/
function deleteIp(id,customerId) {

    if (confirm("确定要执行当前操作吗？")) {
        $.ajax({
            type: "post",
            url: "/company/customer/delete-ip",
            data: {"customerId": customerId, "id": id},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data != null) {
                    if (data.success != null) {
                        alert("操作成功");
                        showIp(customerId);
                        return;
                    }
                    alert("操作失败！");
                }

            }
        })
    }
}