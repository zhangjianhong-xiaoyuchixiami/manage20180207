

var oTableEditIp = null;

/*显示标签*/
function addApiTags(apiId) {

    $("#error_alert_api_tags").empty();

    $("#api_tag_list tbody").empty();

    $('#api_tag_list').dataTable().fnClearTable();

    $('#api_tag_list').dataTable().fnDestroy();

    var width =  $('#api_tag').width();

    $('#api_tag_list').width(width-35);

    $.ajax({
        type: "post",
        url: "/api-response-condition/show-api-tags",
        data: {"apiId": apiId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='3'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>"
            $("#api_tag_list tbody").append(myContent);
        },
        success: function (data) {

            $('#api_tags').html(apiId);

            $("#api_tag_list tbody").empty();

            if (data != null){
                for (var i = 0; i < data.length; i++) {
                    var myContent = "<tr>" +
                        "<td>" + data[i].apiTag + "</td>" +
                        "<td><a href='javaScript:;' class='warning' onclick='deleteTag("+ data[i].id + ")'>删除</a></td>" +
                        "</tr>"
                    $("#api_tag_list tbody").append(myContent);
                }
            }else {
                var myContent = "<tr>" +
                    "<td>该产品暂无标签</td>" +
                    "</tr>"
                $("#api_tag_list tbody").append(myContent);
            }


            oTableEditIp = $('#api_tag_list').dataTable({
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
$('#api_tags_new').live('click',function (e) {

    if ($("#ip_beginIp").length > 0){
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
    /*jqTds[0].innerHTML = '<input type="hidden" class="span9 m-wrap" id="id" name="apiTagId" placeholder="请输入要添加的标签">';*/
    jqTds[0].innerHTML = '<input type="text" class="span9 m-wrap" id="apiTag" name="apiTag" placeholder="请输入要添加的标签">';
    jqTds[1].innerHTML = '<a class="edit" href="javaScript:;">保存</a>|<a class="cancel" href="javaScript:;">取消</a>';
    $('#id').ipAddress();
}

/*取消*/
$('#api_tag_list a.cancel').live('click', function (e) {
    e.preventDefault();
    oTableEditIp.fnDeleteRow(nEditingIp);
});


/*提交保存标签*/
$('#api_tag_list a.edit').live('click', function (e) {

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

        var apiId = $('#api_tags').html();
        var apiTag = $('#apiTag').val();
        /*var id = $('#id').val();*/
        $.ajax({
            type: "post",
            url: "/api-response-condition/addApiTag",
            data: {"apiId": apiId, "apiTag": apiTag},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data.beginIpMessage != null) {
                    $("#error_alert_api_tags").empty();
                    $("#error_alert_api_tags").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.beginIpMessage + '</span>' +
                        '</div>');
                    return;
                }
                /*if (data.endIpMessage != null) {
                    $("#error_alert_customer_ip").empty();
                    $("#error_alert_customer_ip").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.endIpMessage + '</span>' +
                        '</div>');
                    return;
                }*/
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
                        '该标签已被添加',
                        'success'
                    );
                    addApiTags(apiId);
                }
                if (data.role_warning != null){
                    swal({
                        title: "操作提示",
                        text: data.role_warning,
                        type: "warning",
                        showCancelButton: false, //是否显示取消按钮
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: "确定"//确定按钮上面的文档
                    })
                }
            }
        })

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

});


/*删除标签*/
function deleteTag(id, apiId) {

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
            url: "/api-response-condition/deleteApiTag",
            data: {"id": id, "apiId":apiId},
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
                            '该标签已被删除',
                            'success'
                        );
                        addApiTags(apiId);
                        return;
                    }
                    if (data.fail != null) {
                        swal(
                            '失败',
                            '哎呦，删除失败了',
                            'error'
                        );
                    }
                    if (data.role_warning != null){
                        swal({
                            title: "操作提示",
                            text: data.role_warning,
                            type: "warning",
                            showCancelButton: false, //是否显示取消按钮
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"//确定按钮上面的文档
                        })
                    }
                }
            }
        })

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

}