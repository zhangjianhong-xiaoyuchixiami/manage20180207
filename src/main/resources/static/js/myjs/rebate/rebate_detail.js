var RebateDetail = function () {

    return {

        init: function () {


            $('#rebate').addClass('active');

            $('#customerBalance').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');


            $("#tid").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#cyc").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    {"bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 6 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 7 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 8 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 9 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 10 ],
                        "sType": "html-percent"
                    }
                ],
                "aaSorting": [[2, 'desc']],
                "bPaginate" : true,
                "bLengthChange" : false,
                "iDisplayLength": 10, //每页显示多少行
                "sDom": "t<'row-fluid'<'span6'i><'span6'p>>",
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

            /*表格显示列控制*/
            $('#sample_1_column_toggler input[type="checkbox"]').change(function(){
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            /*多选框*/
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

        }

    };

}();