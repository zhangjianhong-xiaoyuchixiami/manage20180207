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
                    null
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

            var oTable2 = $('#sample_product_2').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null
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


            /* $("#apiTypeId").change(function () {
             var param = $("#apiTypeId").val();
             if (param !=null) {
             $.ajax({
             url: '/api/api-vendor',
             data: {"apiTypeId": param},
             type: 'post',
             dataType: 'json',
             success: function (data) {
             if(data != null){
             $("#vendorId ").empty();
             $("#vendorId").append("<option value=''>请选择...</option>");
             for (var i=0; i<data.length; i++){
             var op=document.createElement("option");
             op.value=data[i].id;
             op.innerHTML=data[i].name;
             $("#vendorId").append(op);
             }
             }
             }
             });
             }
             });*/

        }

    };

}();