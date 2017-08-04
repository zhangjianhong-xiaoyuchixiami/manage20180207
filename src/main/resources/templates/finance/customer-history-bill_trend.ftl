
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

                        <div class="portlet solid bordered light-grey">

                            <div class="portlet-title">

                                <div class="caption"><i class="icon-bar-chart"></i>${name!'无'}</div>

                            <#--<div class="tools">-->

                            <#--<div class="btn-group pull-right" data-toggle="buttons-radio">-->

                            <#--<a href="" class="btn mini">Users</a>-->

                            <#--<a href="" class="btn mini active">Feedbacks</a>-->

                            <#--</div>-->

                            <#--</div>-->

                            </div>

                            <div class="portlet-body">

                                <div id="site_statistics_loading">

                                    <div id="container" style="min-width:400px;height:400px"></div>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

                <div class="row-fluid">

                    <div class="span12">

                        <div class="portlet solid bordered light-grey">

                            <div class="portlet-body">

                                <div id="site_statistics_loading">

                                    <div id="container_2" style="min-width:400px;height:400px"></div>

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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/highcharts/highcharts.js"></script>

    <script src="/js/highcharts/exporting.js"></script>

    <script src="/js/myjs/customer-history_bill.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            CustomerHistoryBill.init();
        });

        (function($){
            $.getUrlParam = function(name)
            {
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
            }
        })(jQuery);

        $(function () {
            var cid = $.getUrlParam('cid');
            $.ajax({
                type: "post",
                url: "/finance/customer-history-bill/trend/data",
                data: {"cid": cid},
                dataType: "json",
                success: function (data) {
                    if (data != null) {

                        new Highcharts.Chart({
                            chart: {
                                renderTo: 'container',
                                type: 'line',
                                reflow: true
                            },
                            title: {
                                text: '月消费走势'
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            xAxis: {
                                categories: data.xList
                            },
                            yAxis: {
                                title: {
                                    text: '扣费量 (条)'
                                }
                            },
                            plotOptions: {
                                line: {
                                    dataLabels: {
                                        enabled: true          // 开启数据标签
                                    },
                                    enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                                }
                            },
                            series: data.seriesData
                        });

                        new Highcharts.Chart({
                            chart: {
                                renderTo: 'container_2',
                                type: 'column',
                                reflow: true
                            },
                            title: {
                                text: '上月各产品扣费量'
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            xAxis: {
                                categories: data.xList_2,
                                crosshair: true
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: ''
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f} 条</b></td></tr>',
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
                            series: data.seriesData_2
                        });
                    }
                }
            });

        });

    </script>

    </#if>

</@layout>
