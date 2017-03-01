
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

                    <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                        <form action="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail" class="customer_consume_detail" method="get">

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">api类型Id</label>

                                <div class="controls">

                                    <input type="text" id="apiTypeId" name="apiTypeId" value="${apiTypeId}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" value="${customerId?c}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" value="${companyName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">api类型名称</label>

                                <div class="controls">

                                    <input type="text" id="apiTypeName" name="apiTypeName" value="${apiTypeName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">消费理由</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId==-1>checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-1">消费扣费

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId==-2>checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-2">弥补扣费

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


                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </form>

                    </div>

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i><#if companyName??>${companyName}</#if><#if apiTypeName??>@${apiTypeName}</#if></div>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <@d.tools idName="exportExcel"></@d.tools>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                <@d.tools idName="exportExcelByDeptId"></@d.tools>
                            </@shiro.hasPermission>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${(-totleAmount/100.0)?c}元&nbsp;&nbsp;&nbsp;</span><#else ><span>0元&nbsp;&nbsp;&nbsp;</span></#if></label>

                                </div>

                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed" id="sample_7">
                                    <thead>
                                    <tr>
                                        <th style="width: 40%">产品</th>
                                        <th style="width: 20%">消费金额（单位/元）</th>
                                        <th style="width: 20%">创建时间</th>
                                        <th style="width: 20%">类型</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if customerBalanceLogList??>
                                            <#list customerBalanceLogList as customerBalanceLog>
                                            <tr class="odd gradeX">
                                                <td data-title="产品">
                                                    <#if customerBalanceLog.apiType??>${customerBalanceLog.apiType.name!''}</#if><#if customerBalanceLog.mobileOperator??>——${customerBalanceLog.mobileOperator.name!''}</#if>
                                                    <@shiro.hasPermission name="customer:findAllCustomer">
                                                        <#if customerBalanceLog.apiVendor??>@${customerBalanceLog.apiVendor.name!''}</#if>
                                                    </@shiro.hasPermission>
                                                </td>
                                                <td data-title="消费金额（单位/元）"><#if customerBalanceLog.amount??>${(-customerBalanceLog.amount/100.0)?c}<#else >0</#if></td>
                                                <td data-title="创建时间">${customerBalanceLog.createTime!''}</td>
                                                <td data-title="类型"><#if customerBalanceLog.customerBalanceModifyReason??>${customerBalanceLog.customerBalanceModifyReason.name}</#if></td>
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

    <script src="/js/myjs/customer-api-consume-detail.js"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            CustomerApiConsumeDetail.init();
            CustomerLeftBar.init();
        });
    </script>

    <#--导出Excel-->
    <script type="text/javascript">
        $(document).ready(function() {

            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var apiTypeId = $('#apiTypeId').val();
                var apiTypeName = $('#apiTypeName').val();
                var reasonId =[];//定义一个数组
                $('input[name="reasonId"]:checked').each(function(){
                    reasonId.push($.trim($(this).val()));
                });
                fetch('/excel-finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail?companyName='+companyName+'&customerId='+customerId+'&reasonId='+reasonId+'&apiTypeId='+apiTypeId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'@'+apiTypeName+'消费明细记录.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });

            $('#exportExcelByDeptId').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var apiTypeId = $('#apiTypeId').val();
                var apiTypeName = $('#apiTypeName').val();
                var reasonId =[];//定义一个数组
                $('input[name="reasonId"]:checked').each(function(){
                    reasonId.push($.trim($(this).val()));
                });
                fetch('/excel-finance/find-all-customer-by-dept/find-all-customer-api-consume-record-by-customer-id/detail?companyName='+companyName+'&customerId='+customerId+'&reasonId='+reasonId+'&apiTypeId='+apiTypeId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'@'+apiTypeName+'消费明细记录.xls';
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
