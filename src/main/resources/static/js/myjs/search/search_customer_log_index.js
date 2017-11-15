/**
 * Created by jonhn on 2017/3/21.
 */
var SearchCustomerLogIndex = function () {

    return {

        //main function to initiate the module
        init: function () {

            $('#tid').select2({
                language: "zh-CN",
                placeholder: "请选择类型",
                allowClear: true
            });

            $('#cid').select2({
                language: "zh-CN",
                placeholder: "请选择客户",
                allowClear: true
            });

            $('#search_log').addClass('active');

            $('#search_log_select').addClass('selected');

            $('#search_log_select_arrow').addClass('arrow open');

            $('#search_customer_log').addClass('active');

            var form = $('#submit_form').validate({
                rules: {
                    tid: {
                        required: true
                    },
                    content: {
                        required: true,
                        number:true
                    }
                },
                messages: {
                    tid:{
                        required:""
                    },
                    content:{
                        required:""
                    }
                },
                submitHandler: function (form) {

                        swal({
                            title: "操作提示",
                            text: "请选择类型！",
                            type: "info",
                            confirmButtonText: "确定"
                        });

                }
                // errorClass: "self-error"
            });



            // $('#search_submit').on("click",function () {

                // var tid = $('#tid').val();
                // if (tid == null){
                //     swal({
                //         title: "操作提示",
                //         text: "请选择类型！",
                //         type: "info",
                //         confirmButtonText: "确定"
                //     });
                //     return;
                // }
                // var content = $('#content').val();
                // if (content == null){
                //     swal({
                //         title: "操作提示",
                //         text: "请输入查找内容！",
                //         type: "info",
                //         confirmButtonText: "确定"
                //     });
                //     return
                // }
                // $('#search_submit').submit();
            // })

        }
    };

}();