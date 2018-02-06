

function currDayApiTypeConsume(vendorId) {
    $("#simple_curr_day_api_type_consume tbody").empty();

    /*加载供应商的名称*/
    $.ajax({
        type: "post",
        url: "/api/find-all-vendor/vendor-name",
        data:{"vendorId":vendorId},
        dataType: "json",
        success:function (result) {
            if (result != null && result.vendorName != null){
                $('#vendor_name').html(result.vendorName)
            }
        }
    });

    /*加载消费数据*/
    $.ajax({
        type: "post",
        url: "/api/find-all-vendor/curr-day-api-type-consume",
        data:{"vendorId":vendorId},
        dataType: "json",
        beforeSend:function () {
            var myContent = "<tr>" +
                "<td rowspan='4'>" + '正在加载，请稍后' + "<span class='loading'></span></td>" +
                "</tr>";
            $("#simple_curr_day_api_type_consume tbody").append(myContent);
        },
        success:function (result) {

            $("#simple_curr_day_api_type_consume tbody").empty();

            if (result != null && result.consumeList != null){
                var data = result.consumeList;
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var myContent;

                        if (data[i].cost == null) {
                            myContent = "<tr>" +
                                "<td>" + data[i].apiTypeName + "</td>" +
                                "<td>" + '未知' + "</td>" +
                                "<td>" + data[i].consumeAccount + "</td>" +
                                /*"<td>" + data[i].countTotle + "</td>" +*/
                                "<td>" + data[i].apiTypeConsume + "</td>" +
                                "</tr>";
                        } else {
                            myContent = "<tr>" +
                                "<td>" + data[i].apiTypeName + "</td>" +
                                "<td>" + data[i].cost + "</td>" +
                                "<td>" + data[i].consumeAccount + "</td>" +
                                /*"<td>" + data[i].countTotle + "</td>" +*/
                                "<td>" + data[i].apiTypeConsume + "</td>" +
                                "</tr>";
                        }
                        $("#simple_curr_day_api_type_consume tbody").append(myContent);
                    }
                }else {
                    var myContent = "<tr>" +
                        "<td rowspan='4'>" + '无消费记录' + "</td>" +
                        "</tr>";
                    $("#simple_curr_day_api_type_consume tbody").append(myContent);
                }
            }else {
                var myContent = "<tr>" +
                    "<td rowspan='4'colspan=\"4\">" + '无消费记录' + "</td>" +
                    "</tr>";
                $("#simple_curr_day_api_type_consume tbody").append(myContent);
            }

        }
    });
}

function updateVendorRate(vid,rate) {

    swal({
        title: '修改比率',
        input: 'number',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "取消",
        confirmButtonText: "确定修改",
        allowOutsideClick: true,
        inputValue: rate,
        inputValidator: function(value) {
            return new Promise(function(resolve, reject) {
                var re =new RegExp("^(\\d+)(\\.\\d+)?$");
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
            url: "/api/update-rate",
            data: {"vid": vid, "rate": value},
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
                            title: '比率修改完成',
                            confirmButtonText: "确定",
                            html: '已将比率修改为：' + value + '%'
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

                        });
                        return;
                    }
                    if (data.warning != null){
                        swal({
                            title: "操作提示",
                            text: data.warning,
                            type: "warning",
                            showCancelButton: false, //是否显示取消按钮
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"//确定按钮上面的文档
                        })
                    }
                    if (data.role_warning != null){
                        swal({
                            title: "操作提示",
                            text: data.role_warning,
                            type: "warning",
                            showCancelButton: false, //是否显示取消按钮
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: "确定"//确定按钮上面的文档
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


