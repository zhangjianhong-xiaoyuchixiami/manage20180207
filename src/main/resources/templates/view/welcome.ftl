
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

                    <div class="tabbable tabbable-custom tabbable-full-width">

                        <@shiro.hasAnyRoles name="backAdmin">

                            <div class="row-fluid">

                                <div class="row-fluid" style="margin-top: 20px">

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat blue">

                                            <div class="details">

                                                <div class="desc">当天总收入金额</div>

                                                <div class="number" id="currIncome"></div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat yellow">

                                            <div class="details">

                                                <div class="desc">当天总成本</div>

                                                <div class="number" id="currCost"></div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat green">

                                            <div class="details">
                                                <div class="desc">当天毛利润</div>

                                                <div class="number" id="currProfit"></div>

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

                                                <div class="desc">昨天同时段总收入金额</div>

                                                <div class="number" id="yesterIncome"></div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat yellow">

                                            <div class="details">

                                                <div class="desc">昨天同时段总成本</div>

                                                <div class="number" id="yesterCost"></div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat green">

                                            <div class="details">
                                                <div class="desc">昨天同时段毛利润</div>

                                                <div class="number" id="yesterProfit"></div>

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

                                                <div class="number" id="yesterTotalIncome"></div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat yellow">

                                            <div class="details">

                                                <div class="desc">昨天总成本</div>

                                                <div class="number" id="yesterTotalCost"></div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="span3 responsive" data-tablet="span4" data-desktop="span3">

                                        <div class="dashboard-stat green">

                                            <div class="details">
                                                <div class="desc">昨天毛利润</div>

                                                <div class="number" id="yesterTotalProfit"></div>

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                            <div class="row-fluid">

                                <div class="span3 responsive" data-tablet="span12  fix-offset" data-desktop="span3">

                                    <div class="portlet box blue">

                                        <div class="portlet-title">

                                            <div class="caption">和昨天同比情况</div>

                                        </div>

                                        <div class="portlet-body">

                                            <div class="row-fluid">

                                                <div class="span4">

                                                    <div class="easy-pie-chart">

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

                        </@shiro.hasAnyRoles>

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
            beforeSend: LoadFunction,
            error: erryFunction,
            success: succFunction
        });
        function LoadFunction() {
            $("#list").html('加载中...');
        }
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


</@layout>