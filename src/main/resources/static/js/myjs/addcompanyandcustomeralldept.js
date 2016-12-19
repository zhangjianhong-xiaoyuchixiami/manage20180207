

$(document).ready(function() {
    $('#customerManage').addClass('active');

    $('#addCustomer').addClass('active');

    $('#customerManageSelect').addClass('selected');

    $('#customerManageArrow').addClass('arrow open');

    $("#customer_Name").focus(function () {
        $("#customer_NameMsg").html("");
    });

    $("#customer_authId").focus(function () {
        $("#customer_authIdMsg").html("");
    });

    $("#deptId").focus(function () {
        $("#deptIdMsg").html("");
    });

    $("#customer_authId").blur(function(){
        $("#customer_authIdMsg").load("/customer/findCustomerByAuthId/"+$("#customer_authId").val(),
            function(responseTxt){
                if(responseTxt=="yes")
                    $("#customer_authIdMsg").html("<font color='red'>该账户已存在！</font>");
                if(responseTxt=="no")
                    $("#customer_authIdMsg").html("");
            });
    });
});