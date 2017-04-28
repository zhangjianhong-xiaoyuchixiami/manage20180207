

/*以下操作是给账号充值或扣费*/
/*充值-绑定customerId和充值理由*/
function chargeBalance(customerId) {
    $.ajax({
        type: "post",
        url: "/company/charge-customer-balance",
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
    var customerId=$("#customerId").val();
    var amount=$("#update_balance_amount").val();
    var reason=$("#update_balance_reasonId").val();
    $.ajax({
        type: "post",
        url: "/company/update-customer-balance",
        data: {"customerId":customerId,"amount":amount,"reason":reason},
        dataType: "json",
        success: function (result) {
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
                $("#error_alert_update_balance").empty();
                $("#error_alert_update_balance").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                return;
            }
            if (result.successMessage != null){
                alert("操作成功");
                window.location.href=window.location.href
            }
        }
    });
});