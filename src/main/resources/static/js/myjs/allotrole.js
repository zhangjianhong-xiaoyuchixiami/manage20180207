var AllotRole = function () {

    return {

        init: function () {

                $('#allotRoleSave').on("click",function(){
                    //jquery获取复选框值
                    var roleId_id =[];//定义一个数组
                    $('input[name="roleId"]:checked').each(function(){
                        roleId_id.push($.trim($(this).val()));
                    });
                    var userId = $('input[name="userId"]').val();
                    var indata = {"roleId":roleId_id,"userId":userId};
                    $.ajax({
                        type:'post',
                        url:"/role/allotRoleAction",
                        data:indata,
                        dataType:'json',
                        success:function(data){
                            if(data!=null && data.result=="ok"){
                                alert("操作成功");
                                window.location.href="/role/allotRoleView?userId="+userId;
//                        $('#form_modal4').css('display','block');
//                        $('#tiShiXinXi').html("操作成功");
                            }else{
                                alert("操作失败");
                                window.location.href="/role/allotRoleView?userId="+userId;
//                        $('#form_modal4').css('display','block');
//                        $('#tiShiXinXi').html("操作失败");
                            }
                        }
                    });
                });

                $('#userManage').addClass('active');

                $('#userList').addClass('active');

                $('#userManageSelect').addClass('selected');

                $('#userManageArrow').addClass('arrow open');

        }

    };

}();