/**
 * Created by jonhn on 2017/3/21.
 */
var SearchCustomerLogIndex = function () {

    return {

        //main function to initiate the module
        init: function () {

            $('#k_reqId').select2({
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

            $('#search_submit').on("click",function () {
                var k_reqId = $("#k_reqId").val();
                if (k_reqId == null || k_reqId == ""){
                    swal({
                        title: "操作提示",
                        text: "请选择类型！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                    return;
                }

                var content = $("input[name='content']").val();
                console.log(content);
                if (content == null || content == ""){
                    swal({
                        title: "操作提示",
                        text: "请输入查找内容！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                    return
                }
                $('#submit_form').submit();
            })

        }
    };

}();