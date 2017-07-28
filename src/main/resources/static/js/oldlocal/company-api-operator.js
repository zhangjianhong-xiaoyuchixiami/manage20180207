
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
                "<td rowspan='5'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
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
                        myContent = "<tr>" +
                            "<td>" + data[i].type_stid_name + "</td>" +
                            "<td><a href='javaScript:;' onclick='updatePrice("+companyId+','+ data[i].apiTypeId +','+ data[i].subTypeId +','+ data[i].price +")'>"+ (data[i].price / 100.0) +"</a></td>" +
                            "<td><a href='javaScript:;' onclick='updateAppointApi("+companyId+','+ data[i].apiTypeId +','+ data[i].subTypeId +','+ data[i].price +','+ data[i].apiId +")'>" + data[i].btypeName + "</a></td>" +
                            "<td>正在使用</td>" +
                            "<td><a class='warning' href='javaScript:;' onclick='banCompanyApi(" + data[i].id + ',' + companyId + ")'>禁用</a></td>" +
                            "</tr>";
                        $("#simple_company_api_1 tbody").append(myContent);
                    }else {
                        myContent = "<tr>" +
                            "<td class='font-text-decoration'>" + data[i].type_stid_name + "</td>" +
                            "<td><a href='javaScript:;' onclick='updatePrice("+companyId+','+ data[i].apiTypeId +','+ data[i].subTypeId +','+ data[i].price +")'>"+ (data[i].price / 100.0) +"</a></td>" +
                            "<td><a href='javaScript:;' onclick='updateAppointApi("+companyId+','+ data[i].apiTypeId +','+ data[i].subTypeId +','+ data[i].price +','+ data[i].apiId +")'>" + data[i].btypeName + "</a></td>" +
                            "<td>已禁用</td>" +
                            "<td><a href='javaScript:;' onclick='unBanCompanyApi(" + data[i].id + ',' + companyId + ")'>启用</a></td>" +
                            "</tr>";
                        $("#simple_company_api_1 tbody").append(myContent);
                    }

                }
            }

            oTableEdit = $('#simple_company_api_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
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
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据"
                }
            });

        }
    })
}

var nEditing = null;

/*新增*/
$('#simple_company_api_1_new').on('click',function (e) {

    var apiType_companyId = $('#apiType_companyId').html();

    $.ajax({
        type: "post",
        url: "/company/query-not-have-api",
        data: {"companyId": apiType_companyId},
        dataType: "json",
        success: function (data) {
            if (data == null){
                swal({
                    title: "操作提示",
                    text: "该客户已具有全部产品权限！",
                    type: "info",
                    confirmButtonText: "确定"
                });
                return;
            }
            if (data != null && data.length <= 0){
                swal({
                    title: "操作提示",
                    text: "该客户已具有全部产品权限！",
                    type: "info",
                    confirmButtonText: "确定"
                });
                return;
            }
            if ($("#apiType_price").length > 0){

                var demo = $("#apiType_price").length;

                console.log(demo)

                swal({
                    title: "操作提示",
                    text: "请先完成当前操作！",
                    type: "info",
                    confirmButtonText: "确定"
                });
                return;
            }else {
                e.preventDefault();
                var aiNew = oTableEdit.fnAddData(['', '', '', '','']);
                var nRow = oTableEdit.fnGetNodes(aiNew[0]);
                editRow(oTableEdit, nRow);
                nEditing = nRow;
            }
        }
    });

});

/*新增--编辑*/
function editRow(oTableEdit, nRow) {

    var jqTds = $('>td', nRow);
    var apiType_companyId = $('#apiType_companyId').html();

    $.ajax({
        type: "post",
        url: "/company/query-not-have-api",
        data: {"companyId": apiType_companyId},
        dataType: "json",
        success: function (data) {

            jqTds[0].innerHTML = '<select class="medium m-wrap" id="apiTypeId_subTypeId" name="apiTypeId_subTypeId">' +
                '<option value=""></option></select>';
            jqTds[1].innerHTML = '<input type="text" class="span9 m-wrap" id="apiType_price" name="apiType_price" placeholder="单位：元">';
            jqTds[2].innerHTML = '<select class="medium m-wrap" id="tid_stid_aid" name="tid_stid_aid">' +
                '<option value=""></option></select>';
            jqTds[3].innerHTML = '';
            jqTds[4].innerHTML = '<a class="save" href="javaScript:;">保存</a>&nbsp;|&nbsp;<a class="cancel" href="javaScript:;">取消</a>';

            if (data != null){
                var myContent = null;
                for (var i = 0; i < data.length; i++){
                    if (data[i].mobileOperatorName == null){
                        myContent="<option value='"+ data[i].apiTypeId +"'>"+ data[i].apiTypeName +"</option>";
                    }else{
                        myContent="<option value='"+data[i].apiTypeId+'-'+data[i].mobileOperatorId+"'>"+ data[i].apiTypeName+'--'+data[i].mobileOperatorName +"</option>";
                    }
                    $('#apiTypeId_subTypeId').append(myContent);
                }
            }

            $("#apiTypeId_subTypeId").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#tid_stid_aid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            });

        }
    });
}

/*新增--级联*/
$("#apiTypeId_subTypeId").live("change",function () {
    var param = $("#apiTypeId_subTypeId").val();
    $("#tid_stid_aid ").empty();
    $("#tid_stid_aid ").append('<option value=""></option>');
    if (param !=null) {
        $.ajax({
            url: '/company/query-api-by-typeId',
            data: {"tid_stid": param},
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if(data != null){
                    for (var i=0; i<data.length; i++){
                        var op=document.createElement("option");
                        op.value=data[i].id;
                        op.innerHTML=data[i].name;
                        $("#tid_stid_aid").append(op);
                    }
                }
            }
        });
    }

    $("#tid_stid_aid").select2({

        language: "zh-CN",
        placeholder: "请选择",
        allowClear: true

    });


});

/*新增-取消*/
$('#simple_company_api_1 a.cancel').live('click', function (e) {
    e.preventDefault();
    oTableEdit.fnDeleteRow(nEditing);
    nEditing = null;

});

/*新增-保存*/
$('#simple_company_api_1 a.save').live('click', function (e) {

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

        var apiType_companyId = $('#apiType_companyId').html();
        var apiTypeId_subTypeId = $('#apiTypeId_subTypeId').val();
        var apiType_price = $('#apiType_price').val();
        var tid_stid_aid = $('#tid_stid_aid').val();

        $.ajax({
            type: "post",
            url: "/company/add-company-api",
            data: {"companyId": apiType_companyId, "apiTypeId": apiTypeId_subTypeId, "price": apiType_price,"aid": tid_stid_aid},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
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
                    swal(
                        '失败',
                        '哎呦，添加失败了',
                        'error'
                    );
                    return;
                }
                if (data.successMessage != null) {
                    swal(
                        '成功',
                        '该权限已被添加',
                        'success'
                    );
                    findCompanyApi(apiType_companyId);
                }
            }
        });

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

});


/*禁用产品权限*/
function banCompanyApi(id, companyId) {

    swal({
        title: "确定要禁用吗？",   //弹出框的title
        type: "warning",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        allowOutsideClick: false,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定禁用！"//确定按钮上面的文档
    }).then(function () {

        $.ajax({
            type: "post",
            url: "/company/ban-api",
            data: {"companyId": companyId, "id": id},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data != null) {
                    if (data.success != null) {
                        swal(
                            '成功',
                            '该权限已被禁用',
                            'success'
                        );
                        findCompanyApi(companyId);
                        return;
                    }
                    if (data.error != null) {
                        swal(
                            '失败',
                            '哎呦，禁用失败了',
                            'error'
                        );
                    }
                }
            }
        });
    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

}

/*启用产品权限*/
function unBanCompanyApi(id,companyId) {

    swal({
        title: "确定要启用吗？",   //弹出框的title
        type: "warning",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        allowOutsideClick: false,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定启用！"//确定按钮上面的文档
    }).then(function () {

        $.ajax({
            type: "post",
            url: "/company/unban-api",
            data: {"companyId": companyId, "id": id},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data != null) {
                    if (data.success != null) {
                        swal(
                            '成功',
                            '该权限已被启用',
                            'success'
                        );
                        findCompanyApi(companyId);
                        return;
                    }
                    if (data.error != null) {
                        swal(
                            '失败',
                            '哎呦，启用失败了',
                            'error'
                        );
                    }
                }
            }
        });
    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });
}

/*修改价格*/
function updatePrice(companyId,typeId,stid,price) {
    var pic = price/100.0 ;
    swal({
        title: '修改产品价格',
        input: 'text',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",
        confirmButtonText: "确定修改",
        allowOutsideClick: true,
        inputValue: pic,
        inputValidator: function(value) {
            return new Promise(function(resolve, reject) {
                var re =new RegExp("^(-?\\d+)(\\.\\d+)?$");
                if(!re.test(value)){
                    reject('格式输入不正确！');
                } else {
                    resolve();
                }
            });
        }
    }).then(function (value) {

        $.ajax({
            type: "post",
            url: "/company/mid-company-api-price",
            data: {"companyId": companyId,"tid": typeId,"stid": stid,"pic": value},
            dataType: "json",
            beforeSend: function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data != null) {
                    if (data.success != null) {
                        swal({
                            type: 'success',
                            title: '价格修改完成',
                            confirmButtonText: "确定",
                            html: '已将价格修改为：' + value
                        }).then(function () {
                            findCompanyApi(companyId);
                        });

                    }
                    if (data.fail != null) {

                        swal({
                            type: 'error',
                            title: '失败',
                            text: "哎呦，修改失败了",
                            confirmButtonText: "确定"
                        })
                    }
                }
            }
        });

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });
}

/*修改指定产品*/
function updateAppointApi(companyId,typeId,stid,price,aid) {

    $.ajax({
        type: "post",
        url: "/company/query-appoint-api-by-type",
        data: {"tid": typeId},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                swal({
                    title: '修改指定产品',
                    input: 'select',
                    inputOptions: data,
                    inputPlaceholder: '请选择',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    cancelButtonText: "取消",
                    confirmButtonText: "确定",
                    allowOutsideClick: true,
                    inputValidator: function(value) {
                        return new Promise(function(resolve, reject) {
                            if(value){
                                resolve();
                            } else {
                                reject('请选择指定产品！');
                            }
                        });
                    }
                }).then(function (value) {

                    $.ajax({
                        type: "post",
                        url: "/company/mid-company-api-appoint-api",
                        data: {"companyId": companyId,"tid": typeId,"stid": stid,"pic": price,"aid": value},
                        dataType: "json",
                        beforeSend: function () {
                            openProgress();
                        },
                        success: function (data) {
                            closeProgress();
                            if (data != null) {
                                if (data.success != null) {
                                    swal({
                                        type: 'success',
                                        title: '成功',
                                        text: "指定产品修改完成",
                                        confirmButtonText: "确定"
                                    }).then(function () {
                                        findCompanyApi(companyId);
                                    });

                                }
                                if (data.fail != null) {

                                    swal({
                                        type: 'error',
                                        title: '失败',
                                        text: "哎呦，修改失败了",
                                        confirmButtonText: "确定"
                                    })
                                }
                            }
                        }
                    });

                },function(dismiss) {
                    // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                    if (dismiss === 'cancel') {}
                });

            }
        }
    });
}




