
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

                    <form action="/finance/customer-history-bill/detail/log" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <input style="display: none" id="id" name="id" value="${id?c}">

                            <input style="display: none" id="cName" name="cName" value="${cName!''}">

                            <input style="display: none" id="tName" name="tName" value="${tName!''}">

                            <input style="display: none" id="cyc" name="cyc" value="${cyc!''}">

                            <div class="pull-left head-search-bottom">

                                <label>请选择修改类型</label>

                                <div class="controls">
                                    <select class="medium m-wrap" id="typeId" name="typeId">
                                        <option value=""></option>
                                        <option <#if typeId?? && typeId == 1> selected </#if> value="1">修改单价</option>
                                        <option <#if typeId?? && typeId == 2> selected </#if> value="2">修改扣费量</option>
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

                        <div class="portlet-title">

                            <div class="caption">${cyc!'无'}--${cName!'无'}@${tName!'无'}</div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <div class="pull-right tip-remark">
                                    <span class="pull-right">注：本页面与金额相关数字单位都为：元</span>
                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>

                                    <th>修改类型</th>
                                    <th>修改之前值</th>
                                    <th>修改之后值</th>
                                    <th>备注</th>
                                    <th>修改时间</th>

                                </tr>
                                </thead>
                                <tbody>
                                    <#if logList??>
                                        <#list logList as log>
                                        <tr>
                                            <td data-title="修改类型">${log.typeName!'无'}</td>
                                            <td data-title="修改之前值">${log.beforData!'0'}</td>
                                            <td data-title="修改之后值">${log.afterData!'0'}</td>
                                            <td data-title="备注">${log.content!'无'}</td>
                                            <td data-title="修改时间">${log.createTime!'无'}</td>
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

    <script src="/js/myjs/customer_history_bill_month_detail_update_log.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            CustomerHistoryBillMonthDetailUpdateLog.init();
        });

    </script>

    </#if>

</@layout>
