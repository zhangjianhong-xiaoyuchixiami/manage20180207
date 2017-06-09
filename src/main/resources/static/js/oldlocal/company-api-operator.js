
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
        success: function (data) {

            $('#apiType_companyId').html(companyId);

            if (data != null){
                for (var i = 0; i < data.length; i++) {
                    var myContent;
                    if (data[i].enabled != 0) {
                        if (data[i].mobileOperator == null) {
                            myContent = "<tr>" +
                                "<td style='width: 40%'>" + data[i].apiType.name + "</td>" +
                                "<td style='width: 25%'>" + (data[i].price / 100.0) + "</td>" +
                                "<td style='width: 15%'>正在使用</td>" +
                                "<td style='width: 20%'>" +
                                "<a class='edit' href='javaScript:;'>修改</a>" + "&nbsp;|&nbsp;" +
                                "<a class='warning' href='javaScript:;' onclick='banCompanyApi(" + data[i].id + ',' + companyId + ")'>禁用</a>" +
                                "</td>" +
                                "</tr>"
                        } else {
                            myContent = "<tr>" +
                                "<td style='width: 40%'>" + data[i].apiType.name + "--" + data[i].mobileOperator.name + "</td>" +
                                "<td style='width: 25%'>" + (data[i].price / 100.0) + "</td>" +
                                "<td style='width: 15%'>正在使用</td>" +
                                "<td style='width: 20%'>" +
                                "<a class='edit' href='javaScript:;'>修改</a>" + "&nbsp;|&nbsp;" +
                                "<a class='warning' href='javaScript:;' onclick='banCompanyApi(" + data[i].id + ',' + companyId + ")'>禁用</a>" +
                                "</td>" +
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

var nEditing = null;

/*新增*/
$('#simple_company_api_1_new').live('click',function (e) {

    var apiType_companyId = $('#apiType_companyId').html();

    $.ajax({
        type: "post",
        url: "/company/query-not-have-api",
        data: {"companyId": apiType_companyId},
        dataType: "json",
        success: function (data) {
            if (data == null){
                alert("该客户已具有全部产品权限！");
                return;
            }
            if (data != null && data.length <= 0){
                alert("该客户已具有全部产品权限！");
                return;
            }
            if ($("#simple_company_api_1 input").attr('id')){
                alert("请先完成当前操作！");
                return;
            }else {
                e.preventDefault();
                var aiNew = oTableEdit.fnAddData(['', '', '', '']);
                var nRow = oTableEdit.fnGetNodes(aiNew[0]);
                editRow(oTableEdit, nRow);
                nEditing = nRow;
            }
        }
    });
});

/*编辑*/
function editRow(oTableEdit, nRow) {

    var jqTds = $('>td', nRow);
    var apiType_companyId = $('#apiType_companyId').html();
    $.ajax({
        type: "post",
        url: "/company/query-not-have-api",
        data: {"companyId": apiType_companyId},
        dataType: "json",
        success: function (data) {
            jqTds[0].innerHTML = '<select class="m-wrap small" id="apiTypeId_subTypeId" name="apiTypeId_subTypeId">' +
                '<option value="">请选择</option></select>';
            jqTds[1].innerHTML = '<input type="text" class="m-wrap small" id="apiType_price" name="apiType_price" placeholder="单位：元">';
            jqTds[2].innerHTML = '';
            jqTds[3].innerHTML = '<a class="save" href="javaScript:;">保存</a>&nbsp;|&nbsp;<a class="cancel" href="javaScript:;">取消</a>';

            if (data != null){
                var myContent = null;
                for (var i = 0; i < data.length; i++){
                    if (data[i].mobileOperatorName == null){
                        myContent="<option value='"+ data[i].apiTypeId +"'>"+ data[i].apiTypeName +"</option>";
                    }else{
                        myContent="<option value='"+data[i].apiTypeId+','+data[i].mobileOperatorId+"'>"+ data[i].apiTypeName+'--'+data[i].mobileOperatorName +"</option>";
                    }
                    $('#apiTypeId_subTypeId').append(myContent);
                }
            }
        }
    });

}


function restoreRow(oTableEdit, nRow) {
    var aData = oTableEdit.fnGetData(nRow);
    var jqTds = $('>td', nRow);

    for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
        oTableEdit.fnUpdate(aData[i], nRow, i, false);
    }
    oTableEdit.fnDraw();
}

/*编辑*/
function editRowPrice(oTableEdit, nRow) {
    var aData = oTableEdit.fnGetData(nRow);
    var jqTds = $('>td', nRow);
    jqTds[0].innerHTML = aData[0];
    jqTds[1].innerHTML = '<input type="text" class="m-wrap small" value="' + aData[1] + '">';
    jqTds[2].innerHTML = aData[2];
    jqTds[3].innerHTML = '<a class="savePrice" href="javaScript:;">保存</a>&nbsp;|&nbsp;<a class="cancelPrice" href="javaScript:;">取消</a>';
}


/*取消*/
$('#simple_company_api_1 a.cancel').live('click', function (e) {
    e.preventDefault();
    oTableEdit.fnDeleteRow(nEditing);
    nEditing = null;

});

/*取消*/
$('#simple_company_api_1 a.cancelPrice').live('click', function (e) {
    e.preventDefault();
    restoreRow(oTableEdit, nEditing);
    nEditing = null;
});

/*保存*/
$('#simple_company_api_1 a.save').live('click', function (e) {
    var apiType_companyId = $('#apiType_companyId').html();
    var apiTypeId_subTypeId = $('#apiTypeId_subTypeId').val();
    var apiType_price = $('#apiType_price').val();
    if (confirm("确定要执行当前操作吗？")) {
        $.ajax({
            type: "post",
            url: "/company/add-company-api",
            data: {"companyId": apiType_companyId, "apiTypeId": apiTypeId_subTypeId, "price": apiType_price},
            dataType: "json",
            success: function (data) {
                if (data.apiTypeIdMessage != null) {
                    $("#error_alert_company_api").empty();
                    $("#error_alert_company_api").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.apiTypeIdMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.priceMessage != null) {
                    $("#error_alert_company_api").empty();
                    $("#error_alert_company_api").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.priceMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.errorMessage != null) {
                    $("#error_alert_company_api").empty();
                    $("#error_alert_company_api").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.errorMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.successMessage != null) {
                    $("#error_alert_company_api").empty();
                    findCompanyApi(apiType_companyId);
                }
            }
        })
    }
});


$('#simple_company_api_1 a.edit').live('click', function (e) {
    e.preventDefault();
    var nRow = $(this).parents('tr')[0];
    if (nEditing !== null && nEditing != nRow) {
        restoreRow(oTableEdit, nEditing);
        editRowPrice(oTableEdit, nRow);
        nEditing = nRow;
    }else {
        editRowPrice(oTableEdit, nRow);
        nEditing = nRow;
    }
});

$('#simple_company_api_1 a.savePrice').live('click', function (e) {
    e.preventDefault();
    var nRow = $(this).parents('tr')[0];
    saveRow(oTableEdit, nRow);
    nEditing = nRow;
});


function saveRow(oTableEdit, nRow) {
    var aData = oTableEdit.fnGetData(nRow);
    var jqInputs = $('input', nRow);

    console.log(aData[0]);
    console.log(jqInputs[0].value);
    console.log(aData[2]);
    console.log(aData[3]);

    var apiType_companyId = $('#apiType_companyId').html();
    var apiType_stid = aData[0];
    var price = jqInputs[0].value;

    if (confirm("确定要执行当前操作吗？")) {
        $.ajax({
            type: "post",
            url: "/company//mod-company-api-price",
            data: {"companyId": apiType_companyId, "apiTypeId": apiType_stid, "price": price},
            dataType: "json",
            success: function (data) {
                if (data.priceMessage != null) {
                    $("#error_alert_company_api").empty();
                    $("#error_alert_company_api").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.priceMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.errorMessage != null) {
                    $("#error_alert_company_api").empty();
                    $("#error_alert_company_api").append(
                        '<div class="alert alert-error show">' +
                        '<button class="close" data-dismiss="alert"></button>' +
                        '<span>' + data.errorMessage + '</span>' +
                        '</div>');
                    return;
                }
                if (data.successMessage != null) {
                    $("#error_alert_company_api").empty();
                    findCompanyApi(apiType_companyId);
                }
            }
        })
    }
}

/*禁用产品权限*/

function banCompanyApi(id, companyId) {
    if (confirm("确定要执行当前操作吗？")) {
        $.ajax({
            type: "post",
            url: "/company/ban-api",
            data: {"companyId": companyId, "id": id},
            dataType: "json",
            success: function (data) {
                if (data != null) {
                    if (data.success != null) {
                        alert("禁用成功！");
                        findCompanyApi(companyId);
                        return;
                    }
                    if (data.error != null) {
                        alert("禁用失败！");
                        findCompanyApi(companyId);
                    }
                }


            }
        })
    }
}

/*启用产品权限*/
function unBanCompanyApi(id,companyId) {
    $.ajax({
        type: "post",
        url: "/company/unban-api",
        data: {"id": id},
        dataType: "json",
        success: function () {
            findCompanyApi(companyId)
        }
    })
}




