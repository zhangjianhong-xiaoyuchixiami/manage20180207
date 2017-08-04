/**
 * Created by jonhn on 2017/7/28.
 */


var CustomerHistoryBill = function () {

    return {

        init: function () {

            $('#customerBalance').addClass('active');

            $('#customer-history-bill').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

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

            $('#beg_month').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#end_month').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });


            var oTable = $('#sample_2').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    {"bSortable": false}
                ],
                "aoColumnDefs": [
                    {
                        "aTargets": [ 0 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 1 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 2 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 3 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 4 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 5 ],
                        "sType": "html-percent"
                    },
                    {
                        "aTargets": [ 6 ],
                        "sType": "html-percent"
                    }
                ],
                "aaSorting": [[5, 'desc']],
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

        }

    }

}();