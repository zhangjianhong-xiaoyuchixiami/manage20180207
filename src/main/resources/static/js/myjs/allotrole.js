$(document).ready(function(){

    $('#allotRoleSave').on("click",function(){
        //jquery获取复选框值
        var roleId_id =[];//定义一个数组
        $('input[name="roleId"]:checked').each(function(){
            roleId_id.push($.trim($(this).val()));
        });
        var username_id = $('input[name="username"]').val();
        var indata = {"roleId":roleId_id,"username":username_id};
        $.ajax({
            type:'post',
            url:"/role/allotRoleAction",
            data:indata,
            dataType:'json',
            success:function(data){
                if(data!=null && data.result=="ok"){
                    alert("操作成功");
                    window.location.href="/role/allotRoleView/"+data.msg;
//                        $('#form_modal4').css('display','block');
//                        $('#tiShiXinXi').html("操作成功");
                }else{
                    alert("操作失败");
                    window.location.href="/role/allotRoleView/"+data.msg;
//                        $('#form_modal4').css('display','block');
//                        $('#tiShiXinXi').html("操作失败");
                }
            }
        });
    });
});