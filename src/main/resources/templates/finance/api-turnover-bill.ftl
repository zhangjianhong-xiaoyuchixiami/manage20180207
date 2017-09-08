
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

                    <form action="/finance/api-turnover-bill" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>请选择产品</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="tid" name="tid">
                                        <option value=""></option>
                                        <#if typeList??>
                                            <#list typeList as type>
                                                <option <#if tid??><#list tid as tid><#if tid?? && tid == type.typeId_stid>selected="selected"</#if></#list></#if> value="${type.typeId_stid}">${type.type_stid_name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择周期</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="cyc" name="cyc">
                                        <option value=""></option>
                                        <#if conTimeList??>
                                            <#list conTimeList as conTime>
                                                <option <#if cyc?? && cyc == conTime> selected="selected"</#if> value="${conTime}">${conTime}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">
                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">上游消费总额&yen;：${vendorTot!'0.0'}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">客户消费总额&yen;：${customerTot!'0.0'}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">盈利总额&yen;：${profitTot!'0.0'}元</label>

                                </div>

                                <div class="pull-right tip-remark">
                                    <span class="pull-right">注：1、本页面与金额相关数字单位都为：元</span>
                                    </br>
                                    <span class="pull-right">注：2、均不包含当月消费情况</span>
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>
                                    <th>产品名称</th>
                                    <th>上游扣费量</th>
                                    <th>客户扣费量</th>
                                    <th>上游消费金额</th>
                                    <th>客户消费金额</th>
                                    <th>盈利</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if billList??>
                                    <#list billList as bill>
                                    <tr <#if bill.status == 0> class="danger" </#if>>
                                        <td data-title="产品名称">${bill.type_stid_name!'无'}</td>
                                        <td data-title="上游扣费量">${bill.vendorAmount!'0'}</td>
                                        <td data-title="客户扣费量">${bill.customerAmount!'0'}</td>
                                        <td data-title="上游消费金额">${bill.vendorConsume!'0'}</td>
                                        <td data-title="客户消费金额">${bill.customerConsume!'0'}</td>
                                        <td data-title="盈利">${bill.profit!'0'}</td>
                                        <td data-title="操作"><a id="consumption_than" href="/finance/api-turnover-bill/trend?tid=${bill.typeId_stid}&name=${bill.type_stid_name!'无'}">消费占比</a></td>
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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/multi/get-url-param.js?v=${ver}"></script>

    <script src="/js/myjs/api_turnover_bill.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            ApiTurnoverBill.init();
        });

        $('#sample_2 a').each(function(){
            var href = $(this).attr('href');
            if(href) {
                href += (href.match(/\?/) ? '&' : '?') + 'cyc=' + $.getUrlParam('cyc');

                $(this).attr('href', href);
            }
        });

    </script>

    </#if>

</@layout>
