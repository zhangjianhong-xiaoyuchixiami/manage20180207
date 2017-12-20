
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
                                        <th>总消费</th>
                                        <th>总消费同比</th>
                                        <th>总成本</th>
                                        <th>总成本同比</th>
                                        <th>毛利润</th>
                                        <th>毛利润同比</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td data-title="产品名称" rowspan="2" style="text-align: center">银行卡三要素</td>
                                        <td data-title="周期">今天</td>
                                        <td data-title="总消费"><a href="#form_modal_1" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="点击查看当天客户消费情况">1000</a></td>
                                        <td data-title="总消费同比" rowspan="2" style="text-align: center">+5%</td>
                                        <td data-title="总成本"><a href="#form_modal_3" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="点击查看当天供应商消费情况">1000</a></td>
                                        <td data-title="总成本同比" rowspan="2" style="text-align: center">-7%</td>
                                        <td data-title="毛利润">1000</td>
                                        <td data-title="毛利润同比" rowspan="2" style="text-align: center">+20%</td>
                                    </tr>
                                    <tr>
                                        <td class="table-td-none"></td>
                                        <td data-title="周期">昨天</td>
                                        <td data-title="总消费"><a href="#form_modal_2" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="点击查看昨天客户消费情况">1000</a></td>
                                        <td class="table-td-none"></td>
                                        <td data-title="总成本"><a href="#form_modal_4" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="点击查看昨天供应商消费情况">1000</a></td>
                                        <td class="table-td-none"></td>
                                        <td data-title="毛利润">1000</td>
                                        <td class="table-td-none"></td>
                                    </tr>
                                    </tbody>

                                </table>

                            </div>

                        </div>

                    </div>

                <#--客户当天消费弹框-->
                    <div id="form_modal_1" class="modal hide fade myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_1" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_1">当天客户消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="simple_1">
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
                    <div id="form_modal_2" class="modal hide fade myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_2" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_2"></span>昨天客户消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="simple_2">
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
                    <div id="form_modal_3" class="modal hide fade myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_3" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_3"></span>当天供应商消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="simple_3">
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
                    <div id="form_modal_4" class="modal hide fade myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_4" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_4">昨天供应商消费情况</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="simple_4">
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

    <script>
        jQuery(document).ready(function() {
            ApiConsumeCondition.init();
            ApiFinanceConditionLeftBar.init();

        });

    </script>
    </#if>

</@layout>
