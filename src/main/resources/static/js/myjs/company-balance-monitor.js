var CompanyBalanceMonitor = function () {

    return {

        init: function () {

            var oTable = $('#Sample_1').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false}
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 3 ],
                        "sType": "html-percent"
                    }
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

            /*左侧导航*/
            $('#customerManage').addClass('active');

            $('#customerBalanceMonitorList').addClass('active');

            $('#customerManageSelect').addClass('selected');

            $('#customerManageArrow').addClass('arrow open');

        }

    };

}();