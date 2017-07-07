var ExtraExcel = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aaSorting": [[3, 'asc']],
                "bPaginate" : false,
                "sDom": "t<'row-fluid'<'span6'i><'span6'>>",
                "sPaginationType": "bootstrap",
                "oLanguage" : {  //设置语言
                    "sLengthMenu" : "每页显示 _MENU_ 条记录",
                    "sZeroRecords" : "对不起，没有匹配的数据",
                    "sInfo" : "共 _TOTAL_ 条数据",
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

            $('#extra-account').addClass('active');

            $('#customerBalance').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

            $("#pid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            })

            $("#obj").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            })

            $("#wid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            })




        }

    };

}();