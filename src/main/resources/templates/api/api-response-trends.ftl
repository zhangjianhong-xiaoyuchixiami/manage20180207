
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

                                <div class="caption"><i class="icon-bar-chart"></i>(apiId = ${apiId})${apiName!'无'}(${vendorName})</div>

                            </div>

                            <div class="portlet-body">

                                <div id="site_statistics_loading">

                                    <div style="min-width:400px;height:400px" id="container"></div>

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

    <script src="/js/myjs/api-response-condition.js?v=${ver}"></script>

    <script src="/js/myjs/api_finance_condition_left_bar.js?v=${ver}"></script>

    <script src="../../js/highcharts/highcharts.js"></script>
    <script src="../../js/highcharts/series-label.js"></script>
    <script src="../../js/highcharts/exporting.js"></script>

    <script type="text/javascript">
        (function($){
            $.getUrlParam = function(name)
            {
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
            }
        })(jQuery);
    $(function () {
        var apiId = $.getUrlParam("apiId");
            $.ajax({
                type: "GET",
                url: "/api-response-condition/api-response-nearly-week-thread/data",
                data: {"apiId": apiId},
                dataType: "json",
                timeout: 30000,
                cache: false,
                success: succFunction
            });
            function succFunction (data) {

                if (data != null) {

                    Highcharts.setOptions({
                        lang: {
                            resetZoom: "重置"
                        }
                    });
                    var chart = new Highcharts.chart('container', {

                        chart: {
                            zoomType: '',
                            panning: false,
                            panKey: 'shift',
                            resetZoom:'重置',
                            resetZoomButton: {
                                position: {
                                    x: 0,
                                    y: -50
                                }
                            }

                        },

                        title: {
                            text: '产品响应时间分布折线图'
                        },

                        yAxis: {
                            labels: {
                                max: 100,
                                format:"{value}%"
                                },
                            title: {
                                text: '百分比(%)'
                            }
                        },

                        xAxis : [{
                            categories : data.xList,
                            min:3,
                            max:17,
                            labels:{
                                rotation:-30
                            },
                            tickInterval:1,
                            floor: 0,
                            ceiling: 17,
                            endOnTick:true
                        }],

                        scrollbar : {
                            enabled:true
                        },

                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'middle',
                            borderWidth: 0
                        },

                        plotOptions: {
                            series: {
                                label: {
                                    connectorAllowed: false
                                },
                                dataLabels: {
                                    enabled: false,
                                    format: '{y} %'
                                }
                            }
                        },

                        tooltip: {
                            split: true
                        },

                        responsive: {
                            rules: [{
                                condition: {
                                    maxWidth:500
                                }
                            }]
                        },

                        exporting:{
                            enabled:false
                        },

                        series: data.seriesData

                    });

                    var xAxis = chart.xAxis[0],
                            xMin = xAxis.dataMin,
                            xMax = xAxis.dataMax;
                    Highcharts.addEvent(document.getElementById('container'), document.onmousewheel === undefined ? 'DOMMouseScroll': 'mousewheel', function(e){
                        var step = e.wheelDelta > 0 ? -2 : 2,
                                min = xAxis.min + step,
                                max = xAxis.max + step;
                        if(min < xMin || max > xMax) {
                            return false;
                        }
                        xAxis.setExtremes(min, max);
                        e.preventDefault();
                    });
                }
            }
        });
    </script>

    <script>
        jQuery(document).ready(function() {
            ApiResponseCondition.init();
            ApiFinanceConditionLeftBar.init();
        });

    </script>

</#if>

</@layout>
