var TableManaged = function () {

    return {

        //main function to initiate the module
        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            // apiRecord
            $('#sample_1').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[4, 'asc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
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
                "bFilter" : false, //设置全文搜索框，默认true

                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                }
                ]
            });

            jQuery('#sample_1 .group-checkable').change(function () {
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
            jQuery('#sample_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            //jQuery('#sample_1_wrapper .dataTables_length select').select2(); // initialzie select2 dropdown

            //customerFinancialAccount
            $('#sample_2').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[7, 'asc']],
                "aLengthMenu": [
                    [10, 15, 20, -1],
                    [10, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false, //设置全文搜索框，默认true

                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                }
                ]

            });



            //partnersFinancialAccount
            $('#sample_3').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[5, 'desc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false, //设置全文搜索框，默认true

                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                }
                ]

            });

            //partnersFinancialAccount
            $('#sample_4').dataTable({
                "aoColumns": [
                    null,
                    null,
                    { "bSortable": false },
                    { "bSortable": false }
                ],
                "aaSorting": [[1, 'desc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true

            });

            //customerApiConsumeRecord
            $('#sample_5').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    { "bSortable": false },
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[2, 'desc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true

            });

            //customerBalanceLogRecord
            $('#sample_6').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[1, 'desc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true

            });

            //customerApiConsumeDetailRecord
            $('#sample_7').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    { "bSortable": false },
                    null
                ],
                "aaSorting": [[2, 'desc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true
            });

            //weekRecord
            $('#sample_8').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[2, 'desc']],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "全部"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
                    "sInfoEmpty" : "没有匹配的数据",
                    "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
                    "sProcessing" : "正在加载中...",
                    "sSearch" : "请输入公司名称：",
                    "oPaginate" : {
                        "sFirst" : "第一页",
                        "sPrevious" : " 上一页 ",
                        "sNext" : " 下一页 ",
                        "sLast" : " 最后一页 "
                    }
                },
                "bFilter" : false //设置全文搜索框，默认true
            });

        }

    };

}();