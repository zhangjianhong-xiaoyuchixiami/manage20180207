
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/api/find-all-api-record" class="form-bottom api_record" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select id="apiTypeId" name="apiTypeId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if apiTypeId?? && apiTypeId==apiType.id>selected="selected"</#if> value="${apiType.id}">${apiType.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品供应商</label>

                                <div class="controls">

                                    <select id="vendorId" name="vendorId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">正在使用的产品</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">已停用的产品</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                            <#--正在使用的产品-->
                                <div class="portlet box grey">

                                    <div class="portlet-title">

                                        <@d.tools idName="exportExcel" hrefName="/api/find-all-api-record?export=true"></@d.tools>

                                        <div class="actions">

                                            <div class="btn-group">

                                                <a class="btn" href="#" data-toggle="dropdown">

                                                    表格显示列

                                                    <i class="icon-angle-down"></i>

                                                </a>

                                                <div id="sample_1_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                                    <label><input type="checkbox" checked data-column="0">产品类型</label>

                                                    <label><input type="checkbox" checked data-column="1">产品供应商</label>

                                                    <label><input type="checkbox" data-column="2">产品名称</label>

                                                    <label><input type="checkbox" checked data-column="3">消费总额</label>

                                                    <label><input type="checkbox" data-column="4">上周消费</label>

                                                    <label><input type="checkbox" checked data-column="5">上月消费</label>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 115px;">总产品：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${(weekTotleAmount!0+weekTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${(monthTotleAmount!0+monthTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${(consumeTotleAmount!0+consumeTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 115px;">正在使用的产品：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${(weekTotleAmount!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${(monthTotleAmount!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${(consumeTotleAmount!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        <#--总消费-->
                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">

                                                    <a id="columnHistogram" href="#form_modal7" data-toggle="modal">

                                                        <i class="icon-bar-chart"></i>总消费

                                                    </a>

                                                </label>

                                                <div id="form_modal7" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true">

                                                    <div class="modal-header">

                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                                        <h3 id="myModalLabel7">&nbsp;</h3>

                                                    </div>

                                                    <div class="modal-body">
                                                        <div id="columnHistogramContainer">

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                                <thead>
                                                <tr>
                                                    <th>产品类型</th>
                                                    <th>产品供应商</th>
                                                    <th>产品名称</th>
                                                    <th>消费总额（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                                    <th>${year!''}年${month!''}月消费（单位：元）</th>
                                                    <th style="text-align: center; width: 13%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if apiFinanceList??>
                                                        <#list apiFinanceList as apiFinance>
                                                        <tr>
                                                            <td data-title="产品类型">${apiFinance.apiTypeName}
                                                                <#if (apiFinance.mobileOperatorList?size>0)>--
                                                                    <#list apiFinance.mobileOperatorList as mobileOperator>
                                                                    ${mobileOperator.name!''}<#if (apiFinance.mobileOperatorList?size>1)>,</#if>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品供应商"><a href="/api/find-all-api-record<#if apiFinance.vendorId??>?vendorId=${apiFinance.vendorId}</#if>">${apiFinance.vendorName!'无'}</a></td>
                                                            <td data-title="产品名称">${apiFinance.apiName}</td>
                                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="操作" style="text-align: center;">
                                                                <a href="/api/find-all-api-record/detail?apiId=${apiFinance.apiId?c}&apiTypeName=${apiFinance.apiTypeName}&vendorName=${apiFinance.vendorName}<#if apiFinance.mobileOperator??>&mobileOperatorName=${apiFinance.mobileOperator.name}</#if>">消费明细</a>
                                                            </td>
                                                        </tr>
                                                        </#list>
                                                    </#if>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane " id="tab_2">

                            <#--已停用的产品-->
                                <div class="portlet box grey">

                                    <div class="portlet-title">

                                        <@d.tools idName="exportExcel" hrefName="/api/find-all-api-record?export=true&dead=true"></@d.tools>

                                        <div class="actions">

                                            <div class="btn-group">

                                                <a class="btn" href="#" data-toggle="dropdown">

                                                    表格显示列

                                                    <i class="icon-angle-down"></i>

                                                </a>

                                                <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                                    <label><input type="checkbox" checked data-column="0">产品类型</label>

                                                    <label><input type="checkbox" checked data-column="1">产品供应商</label>

                                                    <label><input type="checkbox" data-column="2">产品名称</label>

                                                    <label><input type="checkbox" checked data-column="3">消费总额</label>

                                                    <label><input type="checkbox" data-column="4">上周消费</label>

                                                    <label><input type="checkbox" checked data-column="5">上月消费</label>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 105px;">总产品：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${(weekTotleAmount!0+weekTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${(monthTotleAmount!0+monthTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${(consumeTotleAmount!0+consumeTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>
                                        </div>

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 105px;">已停用的产品：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${(weekTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${(monthTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${(consumeTotleAmountDead!0)/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                                <thead>
                                                <tr>
                                                    <th>产品类型</th>
                                                    <th>产品供应商</th>
                                                    <th>产品名称</th>
                                                    <th>消费总额（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                                    <th>${year!''}年${month!''}月消费（单位：元）</th>
                                                    <th style="text-align: center; width: 13%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if apiFinanceListDead??>
                                                        <#list apiFinanceListDead as apiFinance>
                                                        <tr>
                                                            <td data-title="产品类型" class="font-text-decoration">${apiFinance.apiTypeName}
                                                                <#if (apiFinance.mobileOperatorList?size>0)>--
                                                                    <#list apiFinance.mobileOperatorList as mobileOperator>
                                                                    ${mobileOperator.name!''}<#if (apiFinance.mobileOperatorList?size>1)>,</#if>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品供应商"><a href="/api/find-all-api-record<#if apiFinance.vendorId??>?vendorId=${apiFinance.vendorId}</#if>">${apiFinance.vendorName!'无'}</a></td>
                                                            <td data-title="产品名称">${apiFinance.apiName}</td>
                                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="操作" style="text-align: center;">
                                                                <a href="/api/find-all-api-record/detail?apiId=${apiFinance.apiId?c}&apiTypeName=${apiFinance.apiTypeName}&vendorName=${apiFinance.vendorName}<#if apiFinance.mobileOperator??>&mobileOperatorName=${apiFinance.mobileOperator.name}</#if>">消费明细</a>
                                                            </td>
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

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/api.js"></script>

    <script src="/js/oldlocal/api-Record.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            Api.init();
        });
    </script>

    </#if>

</@layout>
