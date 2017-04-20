var Api = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            var oTable = $('#sample_1').dataTable({
                "aoColumns": [
                    null,
                    null,
                    { "bVisible": false },
                    null,
                    null,
                    { "bVisible": false },
                    { "bVisible": false },
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aaSorting": [[8, 'desc']],
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

            $('#sample_1_column_toggler input[type="checkbox"]').change(function(){
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('#customerBalance').addClass('active');

            $('#apiRecordLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

            $("#columnHistogram").on("click",function () {
                $.ajax({
                    type: 'post',
                    url: '/api/find-all-api-record/bar-chart',
                    dataType: 'json',
                    success: function (result) {
                        var json = result;
                        var chart = new Highcharts.Chart({
                            chart: {
                                renderTo: 'columnHistogramContainer',
                                type: 'column',
                                reflow: true
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
                                min: 0,
                                title: {
                                    text: '消费总额（单位：元）'
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f} 元</b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            },
                            series: json.yList
                        });
                    }
                });
            });

            $("#apiTypeId").change(function () {
                var param = $("#apiTypeId").val();
                if (param !=null) {
                    $.ajax({
                        url: '/api/find-api-vendor-by-api-type-id',
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
            });

        }

    };

}();