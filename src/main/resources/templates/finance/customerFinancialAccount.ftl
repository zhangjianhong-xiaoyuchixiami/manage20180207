
<#include "../customer/layout.ftl">

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

                    <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                        <label class="control-label">合作公司Id</label>

                        <div class="controls">

                            <input type="text" id="partnerId" name="partnerId" <#if partnerId??>value="${partnerId?c}"</#if> class="m-wrap medium">

                        </div>
                    </div>

                    <#if deptIdList??>

                        <form action="/finance/find-all-customer-by-dept-id" method="get">

                            <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

                                <div class="control-group pull-left" style="margin-bottom: -20px;margin-top: -25px;">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>

                    <#else >
                        <form action="/finance/find-all-customer" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -20px;margin-top: -25px;">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                            <button class="btn black" type="submit">搜索</button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </form>
                    </#if>

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></div>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <@d.tools idName="exportExcel"></@d.tools>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                <@d.tools idName="exportExcelByDeptId"></@d.tools>
                            </@shiro.hasPermission>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                        <label><input type="checkbox" checked data-column="1">公司名称</label>

                                        <label><input type="checkbox" checked data-column="2">合作公司</label>

                                        <label><input type="checkbox" checked data-column="3">余额</label>

                                        <label><input type="checkbox" checked data-column="4">充值总额</label>

                                        <label><input type="checkbox" checked data-column="5">消费总额</label>

                                        <label><input type="checkbox" data-column="6">上周充值</label>

                                        <label><input type="checkbox" data-column="7">上周消费</label>

                                        <label><input type="checkbox" checked data-column="8">上月充值</label>

                                        <label><input type="checkbox" checked data-column="9">上月消费</label>
                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">

                                <thead>
                                <tr>
                                    <th style="text-align: center;">公司名称</th>
                                    <th style="text-align: center;">合作公司</th>
                                    <th>余额（单位：元）</th>
                                    <th>充值总额（单位：元）</th>
                                    <th>消费总额（单位：元）</th>
                                    <th>上周充值（单位：元）</th>
                                    <th>上周消费（单位：元）</th>
                                    <th>上月充值（单位：元）</th>
                                    <th>上月消费（单位：元）</th>
                                    <th>产品类型</th>
                                    <th>产品价格</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerFinanceList??>
                                        <#list customerFinanceList as customer>
                                        <tr class="odd gradeX">
                                            <td data-title="公司名称">${customer.companyName}</td>
                                            <@shiro.hasPermission name="customer:findAllCustomer">
                                                <td data-title="合作公司"><a href="/finance/find-all-customer<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</a></td>
                                            </@shiro.hasPermission>
                                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                <td data-title="合作公司"><a href="/finance/find-all-customer-by-dept-id<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</td>
                                            </@shiro.hasPermission>
                                            <td data-title="账号余额"><#if customer.balance??>${(customer.balance/100.0)?c}<#else >0</#if></td>
                                            <td data-title="充值总额"><a href="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?customerId=${customer.id?c}&reasonId=1&companyName=${customer.companyName}"><#if customer.chargeTotleAmount??>${(customer.chargeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="消费总额"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}"><#if customer.consumeTotleAmount??>${(-customer.consumeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="上周充值"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}&typeId=1"><#if customer.chargeWeekTotleAmount??>${(customer.chargeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="上周消费"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}&typeId=2"><#if customer.consumeWeekTotleAmount??>${(-customer.consumeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="上月充值"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}&typeId=1"><#if customer.chargeMonthTotleAmount??>${(customer.chargeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td data-title="上月消费"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id?c}&companyName=${customer.companyName}&typeId=2"><#if customer.consumeMonthTotleAmount??>${(-customer.consumeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                            <td>
                                                <#if customer.companyApiList??>
                                                    <#list customer.companyApiList as companyApi>
                                                        <#if companyApi.apiType??>${companyApi.apiType.name!''}<#if companyApi.mobileOperator??>——${companyApi.mobileOperator.name!''}</#if></#if><br/>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td>
                                                <#if customer.companyApiList??>
                                                    <#list customer.companyApiList as companyApi>
                                                        <#if companyApi.price??>${(companyApi.price/100.0)!''}</#if><br/>
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

    <script>
        jQuery(document).ready(function() {
            TableManaged.init();
        });

        <#--导出Excel-->
        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                var partnerId = $('#partnerId').val();
                fetch('/excel-finance/find-all-customer?content='+companyName+'&partnerId='+partnerId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = '客户财务报表.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });

            $('#exportExcelByDeptId').on('click', function () {
                var companyName = $('#companyName').val();
                var partnerId = $('#partnerId').val();
                var username = $('#username').text();
                fetch('/excel-finance/find-all-customer-by-dept-id?content='+companyName+'&username='+username+'&partnerId='+partnerId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = '客户财务报表.xls';
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
