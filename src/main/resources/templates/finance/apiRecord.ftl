
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

                                <label class="control-label">产品状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="0">checked="checked"</#if></#list></#if> id="status" name="status" value="0">正在使用

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="-1">checked="checked"</#if></#list></#if> id="status" name="status" value="-1">已停用

                                    </label>

                                </div>

                            </div>

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

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

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

                                        <label><input type="checkbox" checked data-column="4">调用次数</label>

                                        <label><input type="checkbox" checked data-column="5">扣费次数</label>

                                        <label><input type="checkbox" data-column="6">上周消费</label>

                                        <label><input type="checkbox" data-column="7">上月消费</label>

                                        <label><input type="checkbox" checked data-column="8">本月消费</label>

                                        <label><input type="checkbox" checked data-column="9">当天消费</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">上周消费总额&yen;：${(weekTotleAmount!0)/100.0}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">上月消费总额&yen;：${(monthTotleAmount!0)/100.0}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">本月消费总额&yen;：${(currMonthTotleCost!0)/100.0}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">当天消费总额&yen;：${(currDayTotleCost!0)/100.0}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">消费总额(${beginDate!'开通后'}--${endDate!'至今'})&yen;：${(consumeTotleAmount!0)/100.0}元</label>

                                </div>

                            <#--总消费-->
                            <#--  <div class="pull-left table-top-bottom">

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

                              </div>-->

                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th>产品类型</th>
                                        <th>产品供应商</th>
                                        <th>产品名称</th>
                                        <th>消费总额（单位：元，${beginDate!'开通后'}--${endDate!'至今'})</th>
                                        <th>调用次数</th>
                                        <th>扣费次数</th>
                                        <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                        <th>${year!''}年${month!''}月消费（单位：元）</th>
                                        <th>${currYear!''}年${currMonth!''}月消费（单位：元）</th>
                                        <th>${currYear!''}年${currMonth!''}月第${currDay!''}日消费（单位：元）</th>
                                        <th style="text-align: center;">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiFinanceList??>
                                            <#list apiFinanceList as apiFinance>
                                            <tr>
                                                <#if apiFinance.status == 0>
                                                <td data-title="产品类型">
                                                <#else >
                                                <td data-title="产品类型" class="font-text-decoration">
                                                </#if>
                                                <a href="/api/find-all-api-record<#if apiFinance.apiTypeId??>?apiTypeId=${apiFinance.apiTypeId}</#if>">
                                                ${apiFinance.apiTypeName}
                                                    <#if (apiFinance.mobileOperatorList?size>0)>--
                                                        <#list apiFinance.mobileOperatorList as mobileOperator>
                                                        ${mobileOperator.name!''}<#if (apiFinance.mobileOperatorList?size>1)>,</#if>
                                                        </#list>
                                                    </#if>
                                                </a>
                                            </td>
                                                <td data-title="产品供应商"><a href="/api/find-all-api-record<#if apiFinance.vendorId??>?vendorId=${apiFinance.vendorId}</#if>">${apiFinance.vendorName!'无'}</a></td>
                                                <td data-title="产品名称">${apiFinance.apiName}</td>
                                                <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="调用次数"><#if apiFinance.usageAmount??>${(apiFinance.usageAmount)?c}<#else >0</#if></td>
                                                <td data-title="扣费次数"><#if apiFinance.feeUsageAmount??>${(apiFinance.feeUsageAmount)?c}<#else >0</#if></td>
                                                <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="本月消费"><#if apiFinance.currMonthCost??>${(apiFinance.currMonthCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="当天消费"><#if apiFinance.currDayCost??>${(apiFinance.currDayCost/100.0)?c}<#else >0</#if></td>
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
