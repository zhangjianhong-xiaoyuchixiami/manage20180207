
<#include "../publicPart/layout.ftl">

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid profile">

                <div class="span12">

                    <div class="tabbable tabbable-custom tabbable-full-width">

                        <div class="row-fluid">

                            <div class="row-fluid" style="margin-top: 20px">

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat blue">

                                        <div class="details">

                                            <div class="desc">当天总收入金额</div>

                                            <div class="number" id="currIncome">1000</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat yellow">

                                        <div class="details">

                                            <div class="desc">当天总成本</div>

                                            <div class="number" id="currCost">100</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat green">

                                        <div class="details">
                                            <div class="desc">当天毛利润</div>

                                            <div class="number" id="currProfit">900</div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="row-fluid">

                            <div class="row-fluid" style="margin-top: 20px">

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat blue">

                                        <div class="details">

                                            <div class="desc">昨天总收入金额</div>

                                            <div class="number" id="yesterIncome">900</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat yellow">

                                        <div class="details">

                                            <div class="desc">昨天总成本</div>

                                            <div class="number" id="yesterCost">200</div>

                                        </div>

                                    </div>

                                </div>

                                <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                    <div class="dashboard-stat green">

                                        <div class="details">
                                            <div class="desc">昨天毛利润</div>

                                            <div class="number" id="yesterProfit">700</div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="row-fluid">

                            <div class="span3 responsive" data-tablet="span12  fix-offset" data-desktop="span3">

                                <div class="portlet box blue">

                                    <div class="portlet-title">

                                        <div class="caption">同比情况</div>

                                    </div>

                                    <div class="portlet-body">

                                        <div class="row-fluid">

                                            <div class="span4">

                                                <div class="easy-pie-chart">

                                                    <div>总收入</div>

                                                    <div class="number transactions" data-percent="55"><span id="totalIncomePercent">+11.11</span>
                                                    </div>

                                                </div>

                                            </div>

                                            <div class="margin-bottom-10 visible-phone"></div>

                                            <div class="span4">

                                                <div class="easy-pie-chart">

                                                    <div>总成本</div>

                                                    <div class="number visits" data-percent="85"><span id="totalCostPercent">-50</span></div>

                                                </div>

                                            </div>

                                            <div class="margin-bottom-10 visible-phone"></div>

                                            <div class="span4">

                                                <div class="easy-pie-chart">

                                                    <div>毛利润</div>

                                                    <div class="number bounce" data-percent="46"><span id="profitPrecent">+28.57</span></div>

                                                </div>

                                            </div>

                                        </div>

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
                timeout: 3000,
                cache: false,
                beforeSend: LoadFunction,
                error: erryFunction,
                success: succFunction
            })
            function LoadFunction() {
                $("#list").html('加载中...');
            }
            function erryFunction() {
                alert("维护中...请联系技术人员");
            }
            function succFunction(data) {
                $("#currIncome").text(data.currIncomeAccount)
                $("#yesterIncome").text(data.yesterIncomeAccount)
                $("#currCost").text(data.currCostAccount)
                $("#yesterCost").text(data.yesterCostAccount)
                $("#currProfit").text(data.currProfit)
                $("#yesterProfit").text(data.yesterProfit)
                $("#profitPrecent").text(data.profitPercent)
                $("#totalCostPercent").text(data.costPercent)
                $("#totalIncomePercent").text(data.incomePercent)
            }
        });
    </script>


</@layout>