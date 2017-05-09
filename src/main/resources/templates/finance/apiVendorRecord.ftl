
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

                    <div class="pull-left head-search-bottom head-search-display">

                        <label class="control-label">合作公司Id</label>

                        <div class="controls">

                            <input type="text" id="partnerId" name="partnerId" <#if partnerId??>value="${partnerId?c}"</#if> class="m-wrap medium">

                        </div>
                    </div>

                    <form action="/api/find-all-api-vendor-consume" class="form-bottom api_vendor" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">供应商状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="0">checked="checked"</#if></#list></#if> id="status" name="status" value="0">正在供应

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="-1">checked="checked"</#if></#list></#if> id="status" name="status" value="-1">已停用

                                    </label>

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

                            <div class="caption"></div>

                            <@d.tools idName="exportExcel" hrefName="/api/find-all-api-vendor-consume?export=true"></@d.tools>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_11_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                        <label><input type="checkbox" checked data-column="1">供应商</label>

                                        <label><input type="checkbox" checked data-column="2">合作公司</label>

                                        <label><input type="checkbox" checked data-column="3">消费总额</label>

                                        <label><input type="checkbox" checked data-column="4">所剩余额</label>

                                        <label><input type="checkbox" data-column="5">上周消费</label>

                                        <label><input type="checkbox" data-column="6">上月消费</label>

                                        <label><input type="checkbox" checked data-column="7">本月消费</label>

                                        <label><input type="checkbox" checked data-column="7">当天消费</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body">

                            <div class="clearfix margin-bottom-5">

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">上周消费总额&yen;：<#if weekTotleAmount??><span>${(weekTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">上月消费总额&yen;：<#if monthTotleAmount??><span>${(monthTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">本月消费总额&yen;：<#if currMonthTotleCost??><span>${(currMonthTotleCost/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">当天消费总额&yen;：<#if currDayTotleCost??><span>${(currDayTotleCost/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">消费总额(${beginDate!'开通后'}--${endDate!'至今'})&yen;：<#if consumeTotleAmount??><span>${(consumeTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">所剩余额&yen;：<#if totleBalance??><span>${(totleBalance/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            <#--表字段总额-->
                            <#-- <div class="pull-right table-top-bottom">

                                 <label class="control-label">

                                     <a id="columnVendorHistogram" href="#form_modal7" data-toggle="modal">

                                         <i class="icon-bar-chart"></i>总消费

                                     </a>

                                 </label>

                                 <div id="form_modal7" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true">

                                     <div class="modal-header">

                                         <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                         <h3 id="myModalLabel7">&nbsp;</h3>

                                     </div>

                                     <div class="modal-body">
                                         <div id="columnVendorHistogramContainer">

                                         </div>

                                     </div>

                                 </div>

                             </div>-->

                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_11">
                                    <thead>
                                    <tr>
                                        <th>供应商</th>
                                        <th>合作公司</th>
                                        <th>消费总额（单位：元，${beginDate!'开通后'}--${endDate!'至今'})</th>
                                        <th>所剩余额（单位：元）</th>
                                        <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                        <th>${year!''}年${month!''}月消费（单位：元）</th>
                                        <th>${currYear!''}年${currMonth!''}月消费（单位：元）</th>
                                        <th>${currYear!''}年${currMonth!''}月${currDay!''}日消费（单位：元）</th>
                                        <th style="text-align: center; width: 5%;">操作</th>
                                        <th class="table-td-none">类型</th>
                                        <th class="table-td-none">当前价格</th>
                                        <th class="table-td-none">消费总额</th>
                                        <th class="table-td-none">调用次数</th>
                                        <th class="table-td-none">扣费次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiFinanceList??>
                                            <#list apiFinanceList as apiFinance>
                                            <tr>
                                                <#if apiFinance.status == 0>
                                                <td data-title="供应商">
                                                <#else >
                                                <td data-title="供应商" class="font-text-decoration">
                                                </#if>
                                                    <a href="/api/find-all-api-record<#if apiFinance.vendorId??>?vendorId=${apiFinance.vendorId}</#if>">
                                                    ${apiFinance.vendorName}
                                                    </a>
                                                </td>
                                                <td data-title="合作公司">
                                                    <#if apiFinance.partnerId??>
                                                        <a href="/api/find-all-api-vendor-consume?partnerId=${apiFinance.partnerId?c}">${apiFinance.partnerName!'无'}</a>
                                                    <#else >
                                                        无
                                                    </#if>
                                                </td>
                                                <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="所剩余额"><#if apiFinance.balance??>${(apiFinance.balance/100.0)?c}<#else >0</#if></td>
                                                <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="本月消费"><#if apiFinance.currMonthCost??>${(apiFinance.currMonthCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="当天消费"><#if apiFinance.currDayCost??>${(apiFinance.currDayCost/100.0)?c}<#else >0</#if></td>
                                                <td data-title="操作" style="text-align: center;width: 5%;">
                                                    <a href="#form_modal4" onclick="charge(${apiFinance.vendorId})" data-toggle="modal">充值</a>
                                                </td>
                                                <td data-title="类型" class="table-td-none">
                                                    <#if apiFinance.apiTypeConsumeList??>
                                                        <#list apiFinance.apiTypeConsumeList as apiType>
                                                            <#if apiType.apiStatus==-1>
                                                            <span class="font-text-decoration">
                                                            <#else>
                                                            <span>
                                                            </#if>
                                                        ${apiType.apiTypeName!''}
                                                            <#if (apiType.mobileOperatorList?size>0)>--
                                                                <#list apiType.mobileOperatorList as mobileOperator>
                                                                ${mobileOperator.name}<#if (apiType.mobileOperatorList?size>1)>,</#if>
                                                                </#list>
                                                            </#if>
                                                        </span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="当前价格" class="table-td-none">
                                                    <#if apiFinance.apiTypeConsumeList??>
                                                        <#list apiFinance.apiTypeConsumeList as apiType>
                                                            <span>
                                                                <#if apiType.cost??>
                                                                ${(apiType.cost)/100.0}
                                                                <#else >
                                                                    0
                                                                </#if>
                                                                        </span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="消费总额" class="table-td-none">
                                                    <#if apiFinance.apiTypeConsumeList??>
                                                        <#list apiFinance.apiTypeConsumeList as apiType>
                                                            <span>
                                                                <#if apiType.apiTypeConsumeTotleAmount??>
                                                                ${(apiType.apiTypeConsumeTotleAmount)/100.0}
                                                                <#else >
                                                                    0
                                                                </#if>
                                                                        </span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="调用次数" class="table-td-none">
                                                    <#if apiFinance.apiTypeConsumeList??>
                                                        <#list apiFinance.apiTypeConsumeList as apiType>
                                                            <span>
                                                            ${(apiType.apiTypeUsageAmount)!'0'}
                                                                        </span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="扣费次数" class="table-td-none">
                                                    <#if apiFinance.apiTypeConsumeList??>
                                                        <#list apiFinance.apiTypeConsumeList as apiType>
                                                            <span>
                                                            ${(apiType.apiTypefeeAmount)!'0'}
                                                                        </span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>
                            </div>

                        </div>

                    </div>

                    <div id="form_modal4" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true">

                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                            <h3 id="myModalLabel4">请填写信息</h3>

                        </div>

                        <div class="modal-body">

                            <form action="#" class="form-horizontal">

                                <div class="control-group"></div>

                                <div class="control-group"></div>

                                <div id="error-alert"></div>

                                <div id="apiId-controls" class="controls" style="display: none;"></div>

                                <div class="control-group">

                                    <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                    <div id="amount-controls" class="controls"></div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">充值日期<span class="required">*</span></label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input id="chargeDate" name="chargeDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                    <div id="remark-controls" class="controls"></div>

                                </div>

                            </form>

                        </div>

                        <div class="modal-footer">

                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                            <button class="btn black btn-primary" id="btn-black-btn-primary" type="button">提交</button>

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

    <script src="/js/myjs/api-vendor.js"></script>

    <script src="/js/myjs/api-vendor-two.js"></script>

    <script src="/js/oldlocal/api-vendor-Record.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            ApiVendorRecord.init();
        });

    </script>

    </#if>

</@layout>
