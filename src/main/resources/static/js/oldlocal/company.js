

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
        alert("请先选择要禁用的公司")
    }else {
        $.ajax({
            type:'post',
            url:"/company/ban",
            data:{"companyId": companyId},
            dataType:'json',
            success:function(data){
                if (data != null){
                    if (data.fail != null){
                        alert(data.fail);
                        window.location.href=window.location.href;
                        return;
                    }
                    alert("禁用成功");
                    window.location.href=window.location.href;
                }
            }
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
        alert("请先选择要启用的公司")
    }else {
        $.ajax({
            type:'post',
            url:"/company/unban",
            data:{"companyId": companyId},
            dataType:'json',
            success:function(data){
                if (data != null){
                    if (data.fail != null){
                        alert(data.fail);
                        window.location.href=window.location.href;
                        return;
                    }
                    alert("禁用成功");
                    window.location.href=window.location.href;
                }
            }
        });
    }
});

/*禁用账号*/
function banCustomer(authId) {
    $.ajax({
        type: "post",
        url: "/company/customer/ban",
        data: {"authId": authId},
        dataType: "json",
        success: function (data) {
            if (data != null){
                if (data.success != null){
                    alert("禁用成功");
                    window.location.href=window.location.href;
                    return;
                }
                if (data.error != null){
                    alert("禁用失败");
                }
            }
        }
    })
}

/*解禁账号*/
function unBanCustomer(authId) {
    $.ajax({
        type: "post",
        url: "/company/customer/unban",
        data: {"authId": authId},
        dataType: "json",
        success: function (data) {
            if (data != null){
                if (data.success != null){
                    alert("启用成功");
                    window.location.href=window.location.href;
                    return;
                }
                if (data.error != null){
                    alert("启用失败");
                }
            }
        }
    })
}