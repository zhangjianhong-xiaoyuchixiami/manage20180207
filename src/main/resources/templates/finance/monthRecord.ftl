
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

                    <form action="/finance/find-all-customer/find-month-record-by-customer-id" method="get">

                        <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" value="${customerId}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" value="${companyName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">类型</label>

                                <div class="controls">

                                    <input type="text" id="typeId" name="typeId" value="${typeId}" class="m-wrap medium">

                                </div>
                            </div>

                        <#--<div class="control-group pull-left" style="margin-bottom: -20px;">-->

                        <#--<label class="control-label">类型</label>-->

                        <#--<div class="controls">-->

                        <#--<label class="checkbox">-->

                        <#--<input type="checkbox" <#if typeIdArray??><#list typeIdArray as typeId><#if typeId==1>checked="checked"</#if></#list></#if> id="typeId" name="typeId" value="1">充值-->

                        <#--</label>-->

                        <#--<label class="checkbox">-->

                        <#--<input type="checkbox" <#if typeIdArray??><#list typeIdArray as typeId><#if typeId==2>checked="checked"</#if></#list></#if> id="typeId" name="typeId" value="2">消费-->

                        <#--</label>-->

                        <#--</div>-->

                        <#--</div>-->

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

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

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

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

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

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

                            <@d.tools idName="exportExcel"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span><#if totleAmount<0>${(-totleAmount/100.0)?c}<#else >${(totleAmount/100.0)?c}</#if>元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="control-group pull-right" style="margin-bottom: -10px;">


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

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_9">
                                <thead>
                                <tr>
                                    <th style="width: 30%;">周期</th>
                                    <th style="width: 20%;">金额（单位/元）</th>
                                    <th style="width: 25%;">开始时间</th>
                                    <th style="width: 25%;">结束时间</th>
                                <#--<th style="width: 20%;">类型</th>-->
                                </tr>
                                </thead>
                                <tbody>
                                    <#if weekMonthAmountList??>
                                        <#list weekMonthAmountList as weekMonthAmount>
                                        <tr class="odd gradeX">
                                            <td data-title="周期"><#if weekMonthAmount.years??>${weekMonthAmount.years?c}年</#if><#if weekMonthAmount.months??>第${weekMonthAmount.months}月</#if></td>
                                            <td data-title="金额（单位/元）"><#if weekMonthAmount.totleAmount??><#if weekMonthAmount.totleAmount<0>${(-weekMonthAmount.totleAmount/100.0)?c}<#else >${(weekMonthAmount.totleAmount/100.0)?c}</#if><#else >0</#if></td>
                                            <td data-title="开始时间"><#if weekMonthAmount.beginTime??>${weekMonthAmount.beginTime?date}</#if></td>
                                            <td data-title="结束时间"><#if weekMonthAmount.endTime??>${weekMonthAmount.endTime?date}</#if></td>
                                        <#--<#if weekMonthAmount.tableId==1>-->
                                        <#--<td>充值</td>-->
                                        <#--<#else >-->
                                        <#--<td>消费</td>-->
                                        <#--</#if>-->
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

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script src="/js/myjs/monthRecord.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            TableManaged.init();

            CustomerLeftBar.init();

        });
    </script>

    <#--导出Excel-->
    <script type="text/javascript">
        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var years = $('#years').val();
                var months = $('#months').val();
                var typeId = $("#typeId").val();
                fetch('/excel-finance/find-all-customer/find-month-record-by-customer-id?companyName='+companyName+'&typeId='+typeId+'&customerId='+customerId+'&years='+years+'&months='+months).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'月历史记录.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });
        });
    </script>

    </#if>

</@layout>
