


$(function () {
    $("[data-toggle='tooltip']").tooltip();
});

/*预览邮件*/
$("#previewCheckBoxCustomerFinanceCustomerId").on('click',function () {

    var customerId =[];//定义一个数组
    $('input[name="checkBoxCustomerFinanceCustomerId"]:checked').each(function(){
        customerId.push($.trim($(this).val()));
    });

    if (customerId == null || customerId == ""){
        swal({
            title: "操作提示",
            text: "请先选择要预览的公司！",
            type: "info",
            confirmButtonText: "确定"
        });
    }else {

        swal({
            title: "操作提示",  //弹出框的title
            text: "确定要发送预览邮件到当前登陆者邮箱吗？",
            type: "question",    //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            allowOutsideClick: false,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "确定"//确定按钮上面的文档
        }).then(function () {

            $.ajax({
                type:'post',
                url:"/email/preview-customer-finance-account",
                data:{"customerId": customerId},
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
                            })
                        }
                        if (data.success != null){
                            swal({
                                title: "操作提示",
                                text: data.success,
                                type: "success",
                                showCancelButton: false, //是否显示取消按钮
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: "确定"//确定按钮上面的文档
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


/*发送客户邮件*/
$("#sendCheckBoxCustomerFinanceCustomerId").on('click',function () {

    var customerId =[];//定义一个数组
    $('input[name="checkBoxCustomerFinanceCustomerId"]:checked').each(function(){
        customerId.push($.trim($(this).val()));
    });

    if (customerId == null || customerId == ""){
        swal({
            title: "操作提示",
            text: "请先选择要发送的公司！",
            type: "info",
            confirmButtonText: "确定"
        });
    }else {

        swal({
            title: "操作提示",  //弹出框的title
            text: "确定要发送邮件吗？",
            type: "question",    //弹出框类型
            showCancelButton: true, //是否显示取消按钮
            allowOutsideClick: false,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: "取消",//取消按钮文本
            confirmButtonText: "确定"//确定按钮上面的文档
        }).then(function () {

            $.ajax({
                type:'post',
                url:"/email/send-customer-finance-account",
                data:{"customerId": customerId},
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
                            })
                        }
                        if (data.success != null){
                            swal({
                                title: "操作提示",
                                text: data.success,
                                type: "success",
                                showCancelButton: false, //是否显示取消按钮
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: "确定"//确定按钮上面的文档
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
