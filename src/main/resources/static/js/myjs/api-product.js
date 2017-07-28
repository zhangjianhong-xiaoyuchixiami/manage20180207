var ApiProduct = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            // apiRecord
            var oTable = $('#sample_product_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false}
                ],
                "aaSorting": [[1, 'asc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
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
                },
                "bFilter" : false //设置全文搜索框，默认true
            });

            var oTable2 = $('#sample_product_2').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false}
                ],
                "aaSorting": [[0, 'asc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
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
                },
                "bFilter" : false //设置全文搜索框，默认true
            });


            /*状态正常全选操作*/
            jQuery('#sample_product_1 .group-checkable').change(function () {
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

            /*状态禁用全选操作*/
            jQuery('#sample_product_2 .group-checkable').change(function () {
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


            $('#apiProductList').addClass('active');

            $('#apiProduct').addClass('active');

            $('#apiProductSelect').addClass('selected');

            $('#apiProductArrow').addClass('arrow open');

            $('#vid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#pid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#tid').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#statId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            /*状态正常批量禁用Api操作*/
            $("#batchBanApi").on('click',function () {

                var apiId =[];//定义一个数组
                $('input[name="checkBoxApiIdBan"]:checked').each(function(){
                    apiId.push($.trim($(this).val()));
                });

                if (apiId == null || apiId == ""){
                    swal({
                        title: "操作提示",
                        text: "请先选择要禁用的产品！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                }else {

                    swal({
                        title: "确定要禁用吗？",   //弹出框的title
                        type: "question",    //弹出框类型
                        showCancelButton: true, //是否显示取消按钮
                        allowOutsideClick: false,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        cancelButtonText: "取消",//取消按钮文本
                        confirmButtonText: "确定禁用"//确定按钮上面的文档
                    }).then(function () {

                        $.ajax({
                            type:'post',
                            url:"/api/ban",
                            data:{"apiId": apiId},
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
                                            text: data.fail,
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
                                            text: "禁用成功",
                                            type: "success",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {
                                            location.reload();
                                            return;
                                        })
                                    }
                                    if (data.warning != null){
                                        swal({
                                            title: "操作提示",
                                            text: data.warning,
                                            type: "warning",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {

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

            /*状态禁用批量启用Api操作*/
            $("#batchUnBanApi").on('click',function () {

                var apiId =[];//定义一个数组
                $('input[name="checkBoxApiIdUnBan"]:checked').each(function(){
                    apiId.push($.trim($(this).val()));
                });

                if (apiId == null || apiId == ""){
                    swal({
                        title: "操作提示",
                        text: "请先选择要启用的产品！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                }else {

                    swal({
                        title: "确定要启用吗？",   //弹出框的title
                        type: "question",    //弹出框类型
                        showCancelButton: true, //是否显示取消按钮
                        allowOutsideClick: false,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        cancelButtonText: "取消",//取消按钮文本
                        confirmButtonText: "确定启用"//确定按钮上面的文档
                    }).then(function () {

                        $.ajax({
                            type:'post',
                            url:"/api/unban",
                            data:{"apiId": apiId},
                            dataType:'json',
                            beforeSend:function () {
                                openProgress();
                            },
                            success:function(data){
                                closeProgress();
                                if (data != null){
                                    if (data.fail != null){
                                        swal({
                                            title: "操作提示",
                                            text: data.fail,
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
                                            text: "启用成功",
                                            type: "success",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {
                                            location.reload();
                                            return;
                                        })
                                    }
                                    if (data.warning != null){
                                        swal({
                                            title: "操作提示",
                                            text: data.warning,
                                            type: "warning",
                                            showCancelButton: false, //是否显示取消按钮
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"//确定按钮上面的文档
                                        }).then(function () {

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

            /*状态禁用批量恢复Api配额操作*/
            $("#batchRecoverApi").on('click',function () {

                var apiId =[];//定义一个数组
                $('input[name="checkBoxApiIdUnBan"]:checked').each(function(){
                    apiId.push($.trim($(this).val()));
                });

                if (apiId == null || apiId == ""){
                    swal({
                        title: "操作提示",
                        text: "请先选择要恢复的产品！",
                        type: "info",
                        confirmButtonText: "确定"
                    });
                }else {

                    swal({
                        title: "确定要进行恢复操作吗？",   //弹出框的title
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
                            url:"/api/recover-prob",
                            data:{"aid": apiId},
                            dataType:'json',
                            success:function(data){
                                if (data != null) {
                                    if (data.success != null) {
                                        swal({
                                            title: "操作提示",
                                            text: data.success,
                                            type: "success",
                                            showCancelButton: false,
                                            confirmButtonColor: '#3085d6',
                                            confirmButtonText: "确定"
                                        });
                                        return;
                                    }
                                    if (data.fail != null) {
                                        swal({
                                            title: "操作提示",
                                            text: data.fail,
                                            type: "error",
                                            showCancelButton: false,
                                            confirmButtonColor: '#3085d6',
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
            });

        }

    };

}();