

$(function () { $("[data-toggle='tooltip']").tooltip(); });

/*展示密码*/
function showPassword(id) {

    $("#table_password_"+id).empty();

    if($("#table_content_password_"+id).css('display')=='none'){

        $("#table_content_password_"+id).show();
    }else{
        $("#table_content_password_"+id).hide();
    }

}

/*状态正常批量禁用公司操作*/
$("#batchBanCompany").on('click',function () {

    var companyId =[];//定义一个数组
    $('input[name="checkBoxCompanyId"]:checked').each(function(){
        companyId.push($.trim($(this).val()));
    });

    if (companyId == null || companyId == ""){
        swal({
            title: "操作提示",
            text: "请先选择要禁用的公司！",
            type: "info",
            confirmButtonText: "确定"
        });
    }else {

        swal({
            title: "确定要禁用吗？",   //弹出框的title
            type: "question",    //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            allowOutsideClick: false,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "确定禁用"//确定按钮上面的文档
        }).then(function () {

            $.ajax({
                type:'post',
                url:"/company/ban",
                data:{"companyId": companyId},
                dataType:'json',
                beforeSend:function () {
                    openProgress();
                },
                success:function(data){
                    closeProgress();
                    if (data != null){
                        if (data.fail != null){
                            swal({
                                title: "操作提示",
                                text: data.fail,
                                type: "error",
                                showCancelButton: false,
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: "确定"
                            }).then(function () {
                                location.reload();
                                return;
                            })
                        }
                        if (data.success != null){
                            swal({
                                title: "操作提示",
                                text: "禁用成功",
                                type: "success",
                                showCancelButton: false, //是否显示取消按钮
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: "确定"//确定按钮上面的文档
                            }).then(function () {
                                location.reload();
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
});

/*状态禁用批量启用公司操作*/
$("#batchUnBanCompany").on('click',function () {

    var companyId =[];//定义一个数组
    $('input[name="checkUnBanBoxCompanyId"]:checked').each(function(){
        companyId.push($.trim($(this).val()));
    });

    if (companyId == null || companyId == ""){
        swal({
            title: "操作提示",
            text: "请先选择要启用的公司！",
            type: "info",
            confirmButtonText: "确定"
        });
    }else {

        swal({
            title: "确定要启用吗？",   //弹出框的title
            type: "question",    //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            allowOutsideClick: false,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "确定启用"//确定按钮上面的文档
        }).then(function () {

            $.ajax({
                type:'post',
                url:"/company/unban",
                data:{"companyId": companyId},
                dataType:'json',
                beforeSend:function () {
                    openProgress();
                },
                success:function(data){
                    closeProgress();
                    if (data != null){
                        if (data.fail != null){
                            swal({
                                title: "操作提示",
                                text: data.fail,
                                type: "error",
                                showCancelButton: false,
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: "确定"
                            }).then(function () {
                                location.reload();
                                return;
                            })
                        }
                        if (data.success != null){
                            swal({
                                title: "操作提示",
                                text: "启用成功",
                                type: "success",
                                showCancelButton: false, //是否显示取消按钮
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: "确定"//确定按钮上面的文档
                            }).then(function () {
                                location.reload();
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
});

/*禁用账号*/
function banCustomer(authId) {

    swal({
        title: "确定要禁用吗？",   //弹出框的title
        type: "question",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        allowOutsideClick: false,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定禁用"//确定按钮上面的文档
    }).then(function () {

        $.ajax({
            type: "post",
            url: "/company/customer/ban",
            data: {"authId": authId},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success:function(data){
                closeProgress();
                if (data != null) {
                    if (data.success != null){
                        swal({
                            title: "操作提示",
                            text: "禁用成功",
                            type: "success",
                            showCancelButton: false, //是否显示取消按钮
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"//确定按钮上面的文档
                        }).then(function () {
                            location.reload();
                            return;
                        })
                    }
                    if (data.error != null) {
                        swal({
                            title: "操作提示",
                            text: "哎呦，禁用失败",
                            type: "error",
                            showCancelButton: false,
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"
                        }).then(function () {
                            location.reload();
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

/*解禁账号*/
function unBanCustomer(authId) {

    swal({
        title: "确定要启用吗？",   //弹出框的title
        type: "question",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        allowOutsideClick: false,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定启用"//确定按钮上面的文档
    }).then(function () {

        $.ajax({
            type: "post",
            url: "/company/customer/unban",
            data: {"authId": authId},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success:function(data){
                closeProgress();
                if (data != null) {
                    if (data.success != null){
                        swal({
                            title: "操作提示",
                            text: "启用成功",
                            type: "success",
                            showCancelButton: false, //是否显示取消按钮
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"//确定按钮上面的文档
                        }).then(function () {
                            location.reload();
                            return;
                        })
                    }
                    if (data.error != null) {
                        swal({
                            title: "操作提示",
                            text: "哎呦，启用失败",
                            type: "error",
                            showCancelButton: false,
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"
                        }).then(function () {
                            location.reload();
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