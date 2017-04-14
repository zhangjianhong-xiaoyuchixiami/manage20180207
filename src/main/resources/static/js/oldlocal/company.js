/**
 * Created by jonhn on 2017/3/20.
 */

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

/*显示Ip*/
function showIp(id) {
    if($("#customerIpContent_"+id).css('display')=='none'){

        $("#customerIpContent_"+id).show();
    }else{
        $("#customerIpContent_"+id).hide();
    }
}