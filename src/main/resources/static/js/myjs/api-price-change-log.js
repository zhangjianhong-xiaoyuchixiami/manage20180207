var ApiPriceChangeLog = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var oTable = $('#sample_api_price_change_log').dataTable({
                "aoColumns": [
                    { "bSortable": false},
                    null,
                    null,
                    null,
                    null,
                    null
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
            jQuery('#sample_api_price_change_log .group-checkable').change(function () {
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


            $('#apiPriceChangeLog').addClass('active');

            $('#apiProduct').addClass('active');

            $('#apiProductSelect').addClass('selected');

            $('#apiProductArrow').addClass('arrow open');

            $('#apiTypeId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#vendorId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#partnerId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#add_price_change_log_apiTypeId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $('#add_price_change_log_vendorId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $.fn.datetimepicker.dates['zh'] = {
                days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],
                daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],
                daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],
                months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],
                monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
                meridiem:    ["上午", "下午"],
                today:       "今天"
            };

            $(".form_datetime").datetimepicker({
                language:  'zh',
                autoclose: true,
                todayBtn: true,
                format: "yyyy/mm/dd hh:ii",
                titleFormat: "yyyy年mm月",
                pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
            });

        }

    };

}();