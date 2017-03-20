
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/finance/find-all-customer/find-month-record-by-customer-id" class="month_record" method="get">

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

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">类型</label>

                                <div class="controls">

                                    <input type="text" id="typeId" name="typeId" value="${typeId}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">年</label>

                                <div class="controls">

                                    <select id="years" name="years" class="medium m-wrap1" tabindex="1">
                                        <option value="">请选择...</option>
                                        <#if yearList??>
                                            <#list yearList as year>
                                                <option <#if years?? && year==years>selected="selected"</#if> value="${year?c}">${year?c}年</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">月</label>

                                <div class="controls">

                                    <select id="months" name="months" class="medium m-wrap1" tabindex="1">
                                        <option value="">请选择...</option>
                                        <#if monthList??>
                                            <#list monthList as month>
                                                <option <#if months?? && month==months>selected="selected"</#if> value="${month?c}">第${month?c}月</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="input-append" >

                                    <button class="btn black" type="submit">搜索</button>

                                </div>

                            </div>

                        </div>

                    </form>


                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></i><#if companyName??>${companyName}</#if></div>

                            <@d.tools idName="exportExcel" hrefName="/finance/find-all-customer/find-month-record-by-customer-id?export=true"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span><#if totleAmount<0>${(-totleAmount/100.0)?c}<#else >${(totleAmount/100.0)?c}</#if>元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="pull-right table-top-bottom">

                                    <#if typeId?? && typeId==2>
                                        <label class="control-label">

                                            <a id="months-account" href="#form_modalA" data-toggle="modal">

                                                <i class="icon-bar-chart"></i>月消费走势图

                                            </a>

                                        </label>
                                    </#if>

                                    <#if typeId?? && typeId==1>
                                        <label class="control-label">

                                            <a id="months-account" href="#form_modalA" data-toggle="modal">

                                                <i class="icon-bar-chart"></i>月充值走势图

                                            </a>

                                        </label>
                                    </#if>

                                    <div id="form_modalA" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelA" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabelA">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="months-container">

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            </div>
                            <div class="table-responsive">

                                <table class="table table-striped table-hover table-bordered table-condensed" id="sample_9">
                                    <thead>
                                    <tr>
                                        <th>周期</th>
                                        <th>金额（单位/元）</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if weekMonthAmountList??>
                                            <#list weekMonthAmountList as weekMonthAmount>
                                            <tr>
                                                <td data-title="周期"><#if weekMonthAmount.years??>${weekMonthAmount.years?c}年</#if><#if weekMonthAmount.months??>第${weekMonthAmount.months}月</#if></td>
                                                <td data-title="金额（单位/元）"><#if weekMonthAmount.totleAmount??><#if weekMonthAmount.totleAmount<0>${(-weekMonthAmount.totleAmount/100.0)?c}<#else >${(weekMonthAmount.totleAmount/100.0)?c}</#if><#else >0</#if></td>
                                                <td data-title="开始时间"><#if weekMonthAmount.beginTime??>${weekMonthAmount.beginTime?date}</#if></td>
                                                <td data-title="结束时间"><#if weekMonthAmount.endTime??>${weekMonthAmount.endTime?date}</#if></td>
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

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script src="/js/myjs/month-record.js"></script>

    <script src="/js/oldlocal/month-Record.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            MonthRecord.init();

            CustomerLeftBar.init();

        });
    </script>

    </#if>

</@layout>
