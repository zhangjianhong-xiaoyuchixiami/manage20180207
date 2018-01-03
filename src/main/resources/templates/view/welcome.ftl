
<#include "../publicPart/layout.ftl">

<#import "../publicPart/publicJs.ftl" as puj>

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid profile">

                <div class="span12">

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li ><a href="#tab_1" data-toggle="tab">近两日经营数据表格</a></li>

                            <li class="active"><a href="#tab_2" data-toggle="tab">近一周经营状况折线图</a></li>

                        </ul>

                        <div class="tabbable tabbable-custom tabbable-full-width">

                            <div class="tab-content">

                                <@shiro.hasAnyRoles name="backAdmin">

                                <div class="tab-pane" id="tab_1">

                                    <div class="row-fluid">

                                        <div class="row-fluid" style="margin-top: 0px" >

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat blue">

                                                    <div class="details">

                                                        <div class="desc">今日总收入金额</div>

                                                        <div class="number" id="currIncome"></div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat yellow">

                                                    <div class="details">

                                                        <div class="desc">今日总成本</div>

                                                        <div class="number" id="currCost"></div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat green">

                                                    <div class="details">
                                                        <div class="desc">今日毛利润</div>

                                                        <div class="number" id="currProfit"></div>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="row-fluid">

                                        <div class="row-fluid" style="margin-top: 0px">

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat blue">

                                                    <div class="details">

                                                        <div class="desc">昨日同时段总收入金额</div>

                                                        <div class="number" id="yesterIncome"></div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat yellow">

                                                    <div class="details">

                                                        <div class="desc">昨日同时段总成本</div>

                                                        <div class="number" id="yesterCost"></div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat green">

                                                    <div class="details">
                                                        <div class="desc">昨日同时段毛利润</div>

                                                        <div class="number" id="yesterProfit"></div>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="row-fluid">

                                        <div class="row-fluid" style="margin-top: 0px">

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat blue">

                                                    <div class="details">

                                                        <div class="desc">昨日总收入金额</div>

                                                        <div class="number" id="yesterTotalIncome"></div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat yellow">

                                                    <div class="details">

                                                        <div class="desc">昨日总成本</div>

                                                        <div class="number" id="yesterTotalCost"></div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="span3 responsive" data-tablet="span4" data-desktop="span3" style="width: 31%">

                                                <div class="dashboard-stat green">

                                                    <div class="details">
                                                        <div class="desc">昨日毛利润</div>

                                                        <div class="number" id="yesterTotalProfit"></div>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="row-fluid">

                                        <div class="span3 responsive" data-tablet="span12  fix-offset" data-desktop="span3" style="width: 100%">

                                            <div class="portlet box blue">

                                                <div class="portlet-title">

                                                    <div class="caption">和昨日同比情况</div>

                                                </div>

                                                <div class="portlet-body" style="width: 100%">

                                                    <div class="row-fluid">

                                                        <div class="span4" >

                                                            <div class="easy-pie-chart" >

                                                                <div>总收入</div>

                                                                <div class="number transactions" data-percent="55"><span id="totalIncomePercent"></span>
                                                                </div>

                                                            </div>

                                                        </div>

                                                        <div class="margin-bottom-10 visible-phone"></div>

                                                        <div class="span4">

                                                            <div class="easy-pie-chart">

                                                                <div>总成本</div>

                                                                <div class="number visits" data-percent="85"><span id="totalCostPercent"></span></div>

                                                            </div>

                                                        </div>

                                                        <div class="margin-bottom-10 visible-phone"></div>

                                                        <div class="span4">

                                                            <div class="easy-pie-chart">

                                                                <div>毛利润</div>

                                                                <div class="number bounce" data-percent="46"><span id="profitPrecent"></span></div>

                                                            </div>

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </div>
                                </@shiro.hasAnyRoles>

                                <div class="tab-pane active" id="tab_2">

                                    <div class="row-fluid">

                                        <div id="container" style="min-width: 100%; height: 400px; margin: 0 auto"></div>

                                    </div>

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

    </#if>

<script type="text/javascript">

    $(function () {
        $.ajax({
            url: '/operation/operation_condition',
            type: 'GET',
            dataType: 'json',
            timeout: 30000,
            cache: false,
//            beforeSend: LoadFunction,
            error: erryFunction,
            success: succFunction
        });
       /* function LoadFunction() {
            $("#tabbable tabbable-custom tabbable-full-widt").html('加载中...');
        }*/
        function erryFunction() {
            alert("维护中...请联系技术人员");
        }
        function succFunction(data) {
            $("#currIncome").text(data.currIncomeAccount);
            $("#yesterIncome").text(data.yesterIncomeAccount);
            $("#yesterTotalIncome").text(data.yesterTotalIncomeAccount);
            $("#currCost").text(data.currCostAccount);
            $("#yesterCost").text(data.yesterCostAccount);
            $("#yesterTotalCost").text(data.yesterTotalCostAccount);
            $("#currProfit").text(data.currProfit);
            $("#yesterProfit").text(data.yesterProfit);
            $("#yesterTotalProfit").text(data.yesterTotalProfit);
            $("#profitPrecent").text(data.profitPercent);
            $("#totalCostPercent").text(data.costPercent);
            $("#totalIncomePercent").text(data.incomePercent)
        }
    });
</script>

<script src="../../js/highcharts/highcharts.js"></script>
<script src="../../js/highcharts/series-label.js"></script>
<script src="../../js/highcharts/exporting.js"></script>

<script type="text/javascript">

    $(function () {

        $.ajax({
            type: "GET",
            url: "/operation/operation-nearly-week-thread/data",
            dataType: "json",
            timeout: 30000,
            cache: false,
            success: succFunction
        });
        function succFunction (data) {
            if (data != null) {

                Highcharts.chart('container', {

                    title: {
                        text: '近一周公司经营状况'
                    },

                    yAxis: {
                        title: {
                            text: '金额(元)'
                        }
                    },

                    xAxis : {
                        categories : data.xList
                    },

                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle'
                    },

                    plotOptions: {
                        series: {
                            label: {
                                connectorAllowed: false
                            },
                            pointStart: 0
                        }
                    },
                    responsive: {
                        rules: [{
                            condition: {
                                maxWidth: 500
                            },
                            chartOptions: {
                                legend: {
                                    layout: 'horizontal',
                                    align: 'center',
                                    verticalAlign: 'bottom'
                                }
                            }
                        }]
                    },

                    exporting:{
                        enabled:false
                    },
                    series: data.seriesData

                });
            }
        }
    });
</script>

</@layout>