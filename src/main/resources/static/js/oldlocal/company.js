/**
 * Created by jonhn on 2017/3/20.
 */

/*以下操作为添加账号*/
function addAccount(companyId) {
    $("#authId-account-controls").empty();
    $("#error-alert-account").empty();
    var op=document.createElement("input");
    op.value=companyId;
    op.type="text";
    op.id="companyId";
    op.name="companyId";
    $("#authId-account-controls").append(op);
}
/*以下操作是给账号充值或扣费*/
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