$(document).ready(function(){

    $('#allotDeptSave').on("click",function(){
        //jquery获取复选框值
        var deptId_no =[];//定义一个数组
        $('input[name="deptId"]:checked').each(function(){
            deptId_no.push($.trim($(this).val()));
        });
        var userId_id = $('input[name="userId"]').val();
        var username_id = $('input[name="username"]').val();
        var indata = {"userId":userId_id, "deptId":deptId_no,"username":username_id};
        $.ajax({
            type:'post',
            url:"/dept/allotDeptAction",
            data:indata,
            dataType:'json',
            success:function(data){
                if(data!=null && data.result=="ok"){
                    alert("操作成功");
                    window.location.href="/dept/allotDeptView/"+data.msg;
//                        $('#form_modal4').css('display','block');
//                        $('#tiShiXinXi').html("操作成功");
                }else{
                    alert("操作失败");
                    window.location.href="/dept/allotDeptView/"+data.msg;
//                        $('#form_modal4').css('display','block');
//                        $('#tiShiXinXi').html("操作失败");
                }
            }
        });
    });

});