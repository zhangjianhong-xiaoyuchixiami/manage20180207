/**
 * Created by jonhn on 2017/1/16.
 */
var MonthRecord = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var table = $('#sample_9').dataTable({
                "aoColumns": [
                    { "bSortable": false },
                    null,
                    null,
                    null
                ],
                "aaSorting": [[2, 'desc']],
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

            /*下拉框*/
            $("#years").change(function () {
                var param = $("#years").val();
                var param1 = $("#customerId").val();
                var param2 = $("#typeId").val();
                if (param !=null) {
                    $.ajax({
                        url: '/finance/find-company-customer-month-uplink-months-by-customer-id',
                        data: {"years": param, "customerId": param1, "typeId": param2},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                $("#months ").empty();
                                $("#months").append("<option value=''>请选择...</option>");
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i];
                                    op.innerHTML='第'+data[i]+'月';
                                    $("#months").append(op);
                                }
                            }
                        }
                    });
                }
            });

            /*折线图*/
            $('#months-account').on('click',function () {
                var param1 = $("#customerId").val();
                var param2 = $("#typeId").val();
                var param3 = $("#years").val();
                var param4 = $("#months").val();
                $.ajax({
                    type: "post",
                    url: "/finance/months-charge-consume-toward",
                    data: {"years": param3,"months": param4, "customerId": param1, "typeId": param2},
                    dataType: 'json',
                    success: function (result) {
                        var json = result;
                        $('#months-container').highcharts({
                            chart: {
                                type: 'line'
                            },
                            title: {
                                text: ''
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            xAxis: {
                                categories: json.xList
                            },
                            yAxis: {
                                title: {
                                    text: '金额 (单位：元)'
                                }
                            },
                            plotOptions: {
                                line: {
                                    dataLabels: {
                                        enabled: true
                                    },
                                    enableMouseTracking: false
                                }
                            },
                            series: json.yList
                        });
                    }
                });
            });

            $('.month_record').change(function () {
                $(this).submit();
            });

        }

    };

}();