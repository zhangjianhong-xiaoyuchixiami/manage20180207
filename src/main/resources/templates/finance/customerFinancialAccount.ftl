
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

                <#--搜索框-->
                    <#if deptIdList??>

                        <form action="/finance/find-all-customer-by-dept-id" method="get">

                            <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

                                <div class="control-group pull-left" style="margin-bottom: -20px;margin-top: -25px;">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>
                    <#else >
                        <form action="/finance/find-all-customer" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -20px;margin-top: -25px;">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>
                    </#if>

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></div>

                            <#if deptIdList??>
                                <@d.tools idName="exportExcelByDeptId"></@d.tools>
                            <#else >
                                <@d.tools idName="exportExcel"></@d.tools>
                            </#if>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">

                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 10%;">公司名称</th>
                                    <th>周充值总额（单位：元</th>
                                    <th>周消费总额（单位：元</th>
                                    <th>月充值总额（单位：元</th>
                                    <th>月消费总额（单位：元</th>
                                    <th>充值总额（单位：元</th>
                                    <th>消费总额（单位：元</th>
                                    <th>余额（单位：元</th>
                                    <th style="text-align: center; width: 12%;"></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerFinanceList??>
                                        <#list customerFinanceList as customer>
                                        <tr>
                                            <td data-title="公司名称">${customer.companyName}</td>
                                            <td data-title="周充值总额"><#if customer.chargeWeekTotleAmount??>${(customer.chargeWeekTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="周消费总额"><#if customer.consumeWeekTotleAmount??>${(-customer.consumeWeekTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="月充值总额"><#if customer.chargeMonthTotleAmount??>${(customer.chargeMonthTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="月消费总额"><#if customer.consumeMonthTotleAmount??>${(-customer.consumeMonthTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="充值总额"><#if customer.chargeTotleAmount??>${(customer.chargeTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="消费总额"><#if customer.consumeTotleAmount??>${(-customer.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="账号余额"><#if customer.balance??>${(customer.balance/100.0)?c}<#else >0</#if></td>
                                            <td data-title="操作" style="text-align: center">
                                                <ul class="nav nav-tabs" style="margin-bottom: 0px; min-width: 94px; border-bottom: 0px solid #f4f4f4;">
                                                    <li class="dropdown" style="float: none;">
                                                        <a class="dropdown-toggle" style=" padding-bottom: 0px; padding-top: 0px;" data-toggle="dropdown" href="#">
                                                            操作 <span class="caret"></span>
                                                        </a>
                                                        <ul class="dropdown-menu" style="min-width: 105px; font-size: 13px;">
                                                            <li style="text-align: left"><a style="color: #08c;" href="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?customerId=${customer.id?c}&reasonId=1&companyName=${customer.companyName}">充值记录</a></li>
                                                            <li style="text-align: left"><a style="color: #08c;" href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}">消费记录</a></li>
                                                            <#--<li style="text-align: left"><a style="color: #08c;" href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}&typeId=1">周历史</a></li>-->
                                                            <#--<li style="text-align: left"><a style="color: #08c;" href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}&typeId=1">月历史数据</a></li>-->
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>

                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">
    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script>
        jQuery(document).ready(function() {
            TableManaged.init();
        });

        <#--导出Excel-->
        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                fetch('/excel-finance/find-all-customer?content='+companyName).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = '客户财务报表.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });

            $('#exportExcelByDeptId').on('click', function () {
                var companyName = $('#companyName').val();
                var username = $('#username').text();
                fetch('/excel-finance/find-all-customer-by-dept-id?content='+companyName+'&username='+username).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = '客户财务报表.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });
        });

    </script>

    </#if>

</@layout>
