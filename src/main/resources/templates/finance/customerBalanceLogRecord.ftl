
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

                <#--搜索框-->
                    <form action="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id" class="form-bottom customer_charge" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" <#if customerId??>value="${customerId?c}"</#if> class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" value="${companyName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">充值理由</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="1">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="1">正常充值

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="2">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="2">弥补充值

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="3">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="3">测试充值

                                    </label>

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

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i>${companyName!''}</div>

                            <@d.tools idName="exportExcel" hrefName="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?export=true"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">金额总计&yen;：<span>${(totleAmount/100.0)!'0.0'}元</span></label>

                                </div>

                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed" id="sample_6">
                                    <thead>
                                    <tr>
                                        <th>金额（单位：元）</th>
                                        <th>时间</th>
                                        <th>理由</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if customerBalanceLogList??>
                                            <#list customerBalanceLogList as customerBalanceLog>
                                            <tr>
                                                <td data-title="金额（单位：元）">${(customerBalanceLog.amount/100.0)?c}</td>
                                                <td data-title="时间">${customerBalanceLog.createTime}</td>
                                                <td data-title="理由">${customerBalanceLog.customerBalanceModifyReason.name}</td>
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

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/myjs/customer-charge.js"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script src="/js/oldlocal/customer-balance-log-record.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            CustomerCharge.init();
            CustomerLeftBar.init();
        });

    </script>

    </#if>

</@layout>
