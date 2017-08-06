/**
 * Created by jonhn on 2017/7/28.
 */


var CustomerHistoryBillMonthDetail = function () {

    return {

        init: function () {

            $('#customerBalance').addClass('active');

            $('#customer-history-bill').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

            $('#cyc').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#tid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            var oTable = $('#sample_2').dataTable({
                "aoColumns": [
                    {"bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[1, 'desc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
                "bFilter" : false, //设置全文搜索框，默认true
                "iDisplayLength": 15, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'il><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "全文搜索：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                }
            });

            /*状态正常全选操作*/
            jQuery('#sample_2 .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                });
                jQuery.uniform.update(set);
            });

            var form = $('#submit_form');

            form.validate({
                rules: {
                    add_tid: {
                        required: true
                    },
                    add_cost: {
                        required: true,
                        number:true
                    },
                    add_amount: {
                        required: true,
                        number:true
                    },
                    add_cyc: {
                        required: true
                    }
                },
                messages: {
                    add_tid:{
                        required:"必选"
                    },
                    add_cost:{
                        required:"必填",
                        number:"必须输入合法的数字"
                    },
                    add_amount:{
                        required:"必填",
                        number:"必须输入合法的数字"
                    },
                    add_cyc:{
                        required:"必选"
                    }
                },
                errorClass: "self-error"
            });

            /*新增历史记录*/
            $("#add_btn").on("click",function () {

                if (form.valid() == false) {
                    return false;
                }

                swal({
                    title: "确定要添加吗？",   //弹出框的title
                    type: "question",    //弹出框类型
                    showCancelButton: true, //是否显示取消按钮
                    allowOutsideClick: false,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    cancelButtonText: "取消",//取消按钮文本
                    confirmButtonText: "确定"//确定按钮上面的文档
                }).then(function () {

                    var cid = $.getUrlParam('cid');
                    var add_tid = $('#add_tid').val();
                    var add_cost = $('#add_cost').val();
                    var add_amount = $('#add_amount').val();
                    var add_cyc = $('#add_cyc').val();

                    $.ajax({
                        type: "post",
                        url: "/finance/customer-history-bill/detail/add",
                        data: {"cid": cid, "tid":add_tid,"cost": add_cost, "amount": add_amount,"yearMonth": add_cyc},
                        dataType: "json",
                        beforeSend:function () {
                            openProgress();
                        },
                        success: function (data) {
                            closeProgress();
                            if(data != null){
                                if (data.fail != null) {
                                    swal(
                                        '失败',
                                        '哎呦，添加失败了',
                                        'error'
                                    );
                                    return;
                                }
                                if (data.success != null) {
                                    swal({
                                        title: "成功",
                                        html: '已添加成功',
                                        type: "success",
                                        showCancelButton: false, //是否显示取消按钮
                                        confirmButtonColor: '#3085d6',
                                        confirmButtonText: "确定"//确定按钮上面的文档
                                    }).then(function () {
                                        window.location.href = window.location.href
                                    })

                                }
                            }
                        }
                    });

                },function(dismiss) {
                    // dismiss的值可以是'cancel', 'overlay','close', 'timer'
                    if (dismiss === 'cancel') {}
                });

            });

            /*删除历史记录*/
            $("#del").on('click',function () {

                var id =[];//定义一个数组
                $('input[name="checkBox"]:checked').each(function(){
                    id.push($.trim($(this).val()));
                });

                if (id == null || id == ""){
                    swal({
                        title: "操作提示",
                        text: "请先选择要删除的列！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                }else {

                    swal({
                        title: "确定要删除吗？",   //弹出框的title
                        type: "question",    //弹出框类型
                        showCancelButton: true, //是否显示取消按钮
                        allowOutsideClick: false,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        cancelButtonText: "取消",//取消按钮文本
                        confirmButtonText: "确定"//确定按钮上面的文档
                    }).then(function () {

                        $.ajax({
                            type:'post',
                            url:"/finance/customer-history-bill/detail/delete",
                            data:{"id": id},
                            dataType:"json",
                            beforeSend:function () {
                                openProgress();
                            },
                            success:function(data){
                                closeProgress();
                                if (data != null){
                                    if (data.fail != null){
                                        swal({
                                            title: "操作提示",
                                            text: "删除失败",
                                            type: "error",
                                            showCancelButton: false,
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"
                                        }).then(function () {
                                            location.reload();
                                            return;
                                        })
                                    }
                                    if (data.success != null){
                                        swal({
                                            title: "操作提示",
                                            text: "删除成功",
                                            type: "success",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {
                                            location.reload();
                                            return;
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
            });

        }

    }

}();