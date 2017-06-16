/**
 * Created by jonhn on 2017/6/14.
 */


function updateCredit(id,floor) {

    var companyId = id ;
    var credit = -floor/100.0 ;
    swal({
        title: '修改信用额度',
        input: 'number',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",
        confirmButtonText: "确定修改",
        allowOutsideClick: true,
        inputValue: credit,
        inputValidator: function(value) {
            return new Promise(function(resolve, reject) {
                var re =new RegExp("^(-?\\d+)(\\.\\d+)?$");
                if(!re.test(value)){
                    reject('格式输入不正确！');
                } else {
                    resolve();
                }
            });
        }
    }).then(function (value) {

        $.ajax({
            type: "post",
            url: "/company/customer/update-credit",
            data: {"companyId": companyId, "credit": value},
            dataType: "json",
            beforeSend: function () {
                openProgress();
            },
            success: function (data) {
                closeProgress();
                if (data != null) {
                    if (data.success != null) {
                        swal({
                            type: 'success',
                            title: '信用额度修改完成',
                            confirmButtonText: "确定",
                            html: '已将信用额度修改为：' + value
                        }).then(function () {
                            window.location.href = window.location.href ;
                            return;
                        });

                    }
                    if (data.fail != null) {

                        swal({
                            type: 'error',
                            title: '失败',
                            text: "哎呦，修改失败了",
                            confirmButtonText: "确定"

                        })
                    }
                }
            }
        });

    },function(dismiss) {
        // dismiss的值可以是'cancel', 'overlay','close', 'timer'
        if (dismiss === 'cancel') {}
    });

}