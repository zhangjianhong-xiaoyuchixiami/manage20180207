
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

            <div class="row-fluid">

                <div class="span12">

                    <div class="alert alert-success">

                        <button class="close" data-dismiss="alert"></button>

                        本页面与金额有关字段单位都是元<br>

                    </div>

                    <div class="portlet box grey">

                        <div class="portlet-body">

                            <div class="table-responsive">

                                <table class="table table-bordered table-hover table-condensed" id="sample_2">
                                    <thead>
                                    <tr>
                                        <th>产品名称</th>
                                        <th>周期</th>
                                        <th>总收入</th>
                                        <th>总收入同比</th>
                                        <th>总成本</th>
                                        <th>总成本同比</th>
                                        <th>毛利润</th>
                                        <th>毛利润同比</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiOperaCondition??>
                                            <#list apiOperaCondition as condition>
                                                <tr>
                                                    <td data-title="产品名称" rowspan="2" style="text-align: center" >
                                                        <#if condition.apiTypeName??>
                                                        ${condition.apiTypeName!'未知'}
                                                        <#if condition.apiTypeName??>--${condition.subTypeName!''}
                                                        </#if>
                                                        </#if>
                                                    </td>
                                                    <td data-title="周期">今天</td>
                                                    <td data-title="总收入"><a href="#curr_Customer_Income_Condition" onclick="currentConsumerIncomeCondition(${condition.apiTypeId}, ${condition.subTypeId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="点击查看当天客户消费情况">${condition.currIncomeAccount}</a></td>
                                                    <td data-title="总收入同比" rowspan="2" style="text-align: center" >${condition.incomePercent!"昨日同时段该产品无收入"}</td>
                                                    <td data-title="总成本" ><a href="#curr_vendor_cost_Condition" onclick="currentVendorCostCondition(${condition.apiTypeId}, ${condition.subTypeId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="点击查看当天供应商消费情况">${condition.currCostAccount}</a></td>
                                                    <td data-title="总成本同比" rowspan="2" style="text-align: center" >${condition.costPercent!"昨日同时段该产品无支出"}</td>
                                                    <td data-title="毛利润" >${condition.currProfit}</td>
                                                    <td data-title="毛利润同比" rowspan="2" style="text-align: center" >${condition.profitPercent!"昨日同时段该产品无利润"}</td>
                                                </tr>
                                                <tr>
                                                    <td class="table-td-none"></td>
                                                    <td data-title="周期">昨天</td>
                                                    <td data-title="总收入" ><a href="#yest_Customer_Income_Condition" data-toggle="modal" data-toggle="tooltip" onclick="yesterdayConsumerIncomeCondition(${condition.apiTypeId}, ${condition.subTypeId})" data-placement="bottom" title="点击查看昨天客户消费情况">${condition.yesterIncomeAccount}</a></td>
                                                    <td class="table-td-none"></td>
                                                    <td data-title="总成本" ><a href="#yest_vendor_cost_condition" data-toggle="modal" data-toggle="tooltip" onclick="yesterdayVendorCostCondition(${condition.apiTypeId}, ${condition.subTypeId})" data-placement="bottom" title="点击查看昨天供应商消费情况">${condition.yesterCostAccount}</a></td>
                                                    <td class="table-td-none"></td>
                                                    <td data-title="毛利润" >${condition.yesterProfit}</td>
                                                    <td class="table-td-none"></td>
                                                </tr>
                                            </#list>
                                        </#if>
                                    </tbody>

                                </table>

                            </div>

                        </div>

                    </div>

                <#--客户当天消费弹框-->
                    <div id="curr_Customer_Income_Condition" class="modal hide fade myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_1" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_1">当天客户消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="currConsumerIncomeCondition">
                                <thead>
                                <tr>
                                    <th>客户名称</th>
                                    <th>扣费条数</th>
                                    <th>单价</th>
                                    <th>总消费额（单位：元）</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>

                <#--客户昨天消费弹框-->
                    <div id="yest_Customer_Income_Condition" class="modal hide fade myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_2" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_2"></span>昨天客户消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="yestConsumerIncomeCondition">
                                <thead>
                                <tr>
                                    <th>客户名称</th>
                                    <th>扣费条数</th>
                                    <th>单价</th>
                                    <th>总消费额（单位：元）</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>

                <#--供应商当天消费弹框-->
                    <div id="curr_vendor_cost_Condition" class="modal hide fade myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_3" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_3"></span>当天供应商消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="currVendorCostCondition">
                                <thead>
                                <tr>
                                    <th>供应商名称</th>
                                    <th>扣费条数</th>
                                    <th>单价</th>
                                    <th>总成本额（单位：元）</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>

                <#--供应商昨天消费弹框-->
                    <div id="yest_vendor_cost_condition" class="modal hide fade myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_4" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_4">昨天供应商消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="yestVendorCostCondition">
                                <thead>
                                <tr>
                                    <th>供应商名称</th>
                                    <th>扣费条数</th>
                                    <th>单价</th>
                                    <th>总成本额（单位：元）</th>
                                </tr>
                                </thead>
                                <tbody>
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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/api-finance-condition.js?v=${ver}"></script>

    <script src="/js/myjs/api_finance_condition_left_bar.js?v=${ver}"></script>

    <script src="/js/oldlocal/customer-curr-day-api-type-consume.js?v=${ver}"></script>

    <#--<script type="text/javascript">

        $(function () {
            $.ajax({
                url: '/operation/api_operation_condition',
                type: 'GET',
                dataType: 'json',
                timeout: 30000,
                cache: false,
//                beforeSend: LoadFunction,
                error: erryFunction,
                success: succFunction
            });
            /*function LoadFunction() {
                $("#list").html('加载中...');
            }*/
            function erryFunction() {
                alert("维护中...请联系技术人员");
            }
            function succFunction(json) {

                $.each(json,function(index,item){
                    var a = json[index].apiTypeName;
                    var b = json[index].subTypeName;
                    var c = a + '-' + b;
                    alert('apiName'+c);
                    var d = json[index].currIncomeAccount;
                    alert('当日收入'+d)
                    var e = json[index].currCostAccount;
                    alert('当日成本'+e)
                    var f = json[index].currProfit;
                    alert('当日利润'+f)
                    var d1 = json[index].yesterIncomeAccount;
                    alert('昨日收入'+d1)
                    var e1 = json[index].yesterCostAccount;
                    alert('昨日成本'+e1)
                    var f1 = json[index].yesterProfit;
                    alert('昨日利润'+f1)
                    var g = json[index].incomePercent;
                    alert('收入比'+g)
                    var h = json[index].costPercent;
                    alert('成本比'+h)
                    var i = json[index].profitPercent;
                    alert('利润比'+i)
                    var x = json[index].apiTypeId;
                    var y = json[index].subTypeId;
                    $("#apiName").text(c);
                    $("#currIncome").text(d);
                    $("#currCost").text(e);
                    $("#currProfit").text(f);
                    $("#yestIncome").text(d1);
                    $("#yestCost").text(e1);
                    $("#yestProfit").text(f1);

                    $("#costPercent").text(h);
                    $("#incomePercent").text(g);
                    $("#profitPrecent").text(i);
                })
            }
        });
    </script>-->

    <script>
        jQuery(document).ready(function() {
            ApiConsumeCondition.init();
            ApiFinanceConditionLeftBar.init();

        });

    </script>

</#if>

</@layout>
