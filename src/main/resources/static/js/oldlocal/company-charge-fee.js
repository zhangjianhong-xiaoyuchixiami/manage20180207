

/*以下操作是给账号充值或扣费*/
/*充值-绑定customerId和充值理由*/
function chargeBalance(customerId) {
    $.ajax({
        type: "post",
        url: "/company/charge-customer-balance",
        dataType: "json",
        success: function (data) {
            $("#error_alert_update_balance").empty();
            $("#update_balance_customerId").html(customerId);
            $("#update_balance_amountMsg").empty();
            if(data != null){
                $("#update_balance_reasonId ").empty();
                $("#update_balance_reasonId").append("<option value=''>请选择...</option>");
                for (var i=0; i<data.length; i++){
                    var opSelect=document.createElement("option");
                    opSelect.value=data[i].id;
                    opSelect.innerHTML=data[i].name;
                    $("#update_balance_reasonId").append(opSelect);
                }
            }
        }
    });
}

/*扣费-绑定customerId和扣费理由*/
function consumeBalance(customerId) {
    $.ajax({
        type: "post",
        url: "/company/consume-customer-balance",
        dataType: "json",
        success: function (data) {
            $("#error_alert_update_balance").empty();
            $("#update_balance_customerId").empty();
            $("#update_balance_amountMsg").empty();
            var op=document.createElement("input");
            op.value=customerId;
            op.type="text";
            op.id="customerId";
            op.name="customerId";
            $("#authId-account-controls").append(op);
            if(data != null){
                $("#update_balance_reasonId ").empty();
                $("#update_balance_reasonId").append("<option value=''>请选择...</option>");
                for (var i=0; i<data.length; i++){
                    var opSelect=document.createElement("option");
                    opSelect.value=data[i].id;
                    opSelect.innerHTML=data[i].name;
                    $("#update_balance_reasonId").append(opSelect);
                }
            }
        }
    });
}

/*以下操作是给账号充值或扣费-获得焦点清空文本框*/
$("#update_balance_amount").focus(function () {
    $("#update_balance_amountMsg").html("");
});

/*以下操作是给账号充值或扣费-获得焦点清空文本框*/
$("#update_balance_reasonId").focus(function () {
    $("#update_balance_reasonIdMsg").html("");
});

/*以下操作是给账号充值或扣费-点击提交按钮提交数据*/
$("#update-balance-btn-black-btn-primary").on("click",function () {


    swal({
        title: "确定要充值吗？",   //弹出框的title
        type: "question",    //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        allowOutsideClick: false,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "确定充值"//确定按钮上面的文档
    }).then(function () {

        var customerId=$("#update_balance_customerId").html();
        var amount=$("#update_balance_amount").val();
        var reason=$("#update_balance_reasonId").val();

        $.ajax({
            type: "post",
            url: "/company/update-customer-balance",
            data: {"customerId":customerId,"amount":amount,"reason":reason},
            dataType: "json",
            beforeSend:function () {
                openProgress();
            },
            success: function (result) {
                closeProgress();
                if(result.amountMessage != null){
                    $("#update_balance_amountMsg").empty();
                    $("#update_balance_amountMsg").html('<font color="red">'+result.amountMessage+'</font>');
                    return;
                }
                if(result.reasonMessage != null){
                    $("#update_balance_reasonIdMsg").empty();
                    $("#update_balance_reasonIdMsg").html('<font color="red">'+result.reasonMessage+'</font>');
                    return;
                }
                if(result.errorMessage != null) {
                    swal({
                        title: "失败",
                        text: "哎呦，充值失败了",
                        type: "error",
                        showCancelButton: false,
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: "确定"
                    }).then(function () {
                        return;
                    })
                }
                if(result.successMessage != null){
                    swal({
                        title: "成功",
                        text: "充值成功",
                        type: "success",
                        showCancelButton: false, //是否显示取消按钮
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: "确定"//确定按钮上面的文档
                    }).then(function () {
                        location.reload();
                    })
                }
            }
        });

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

});