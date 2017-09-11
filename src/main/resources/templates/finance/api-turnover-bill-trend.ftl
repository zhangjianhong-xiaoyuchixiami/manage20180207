
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div id="dashboard">

                <div class="clearfix"></div>

                <div class="row-fluid">

                    <div class="span12">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-bar-chart"></i>${name!'无'}</div>

                            <div class="tools">

                                <div class="pull-right">

                                    <form action="/finance/api-turnover-bill/trend" class="tip-remark" method="get">

                                        <input style="display: none" id="tid" name="tid" value="${tid!''}">

                                        <input style="display: none" id="name" name="name" value="${name!'无'}">

                                        <select class="medium m-wrap" id="cyc" name="cyc">
                                            <option value=""></option>
                                            <#if conTimeList??>
                                                <#list conTimeList as conTime>
                                                    <option <#if cyc?? && cyc == conTime> selected="selected"</#if> value="${conTime}">${conTime}</option>
                                                </#list>
                                            </#if>
                                        </select>

                                        <button class="btn black" type="submit">确认</button>

                                    </form>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

                <div class="clearfix"></div>

            <#--供应商-->
                <div class="row-fluid">

                    <div class="span12">

                        <div class="portlet solid bordered light-grey">

                            <div class="portlet-body">

                                <div id="site_statistics_loading">

                                    <div id="container_1" style="min-width:400px;height:400px"></div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet solid light-grey bordered">

                            <div class="portlet-body">

                                <table class="table table-hover table-condensed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th>供应商名称</th>
                                        <th>成本价</th>
                                        <th>扣费量</th>
                                        <th>消费金额</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if vendorTrendList??>
                                            <#list vendorTrendList as vendorTrend>
                                            <tr>
                                                <td data-title="供应商名称">${vendorTrend.vendorPartnerName!'无'}</td>
                                                <td data-title="成本价">${vendorTrend.cost!'0'}</td>
                                                <td data-title="扣费量">${vendorTrend.amount!'0'}</td>
                                                <td data-title="消费金额">${vendorTrend.consume!'0'}</td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>

                                </table>

                            </div>

                        </div>

                    </div>

                </div>

                <div class="clearfix"></div>

            <#--客户-->
                <div class="row-fluid">

                    <div class="span12">

                        <div class="portlet solid bordered light-grey">

                            <div class="portlet-body">

                                <div id="site_statistics_loading">

                                    <div id="container_2" style="min-width:400px;height:400px"></div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet solid light-grey bordered">

                            <div class="portlet-body">

                                <table class="table table-hover table-condensed" id="sample_2">
                                    <thead>
                                    <tr>
                                        <th>客户名称</th>
                                        <th>售卖价</th>
                                        <th>扣费量</th>
                                        <th>消费金额</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if customerTrendList??>
                                            <#list customerTrendList as customerTrend>
                                            <tr>
                                                <td data-title="客户名称">${customerTrend.companyPartnerName!'无'}</td>
                                                <td data-title="售卖价">${customerTrend.cost!'0'}</td>
                                                <td data-title="扣费量">${customerTrend.amount!'0'}</td>
                                                <td data-title="消费金额">${customerTrend.consume!'0'}</td>
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

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/highcharts/highcharts.js"></script>

    <script src="/js/highcharts/exporting.js"></script>

    <script src="/js/myjs/api_turnover_bill_trend.js?v=${ver}"></script>

    <script src="/js/multi/get-url-param.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            ApiTurnoverBillTrend.init();
        });

        $(function () {
            var tid = $.getUrlParam('tid');
            var cyc = $.getUrlParam('cyc');
            $.ajax({
                type: "post",
                url: "/finance/api-turnover-bill/trend-data",
                data: {"tid": tid,"cyc": cyc},
                dataType: "json",
                success: function (data) {
                    if (data != null) {

                        var vendorData = data.vendorMap;
                        var customerData = data.customerMap;
                        var json_vendor_data = [];
                        var json_customer_data = [];
                        for (var i in vendorData) {
                            json_vendor_data.push([i, vendorData[i]]);
                        }
                        for (var i in customerData) {
                            json_customer_data.push([i, customerData[i]]);
                        }
                        new Highcharts.Chart({
                            chart: {
                                renderTo: 'container_1',
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false
                            },
                            title: {
                                text: '供应商统计'
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            tooltip: {
                                headerFormat: '{series.name}<br>',
                                pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: true,
                                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                        style: {
                                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                        }
                                    }
                                }
                            },
                            series: [{
                                type: 'pie',
                                name: '扣费量占比',
                                data: json_vendor_data
                            }]
                        });

                        new Highcharts.Chart({
                            chart: {
                                renderTo: 'container_2',
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false
                            },
                            title: {
                                text: '客户统计'
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            tooltip: {
                                headerFormat: '{series.name}<br>',
                                pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: true,
                                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                        style: {
                                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                        }
                                    }
                                }
                            },
                            series: [{
                                type: 'pie',
                                name: '扣费量占比',
                                data: json_customer_data
                            }]
                        });
                    }
                }
            });

        });

    </script>

    </#if>

</@layout>
