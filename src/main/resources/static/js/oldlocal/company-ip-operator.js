

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
                "<td rowspan='3'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
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
                        "<td><a href='javaScript:;' class='warning' onclick='deleteIp("+ data[i].id +','+ customerId +")'>删除</a></td>" +
                        "</tr>"
                    $("#simple_customer_ip_1 tbody").append(myContent);
                }
            }

            oTableEditIp = $('#simple_customer_ip_1').dataTable({
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


var nEditingIp = null;

/*新增*/
$('#simple_customer_ip_1_new').live('click',function (e) {

    if ($("#simple_customer_ip_1 input").attr('id')){
        swal({
            title: "操作提示",
            text: "请先完成当前操作！",
            type: "info",
            confirmButtonText: "确定"
        });
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


/*提交保存Ip*/
$('#simple_customer_ip_1 a.edit').live('click', function (e) {

    swal({
        title: "确定要添加吗？",   //弹出框的title
        type: "question",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        allowOutsideClick: false,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定添加"//确定按钮上面的文档
    }).then(function () {

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
                    swal(
                        '失败',
                        '哎呦，添加失败了',
                        'error'
                    );
                    return;
                }
                if (data.success != null) {
                    swal(
                        '成功',
                        '该Ip已被添加',
                        'success'
                    );
                    showIp(ip_customerId);
                }
            }
        })

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

});


/*删除Ip*/
function deleteIp(id,customerId) {

    swal({
        title: "确定要删除吗？",   //弹出框的title
        type: "warning",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定删除"//确定按钮上面的文档
    }).then(function () {

        $.ajax({
            type: "post",
            url: "/company/customer/delete-ip",
            data: {"customerId": customerId, "id": id},
            dataType: "json",
            beforeSend: function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data != null) {
                    if (data.success != null) {
                        swal(
                            '成功',
                            '该Ip已被删除',
                            'success'
                        );
                        showIp(customerId);
                        return;
                    }
                    if (data.fail != null) {
                        swal(
                            '失败',
                            '哎呦，删除失败了',
                            'error'
                        );
                    }
                }
            }
        })

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

}