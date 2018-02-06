
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<#import "../publicPart/publicJs.ftl" as puj>

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

                                <label>产品状态</label>

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

                                <label>产品类型</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="apiTypeId" name="apiTypeId">
                                        <option value=""></option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if apiTypeId?? && apiTypeId==apiType.id>selected="selected"</#if> value="${apiType.id!''}">${apiType.name!''}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>产品供应商</label>

                                <div id="vendorId_chosen" class="controls">

                                    <select class="medium m-wrap" id="vendorId" name="vendorId">
                                        <option value=""></option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label>起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

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

                                        <#--<label><input type="checkbox" checked data-column="4">调用成功次数</label>-->

                                        <label><input type="checkbox" checked data-column="4">扣费次数</label>

                                        <label><input type="checkbox" data-column="5">上周消费</label>

                                        <label><input type="checkbox" data-column="6">上月消费</label>

                                        <label><input type="checkbox" checked data-column="7">本月消费</label>

                                        <label><input type="checkbox" checked data-column="8">当天消费</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <table class="table-condensed flip-content" style="width: 100%">
                                    <tbody>
                                    <tr>
                                        <td>上周消费总额：${lastWeekConsume!'0.0'}</td>
                                        <td>上月消费总额：${lastMonthConsume!'0.0'}</td>
                                        <td>当月消费总额：${currMonthConsume!'0.0'}</td>
                                    </tr>
                                    <tr>
                                        <td>当天消费总额：${currDayConsume!'0.0'}</td>
                                        <td>消费总额：${totleConsume!'0.0'}</td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th>产品类型</th>
                                        <th>产品供应商</th>
                                        <th>产品名称</th>
                                        <th>消费总额（${beginDate!'开通后'}--${endDate!'至今'})</th>
                                        <#--<th>调用成功次数</th>-->
                                        <th>扣费次数</th>
                                        <th>上周消费</th>
                                        <th>上月消费</th>
                                        <th>当月消费</th>
                                        <th>当天消费</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if financeList??>
                                            <#list financeList as finance>
                                            <tr>
                                                <td data-title="产品类型"  <#if finance.status == -1> class="font-text-decoration" </#if> >
                                                    <a href="/api/find-all-api-record<#if finance.apiTypeId??>?apiTypeId=${finance.apiTypeId}</#if>">${(finance.apiTypeName)!'无'}</a>
                                                </td>
                                                <td data-title="产品供应商"><a href="/api/find-all-api-record<#if finance.vendorId??>?vendorId=${finance.vendorId}</#if>">${finance.vendorName!'无'}</a></td>
                                                <td data-title="产品名称">${finance.apiName}</td>
                                                <td data-title="消费总额">${finance.consume!'0'}</td>
                                                <#--<td data-title="调用成功次数">${finance.userCount!'0'}</td>-->
                                                <td data-title="扣费次数">${finance.feeCount!'0'}</td>
                                                <td data-title="上周消费">${finance.lastWeekConsume!'0'}</td>
                                                <td data-title="上月消费">${finance.lastMonthConsume!'0'}</td>
                                                <td data-title="本月消费">${finance.currMonthConsume!'0'}</td>
                                                <td data-title="当天消费">${finance.currDayConsume!'0'}</td>
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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/api.js?v=${ver}"></script>

    <script src="/js/oldlocal/api-Record.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            Api.init();

            $("#apiTypeId").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

            $("#vendorId").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

        });
    </script>

    </#if>

</@layout>
