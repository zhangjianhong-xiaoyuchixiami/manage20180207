
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

                    <div class="portlet box grey">

                        <div class="portlet-body">

                            <div class="table-responsive">

                                <table class="table table-bordered table-hover table-condensed" id="sample_2">
                                    <thead>
                                    <tr>
                                        <th width="151px">产品名称</th>
                                        <th width="151px">供应商名称</th>
                                        <th width="151px">请求总条数</th>
                                        <th width="151px">查看响应时间情况</th>
                                        <th width="151px">查看请求成功率</th>
                                        <th width="151px">操作</th>
                                    </tr>


                                    </thead>
                                    <tbody>
                                        <#if apiResponseConditions??>
                                            <#list apiResponseConditions as condition>
                                                <tr>

                                                    <td data-title="产品名称" rowspan="4" style="text-align: center" >
                                                        <#if condition.apiTypeName??>
                                                        ${condition.apiTypeName!'未知'}
                                                            <#if condition.subTypeName ??>
                                                                --${condition.subTypeName!''}
                                                            </#if>
                                                        </#if>
                                                    </td>
                                                    <td data-title="供应商名称">${condition.vendorName}</td>
                                                    <td data-title="请求总条数">${condition.totalCount}</td>
                                                    <td data-title="查看响应时间情况"><a href="#api_response_time_condition" onclick="currentConsumerIncomeCondition(${condition.apiId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="查看响应时间情况">查看响应时间情况</a></td>
                                                    <td data-title="查看请求成功率" ><a href="#api_response_success_condition" onclick="currentVendorCostCondition(${condition.apiId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="查看请求成功率">查看请求成功率</a></td>
                                                    <td data-title="给此产品添加标签" ><a href="#api_tag" onclick="currentVendorCostCondition(${condition.apiId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="给此产品添加标签">给此产品添加标签</a></td>
                                                </tr>

                                            </#list>
                                        </#if>
                                    </tbody>

                                </table>

                            </div>

                        </div>

                    </div>

                <#--产品响应时间-->
                    <div id="api_response_time_condition" class="modal hide fade myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_1" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_1">产品响应时间</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="currConsumerIncomeCondition">

                                <div></div>

                            </table>
                        </div>
                    </div>

                <#--产品成功率-->
                    <div id="api_response_success_condition" class="modal hide fade myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_2" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_2"></span>产品请求成功率</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="yestConsumerIncomeCondition">

                                <div></div>

                                </tbody>
                            </table>
                        </div>
                    </div>




                <#--产品添加标签-->
                    <div id="api_tag" class="modal hide fade myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_5" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_5"></span>为该产品添加标签</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="yestHourConsumerIncomeCondition">

                                <input type="text" id="abc">

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

    <script src="/js/myjs/api-response-condition.js?v=${ver}"></script>

    <script src="/js/myjs/api_finance_condition_left_bar.js?v=${ver}"></script>

    <#--<script src="/js/oldlocal/customer-curr-day-api-type-consume.js?v=${ver}"></script>-->

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
            ApiResponseCondition.init();
            ApiFinanceConditionLeftBar.init();

        });

    </script>

</#if>

</@layout>
