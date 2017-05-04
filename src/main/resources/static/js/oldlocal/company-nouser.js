


/*以下操作为添加账号-赋值公司Id*/
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

/*以下操作为添加账号-获得焦点清空文本框*/
$("#authId-account").focus(function () {
    $("#authId-accountMsg").html("");
});

/*以下操作为添加账号-失去焦点验证authId*/
$("#authId-account").blur(function(){
    $("#authId-accountMsg").load("/customer/findCustomerByAuthId/"+$("#authId-account").val(),
        function(responseTxt){
            if(responseTxt=="yes")
                $("#authId-accountMsg").html("<font color='red'>该账号已被使用，请重新输入！</font>");
            if(responseTxt=="no")
                $("#authId-accountMsg").html("");
        });
});

/*以下操作为添加账号-点击提交按钮提交数据*/
$("#add-account-btn-black-btn-primary").on("click",function () {
    var companyId=$("#companyId").val();
    var authId=$("#authId-account").val();
    $.ajax({
        type: "post",
        url: "/company/add-customer-account",
        data: {"companyId":companyId,"authId":authId},
        dataType: "json",
        success: function (result) {
            if(result.authIdMessage != null){
                $("#authId-accountMsg").empty();
                $("#authId-accountMsg").html('<font color="red">'+result.authIdMessage+'</font>');
                return;
            }
            if(result.errorMessage != null) {
                $("#error-alert-account").empty();
                $("#error-alert-account").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                return;
            }
            if (result.successMessage != null){
                alert("操作成功");
                window.location.href=window.location.href
            }
        }
    });
});

/*以下操作是新增公司-获得焦点清空文本框*/
$("#companyCustomerName").focus(function () {
    $("#companyNameMsg").html("");
});

/*以下操作是新增公司-获得焦点清空文本框*/
$("#authId").focus(function () {
    $("#authIdMsg").html("");
});

/*以下操作是新增公司-获得焦点清空文本框*/
$("#deptId").focus(function () {
    $("#deptIdMsg").html("");
});

/*以下操作是新增公司-失去焦点验证authId*/
$("#authId").blur(function(){
    $("#authIdMsg").load("/customer/findCustomerByAuthId/"+$("#authId").val(),
        function(responseTxt){
            if(responseTxt=="yes")
                $("#authIdMsg").html("<font color='red'>该账号已被使用，请重新输入！</font>");
            if(responseTxt=="no")
                $("#authIdMsg").html("");
        });
});

/*以下操作是新增公司-点击提交按钮提交数据*/
$("#add-btn-black-btn-primary").on("click",function () {
    var companyCustomerName=$("#companyCustomerName").val();
    var authId=$("#authId").val();
    var partnerId=$("#partnerId").val();
    var deptId=$("#deptId").val();
    $.ajax({
        type: "post",
        url: "/company/add-company-customer",
        data: {"companyName":companyCustomerName,"authId":authId,"partnerId":partnerId,"deptId":deptId},
        dataType: "json",
        success: function (result) {
            if(result.companyNameMessage != null){
                $("#companyNameMsg").empty();
                $("#companyNameMsg").html('<font color="red">'+result.companyNameMessage+'</font>');
                return;
            }
            if(result.authIdMessage != null){
                $("#authIdMsg").empty();
                $("#authIdMsg").html('<font color="red">'+result.authIdMessage+'</font>');
                return;
            }
            if(result.deptMessage != null){
                $("#deptIdMsg").empty();
                $("#deptIdMsg").html('<font color="red">'+result.deptMessage+'</font>');
                return;
            }
            if(result.errorMessage != null) {
                $("#error-alert").empty();
                $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                return;
            }
            if (result.successMessage != null){
                alert("操作成功");
                window.location.href=window.location.href
            }
        }
    });
});