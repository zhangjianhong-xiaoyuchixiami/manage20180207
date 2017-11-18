/**
 * Created by jonhn on 2017/3/21.
 */
var DataMobileValid = function () {

    return {

        //main function to initiate the module
        init: function () {

            $('#tid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#aid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#omit').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#skip').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#pro_valid').addClass('active');

            $('#pro_valid_select').addClass('selected');

            $('#pro_valid_arrow').addClass('arrow open');

            $('#data_mobile_valid').addClass('active');

            $('#valid_submit').on("click",function () {

                var tid = $("#tid").val();

                if (tid == null || tid == ""){
                    swal({
                        title: "操作提示",
                        text: "请选择要核验的类型！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                    return
                }
                $('#submit_form').submit();
            });

            $('#mobile').on('blur',function () {
                var mobile = $("input[name='mobile']").val();
                $.ajax({
                    type: "post",
                    url: "/data/mobile/operator",
                    data: {"mobile" : mobile},
                    dataType: "json",
                    success: function (data) {
                        if (data != null){
                            if (data.operator != null) {
                               $('#mobile_msg').text(data.operator)
                            }
                        }
                    }
                });
            })

        }
    };

}();