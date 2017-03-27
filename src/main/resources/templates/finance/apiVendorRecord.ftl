
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

                            <li class="active"><a href="#tab_1" data-toggle="tab">正在供应的供应商</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">已停用的供应商</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                            <#--正在供应的供应商-->
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

                                                    <label><input type="checkbox" checked data-column="6">上月消费</label>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="portlet-body">

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 105px;">总统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：<#if allWeekTotleAmount??><span>${(allWeekTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：<#if allMonthTotleAmount??><span>${(allMonthTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：<#if allConsumeTotleAmount??><span>${(allConsumeTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">所剩余额&yen;：<#if allTotleBalance??><span>${(allTotleBalance/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 105px;">正在使用统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：<#if weekTotleAmount??><span>${(weekTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：<#if monthTotleAmount??><span>${(monthTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：<#if consumeTotleAmount??><span>${(consumeTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">所剩余额&yen;：<#if totleBalance??><span>${(totleBalance/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

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
                                                    <th>消费总额（单位：元）</th>
                                                    <th>所剩余额（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                                    <th>${year!''}年${month!''}月消费（单位：元）</th>
                                                    <th class="table-td-none">类型</th>
                                                    <th style="text-align: center; width: 13%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if apiFinanceList??>
                                                        <#list apiFinanceList as apiFinance>
                                                        <tr>
                                                            <td data-title="供应商"><a href="/api/find-all-api-record<#if apiFinance.vendorId??>?vendorId=${apiFinance.vendorId}</#if>">${apiFinance.vendorName}</a></td>
                                                            <td data-title="合作公司"><a href="/api/find-all-api-vendor-consume<#if apiFinance.partnerId??>?partnerId=${apiFinance.partnerId?c}</#if>">${apiFinance.partnerName!'无'}</a></td>
                                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="所剩余额"><#if apiFinance.balance??>${(apiFinance.balance/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="类型" class="table-td-none">
                                                                <#if apiFinance.apiTypeList??>
                                                                    <#list apiFinance.apiTypeList as apiType>
                                                                        <#if apiType.api.status==-1>
                                                                        <span class="font-text-decoration">
                                                                        <#else>
                                                                        <span>
                                                                        </#if>
                                                                    ${apiType.name!''}
                                                                        <#if (apiType.mobileOperatorList?size>0)>--
                                                                            <#list apiType.mobileOperatorList as mobileOperator>
                                                                            ${mobileOperator.name}<#if (apiType.mobileOperatorList?size>1)>,</#if>
                                                                            </#list>
                                                                        </#if>
                                                                    </span><br/>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="操作" style="text-align: center;">
                                                                <a href="#form_modal4" onclick="charge(${apiFinance.vendorId})" data-toggle="modal">充值</a>
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

                            <#--已停用的供应商-->
                                <div class="portlet box grey">

                                    <div class="portlet-title">

                                        <div class="caption"></div>

                                        <@d.tools idName="exportExcel" hrefName="/api/find-all-api-vendor-consume?export=true&dead=true"></@d.tools>

                                        <div class="actions">

                                            <div class="btn-group">

                                                <a class="btn" href="#" data-toggle="dropdown">

                                                    表格显示列

                                                    <i class="icon-angle-down"></i>

                                                </a>

                                                <div id="sample_12_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                                    <label><input type="checkbox" checked data-column="1">供应商</label>

                                                    <label><input type="checkbox" checked data-column="2">合作公司</label>

                                                    <label><input type="checkbox" checked data-column="3">消费总额</label>

                                                    <label><input type="checkbox" checked data-column="4">所剩余额</label>

                                                    <label><input type="checkbox" data-column="5">上周消费</label>

                                                    <label><input type="checkbox" checked data-column="6">上月消费</label>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                    <div class="portlet-body">

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 105px;">总统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：<#if allWeekTotleAmount??><span>${(allWeekTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：<#if allMonthTotleAmount??><span>${(allMonthTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：<#if allConsumeTotleAmount??><span>${(allConsumeTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">所剩余额&yen;：<#if allTotleBalance??><span>${(allTotleBalance/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 105px;">已停用统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：<#if weekTotleAmountDead??><span>${(weekTotleAmountDead/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：<#if monthTotleAmountDead??><span>${(monthTotleAmountDead/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：<#if consumeTotleAmountDead??><span>${(consumeTotleAmountDead/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">所剩余额&yen;：<#if totleBalanceDead??><span>${(totleBalanceDead/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_12">
                                                <thead>
                                                <tr>
                                                    <th>供应商</th>
                                                    <th>合作公司</th>
                                                    <th>消费总额（单位：元）</th>
                                                    <th>所剩余额（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                                    <th>${year!''}年${month!''}月消费（单位：元）</th>
                                                    <th class="table-td-none">类型</th>
                                                    <th style="text-align: center; width: 13%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if apiFinanceListDead??>
                                                        <#list apiFinanceListDead as apiFinance>
                                                        <tr>
                                                            <td data-title="供应商" class="font-text-decoration"><a href="/api/find-all-api-record<#if apiFinance.vendorId??>?vendorId=${apiFinance.vendorId}</#if>">${apiFinance.vendorName}</a></td>
                                                            <td data-title="合作公司"><a href="/api/find-all-api-vendor-consume<#if apiFinance.partnerId??>?partnerId=${apiFinance.partnerId?c}</#if>">${apiFinance.partnerName!'无'}</a></td>
                                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="所剩余额"><#if apiFinance.balance??>${(apiFinance.balance/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="类型" class="table-td-none">
                                                                <#if apiFinance.apiTypeList??>
                                                                    <#list apiFinance.apiTypeList as apiType>
                                                                        <#if apiType.api.status==-1>
                                                                        <span class="font-text-decoration">
                                                                        <#else>
                                                                        <span>
                                                                        </#if>
                                                                    ${apiType.name!''}
                                                                        <#if (apiType.mobileOperatorList?size>0)>--
                                                                            <#list apiType.mobileOperatorList as mobileOperator>
                                                                            ${mobileOperator.name}<#if (apiType.mobileOperatorList?size>1)>,</#if>
                                                                            </#list>
                                                                        </#if>
                                                                    </span><br/>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="操作" style="text-align: center;">
                                                                <a href="#form_modal4" onclick="charge(${apiFinance.vendorId})" data-toggle="modal">充值</a>
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
            ApiVendorRecordTwo.init();
        });

    </script>

    </#if>

</@layout>
