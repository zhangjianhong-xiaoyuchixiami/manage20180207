$(document).ready(function() {
    $('#customerBalance').addClass('active');

    $('#changeCustomerBalance').addClass('active');

    $('#customerBalanceSelect').addClass('selected');

    $('#customerBalanceArrow').addClass('arrow open');

    $("#authId").focus(function () {
        $("#authIdMsg").html("");
    });

    $("#amount").focus(function () {
        $("#amountMsg").html("");
    });

    $("#reasonId").focus(function () {
        $("#reasonIdMsg").html("");
    });

    $("#authId").blur(function(){
        $("#authIdMsg").load("/customer/findCustomerByAuthId/"+$("#authId").val(),
            function(responseTxt){
                if(responseTxt=="yes")
                    $("#authIdMsg").html("");
                if(responseTxt=="no")
                    $("#authIdMsg").html("<font color='red'>该账户不存在！</font>");
            });
    });
});