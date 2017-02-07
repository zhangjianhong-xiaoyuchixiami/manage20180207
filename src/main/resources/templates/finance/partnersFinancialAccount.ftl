
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">


                    <form action="/partner/find-all-partner-financial-account" method="get">

                        <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

                            <div class="control-group pull-left" style="margin-bottom: -20px;margin-top: -25px;">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if partnerName??>value="${partnerName}" </#if> type="text" name="partnerName" placeholder="请输入合作公司名称">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_3_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                        <label><input type="checkbox" checked data-column="0">公司名称</label>

                                        <label><input type="checkbox" checked data-column="1">收入总额</label>

                                        <label><input type="checkbox" checked data-column="2">支出总额</label>

                                        <label><input type="checkbox" data-column="3">上周收入</label>

                                        <label><input type="checkbox" data-column="4">上周支出</label>

                                        <label><input type="checkbox" checked data-column="5">上月收入</label>

                                        <label><input type="checkbox" checked data-column="6">上月支出</label>
                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <div class="btn-group">

                                    <a class="btn black" id="add-partner" href="#form_modal1" data-toggle="modal">

                                        新增<i class="icon-plus"></i>

                                    </a>

                                </div>

                                <div id="form_modal1" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">

                                    <div class="modal-header">

                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                        <h3 id="myModalLabel1">请填写信息</h3>

                                    </div>

                                    <div class="modal-body">

                                        <form action="#" class="form-horizontal">

                                            <div class="control-group"></div>

                                            <div class="control-group"></div>

                                            <div id="error-alert"></div>

                                            <div class="control-group">

                                                <label class="control-label">请输入合作公司名称<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="partnerName" name="partnerName" class="m-wrap medium">

                                                    <span id="partnerNameMsg" class="help-line"></span>


                                                </div>

                                            </div>

                                        </form>

                                    </div>

                                    <div class="modal-footer">

                                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                        <button class="btn black btn-primary" id="add-btn-black-btn-primary" type="button">提交</button>

                                    </div>

                                </div>

                            </div>

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">周收入总额&yen;：<#if weekIncomeAmount??><span>${(weekIncomeAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">周支出总额&yen;：<#if weekExpenditure??><span>${(weekExpenditure/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">月收入总额&yen;：<#if monthIncomeAmount??><span>${(monthIncomeAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">月支出总额&yen;：<#if monthExpenditure??><span>${(monthExpenditure/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>


                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">收入总额&yen;：<#if totleIncomeAmount??><span>${(totleIncomeAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">支出余额&yen;：<#if totleExpenditure??><span>${(totleExpenditure/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                            <#--支出总额走势-->
                                <div class="control-group pull-right" style="margin-bottom: -10px;">

                                    <label class="control-label">

                                        <a id="columnExpenditureHistogram" href="#form_modal7" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>支出总额

                                        </a>

                                    </label>

                                    <div id="form_modal7" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel7">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="columnExpenditureHistogramContainer">

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            <#--收入总额走势-->
                                <div class="control-group pull-right" style="margin-bottom: -10px;">

                                    <label class="control-label">

                                        <a id="columnIncomeHistogram" href="#form_modal8" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>收入总额

                                        </a>&nbsp;&nbsp;&nbsp;

                                    </label>

                                    <div id="form_modal8" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel8">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="columnIncomeHistogramContainer">

                                            </div>

                                        </div>

                                    </div>

                                </div>


                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_3">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">公司名称</th>
                                    <th>收入总额（单位：元）</th>
                                    <th>支出总额（单位：元）</th>
                                    <th>上周收入（单位：元）</th>
                                    <th>上周支出（单位：元）</th>
                                    <th>上月收入（单位：元）</th>
                                    <th>上月支出（单位：元）</th>
                                    <th style="text-align: center; width: 15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if partnerFinanceList??>
                                        <#list partnerFinanceList as partnerFinance>
                                        <tr class="odd gradeX">
                                            <td data-title="公司名称">${partnerFinance.partnerName}</td>
                                            <td data-title="收入总额"><a href="/partner/find-all-partner-financial-account/income-and-expenditure-record?partnerId=${partnerFinance.partnerId?c}&partnerName=${partnerFinance.partnerName}&reasonId=1"><#if partnerFinance.totleIncomeAmount??>${(partnerFinance.totleIncomeAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="支出总额"><a href="/partner/find-all-partner-financial-account/income-and-expenditure-record?partnerId=${partnerFinance.partnerId?c}&partnerName=${partnerFinance.partnerName}&reasonId=2"><#if partnerFinance.totleExpenditureAmount??>${(partnerFinance.totleExpenditureAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="上周收入"><#if partnerFinance.weekIncomeAmount??>${(partnerFinance.weekIncomeAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="上周支出"><#if partnerFinance.weekExpenditureAmount??>${(partnerFinance.weekExpenditureAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="上月收入"><#if partnerFinance.monthIncomeAmount??>${(partnerFinance.monthIncomeAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="上月支出"><#if partnerFinance.monthExpenditureAmount??>${(partnerFinance.monthExpenditureAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="操作" style="text-align: center">
                                                <a href="#form_modal2" onclick="paymentReceipt(${partnerFinance.partnerId?c},2)"  data-toggle="modal">付款</a>|
                                                <a href="#form_modal2" onclick="paymentReceipt(${partnerFinance.partnerId?c},1)"  data-toggle="modal">收款</a>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                            <div id="form_modal2" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                <div class="modal-header">

                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                    <h3 id="myModalLabel2">请填写信息</h3>

                                </div>

                                <div class="modal-body">

                                    <form action="#" class="form-horizontal">

                                        <div class="control-group"></div>

                                        <div class="control-group"></div>

                                        <div id="error-alert"></div>

                                        <div id="partnerId-controls" class="controls" style="display: none;"></div>

                                        <div id="reasonId-controls" class="controls" style="display: none;"></div>

                                        <div class="control-group">

                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                            <div id="amount-controls" class="controls"></div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">日&nbsp;期<span class="required">*</span></label>

                                            <div class="controls">

                                                <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                                    <input id="date" name="date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                                </div>

                                            </div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                            <div id="remark-controls" class="controls"></div>

                                        </div>

                                    </form>

                                </div>

                                <div class="modal-footer">

                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                    <button class="btn black btn-primary" id="btn-black-btn-primary" type="button">提交</button>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script>
        jQuery(document).ready(function() {
            TableManaged.init();
        });
    </script>

    <script type="text/javascript">

        function paymentReceipt(partnerId,reasonId) {
            $("#partnerId-controls").empty();
            $("#reasonId-controls").empty();
            $("#amount-controls").empty();
            $("#remark-controls").empty();
            $("#error-alert").empty();
            var opPartnerId=document.createElement("input");
            opPartnerId.value=partnerId;
            opPartnerId.type="text";
            opPartnerId.id="partnerId";
            opPartnerId.name="partnerId";
            $("#partnerId-controls").append(opPartnerId);
            var opReasonId=document.createElement("input");
            opReasonId.value=reasonId;
            opReasonId.type="text";
            opReasonId.id="reasonId";
            opReasonId.name="reasonId";
            $("#reasonId-controls").append(opReasonId);
            $("#amount-controls").append('<input type="text" id="amount-flag" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span id="amount-message"></span><span class="help-block">说明：只能输入数字类型并且金额大于0</span>');
            $("#remark-controls").append('<textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea><span class="help-block" style="font-size: 12px;">说明：只能输入255个字符</span>');

        }

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

        $("#add-partner").on("click",function () {
            $("#partnerNameMsg").empty();
        });

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

    </script>

    <#--支出走势-->
    <script type="text/javascript">
        $(document).ready(function () {

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
        });
    </script>

    <#--收入走势-->
    <script type="text/javascript">
        $(document).ready(function () {

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
        });
    </script>

    <#--导出Excel-->
    <script type="text/javascript">

        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var partnerName = $('#partnerName').val();
                fetch('/excel-partner-finance/find-all-partner-financial-account?partnerName='+partnerName).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename ='合作公司财务账单.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#partnersFinancialAccount').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

        });
    </script>
    </#if>

</@layout>
