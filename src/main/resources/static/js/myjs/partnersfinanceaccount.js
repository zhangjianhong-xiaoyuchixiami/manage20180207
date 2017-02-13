var PartnerFinanceAccount = function () {

    return {

        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            //partnersFinancialAccount
            var table =  $('#sample_3').dataTable({
                "aoColumns": [
                    null,
                    null,
                    null,
                    { "bVisible": false },
                    { "bVisible": false },
                    null,
                    null,
                    { "bSortable": false }
                ],
                "aoColumnDefs": [
                    {
                        //收入总额
                        "aTargets": [ 1 ],
                        "sType": "html-percent"
                    },
                    {
                        //支出总额
                        "aTargets": [ 2 ],
                        "sType": "html-percent"
                    }

                ],
                "aaSorting": [[1, 'desc']],
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
                "bFilter" : false//设置全文搜索框，默认true

            });

            $('#sample_3_column_toggler input[type="checkbox"]').change(function(){
                /* Get the DataTables object again - this is not a recreation, just a get of the object */
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = table.fnSettings().aoColumns[iCol].bVisible;
                table.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            /*收款付款*/
            $("#btn-black-btn-primary").on("click",function () {
                var partnerId=$("#partnerId").val();
                var reasonId=$("#reasonId").val();
                var amount=$("#amount-flag").val();
                var date=$("#date").val();
                var remark=$("#remark").attr("value");
                $.ajax({
                    type: "post",
                    url: "/partner/find-all-partner-financial-account/payment-receipt",
                    data: {"partnerId":partnerId,"amount":amount,"date":date,"remark":remark,"reasonId":reasonId},
                    dataType: "json",
                    success: function (result) {
                        if(result.amountMessage != null){
                            $("#amount-message").empty();
                            $("#amount-message").append('<span class="help-line"><font color="red">'+result.amountMessage+'</font></span>');
                            return;
                        }

                        if(result.errorMessage != null) {
                            $("#error-alert").empty();
                            $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                            return;
                        }
                        if(result.successMessage != null){
                            window.location.href="/partner/find-all-partner-financial-account"
                        }
                    }
                });
            });

            /*新增合作公司*/
            $("#add-partner").on("click",function () {
                $("#partnerNameMsg").empty();
            });

            /*新增合作公司*/
            $("#add-btn-black-btn-primary").on("click",function () {
                var partnerName=$("#partnerName").val();
                $.ajax({
                    type: "post",
                    url: "/partner/find-all-partner-financial-account/add-partner",
                    data: {"partnerName":partnerName},
                    dataType: "json",
                    success: function (result) {
                        if(result.partnerMessage != null){
                            $("#partnerNameMsg").html('<font color="red">'+result.partnerMessage+'</font>');
                            return;
                        }
                        if(result.errorMessage != null) {
                            $("#error-alert").empty();
                            $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                            return;
                        }
                        if(result.successMessage != null){
                            window.location.href="/partner/find-all-partner-financial-account"
                        }
                    }
                });
            });

            /*支出走势*/
            $("#columnExpenditureHistogram").on("click",function () {
                $.ajax({
                    type: 'post',
                    url: '/partner/find-all-partner-financial-account/expenditure-bar-chart',
                    dataType: 'json',
                    success: function (result) {
                        var json = result;
                        var chart = new Highcharts.Chart({
                            chart: {
                                renderTo: 'columnExpenditureHistogramContainer',
                                type: 'column'
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
                                    text: '支出总额（单位：元）'
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

            /*收入走势*/
            $("#columnIncomeHistogram").on("click",function () {
                $.ajax({
                    type: 'post',
                    url: '/partner/find-all-partner-financial-account/income-bar-chart',
                    dataType: 'json',
                    success: function (result) {
                        var json = result;
                        var chart = new Highcharts.Chart({
                            chart: {
                                renderTo: 'columnIncomeHistogramContainer',
                                type: 'column'
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
                                    text: '支出总额（单位：元）'
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

            $('#customerBalance').addClass('active');

            $('#partnersFinancialAccount').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');


        }

    };

}();