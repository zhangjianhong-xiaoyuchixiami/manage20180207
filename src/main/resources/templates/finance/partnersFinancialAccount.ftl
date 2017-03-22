
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


                    <form action="/partner/find-all-partner-financial-account" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-top">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if partnerName??>value="${partnerName}" </#if> type="text" name="partnerName" placeholder="请输入合作公司名称">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"></div>

                            <@d.tools idName="exportExcel" hrefName="/partner/find-all-partner-financial-account?export=true"></@d.tools>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_3_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                        <label><input type="checkbox" checked data-column="0">公司名称</label>

                                        <label><input type="checkbox" checked data-column="1">收入总额</label>

                                        <label><input type="checkbox" checked data-column="2">支出总额</label>

                                        <label><input type="checkbox" data-column="3">上周收入</label>

                                        <label><input type="checkbox" data-column="4">上周支出</label>

                                        <label><input type="checkbox" checked data-column="5">上月收入</label>

                                        <label><input type="checkbox" checked data-column="6">上月支出</label>
                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">

                                <div class="btn-group">

                                    <a class="btn black" id="add-partner" href="#form_modal1" data-toggle="modal">

                                        新增<i class="icon-plus"></i>

                                    </a>

                                </div>

                                <div id="form_modal1" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">

                                    <div class="modal-header">

                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                        <h3 id="myModalLabel1">请填写信息</h3>

                                    </div>

                                    <div class="modal-body">

                                        <form action="#" class="form-horizontal">

                                            <div class="control-group"></div>

                                            <div class="control-group"></div>

                                            <div id="error-alert"></div>

                                            <div class="control-group">

                                                <label class="control-label">请输入合作公司名称<span class="required">*</span></label>

                                                <div class="controls">

                                                    <input type="text" id="partnerName" name="partnerName" class="m-wrap medium">

                                                    <span id="partnerNameMsg" class="help-line"></span>


                                                </div>

                                            </div>

                                        </form>

                                    </div>

                                    <div class="modal-footer">

                                        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                        <button class="btn black btn-primary" id="add-btn-black-btn-primary" type="button">提交</button>

                                    </div>

                                </div>

                            </div>

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">周收入总额&yen;：<#if weekIncomeAmount??><span>${(weekIncomeAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">周支出总额&yen;：<#if weekExpenditure??><span>${(weekExpenditure/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">月收入总额&yen;：<#if monthIncomeAmount??><span>${(monthIncomeAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">月支出总额&yen;：<#if monthExpenditure??><span>${(monthExpenditure/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>


                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">收入总额&yen;：<#if totleIncomeAmount??><span>${(totleIncomeAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">支出总额&yen;：<#if totleExpenditure??><span>${(totleExpenditure/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                            <#--支出总额走势-->
                                <div class="pull-right table-top-bottom">

                                    <label class="control-label">

                                        <a id="columnExpenditureHistogram" href="#form_modal7" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>支出总额

                                        </a>

                                    </label>

                                    <div id="form_modal7" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel7">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="columnExpenditureHistogramContainer">

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            <#--收入总额走势-->
                                <div class="pull-right table-top-bottom">

                                    <label class="control-label">

                                        <a id="columnIncomeHistogram" href="#form_modal8" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>收入总额

                                        </a>&nbsp;&nbsp;&nbsp;

                                    </label>

                                    <div id="form_modal8" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel8">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="columnIncomeHistogramContainer">

                                            </div>

                                        </div>

                                    </div>

                                </div>


                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_3">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center;">公司名称</th>
                                        <th>收入总额（单位：元）</th>
                                        <th>支出总额（单位：元）</th>
                                        <th>${year!''}年${month!''}月第${week!''}周收入（单位：元）</th>
                                        <th>${year!''}年${month!''}月第${week!''}周支出（单位：元）</th>
                                        <th>${year!''}年${month!''}月收入（单位：元）</th>
                                        <th>${year!''}年${month!''}月支出（单位：元）</th>
                                        <th style="text-align: center; width: 15%">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if partnerFinanceList??>
                                            <#list partnerFinanceList as partnerFinance>
                                            <tr class="odd gradeX">
                                                <td data-title="公司名称">${partnerFinance.partnerName}</td>
                                                <td data-title="收入总额"><a href="/partner/find-all-partner-financial-account/income-and-expenditure-record?partnerId=${partnerFinance.partnerId?c}&partnerName=${partnerFinance.partnerName}&reasonId=1" data-toggle="tooltip" data-placement="auto" title="点击查看收款记录"><#if partnerFinance.totleIncomeAmount??>${(partnerFinance.totleIncomeAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="支出总额"><a href="/partner/find-all-partner-financial-account/income-and-expenditure-record?partnerId=${partnerFinance.partnerId?c}&partnerName=${partnerFinance.partnerName}&reasonId=2" data-toggle="tooltip" data-placement="auto" title="点击查看付款记录"><#if partnerFinance.totleExpenditureAmount??>${(partnerFinance.totleExpenditureAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="上周收入"><#if partnerFinance.weekIncomeAmount??>${(partnerFinance.weekIncomeAmount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="上周支出"><#if partnerFinance.weekExpenditureAmount??>${(partnerFinance.weekExpenditureAmount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="上月收入"><#if partnerFinance.monthIncomeAmount??>${(partnerFinance.monthIncomeAmount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="上月支出"><#if partnerFinance.monthExpenditureAmount??>${(partnerFinance.monthExpenditureAmount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="操作" style="text-align: center">
                                                    <a href="#form_modal2" onclick="paymentReceipt(${partnerFinance.partnerId?c},2)"  data-toggle="modal">付款</a>|
                                                    <a href="#form_modal2" onclick="paymentReceipt(${partnerFinance.partnerId?c},1)"  data-toggle="modal">收款</a>
                                                </td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>
                            </div>
                            <div id="form_modal2" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">

                                <div class="modal-header">

                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                    <h3 id="myModalLabel2">请填写信息</h3>

                                </div>

                                <div class="modal-body">

                                    <form action="#" class="form-horizontal">

                                        <div class="control-group"></div>

                                        <div class="control-group"></div>

                                        <div id="error-alert"></div>

                                        <div id="partnerId-controls" class="controls" style="display: none;"></div>

                                        <div id="reasonId-controls" class="controls" style="display: none;"></div>

                                        <div class="control-group">

                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                            <div id="amount-controls" class="controls"></div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">日&nbsp;期<span class="required">*</span></label>

                                            <div class="controls">

                                                <div class="input-append date date-picker"  data-date-viewmode="years" data-date-minviewmode="months">

                                                    <input id="date" name="date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

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

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/partnersfinanceaccount.js"></script>

    <script src="/js/oldlocal/partner-finance-account.js"></script>

    <script src="/js/locales/dataTables-sort-plungin.js"></script>

    <script>
        jQuery(document).ready(function() {
            PartnerFinanceAccount.init();

            $(function () { $("[data-toggle='tooltip']").tooltip(); });

        });

    </script>

    </#if>

</@layout>
