var CompanyApiProduct = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }


            var oTable = $('#sample_company_product_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 4 ],
                        "sType": "html-percent"
                    }
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


            var oTable2 = $('#sample_company_product_2').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 4 ],
                        "sType": "html-percent"
                    }
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

            /*状态正常全选操作*/
            jQuery('#sample_company_product_1 .group-checkable').change(function () {
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
            jQuery('#sample_company_product_2 .group-checkable').change(function () {
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


            $('#apiCompanyProductList').addClass('active');

            $('#apiProduct').addClass('active');

            $('#apiProductSelect').addClass('selected');

            $('#apiProductArrow').addClass('arrow open');

            $('#cid').select2({
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

            /*状态正常批量禁用CompanyApi操作*/
            $("#batch_add").on('click',function () {

                var cid_id =[];//定义一个数组
                $('input[name="checkBoxId"]:checked').each(function(){
                    cid_id.push($.trim($(this).val()));
                });

                if (cid_id == null || cid_id == ""){
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
                            url:"/api/company/ban-api",
                            data:{"cid_id": cid_id},
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
                                            window.location.href = window.location.href
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

                }
            });

        }

    };

}();