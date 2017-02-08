var PartnerFinanceAccount = function () {

    return {

        init: function () {

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
                        }
                        if(result.successMessage != null){
                            window.location.href="/partner/find-all-partner-financial-account"
                        }
                        if(result.errorMessage != null) {
                            $("#error-alert").empty();
                            $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
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
                        }
                        if(result.successMessage != null){
                            window.location.href="/partner/find-all-partner-financial-account"
                        }
                        if(result.errorMessage != null) {
                            $("#error-alert").empty();
                            $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
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