
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

                    <div class="pull-left head-search-bottom head-search-display">

                        <label class="control-label">合作公司Id</label>

                        <div class="controls">

                            <input type="text" id="partnerId" name="partnerId" <#if partnerId??>value="${partnerId?c}"</#if> class="m-wrap medium">

                        </div>
                    </div>

                    <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">

                        <form action="/finance/find-all-customer-by-dept-id" class="find_part_customer" method="get">

                            <div class="clearfix margin-bottom-20 head-search-clearfix-top">

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

                                    <label class="control-label">请输入公司名称</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

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

                            </div>

                        </form>

                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="customer:findAllCustomer">

                        <form action="/finance/find-all-customer" class="find_all_customer" method="get">

                            <div class="clearfix margin-bottom-20 head-search-clearfix-top">

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

                                    <label class="control-label">请输入公司名称</label>

                                    <div class="controls">

                                        <div class="input-append">

                                            <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

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

                            </div>

                        </form>

                    </@shiro.hasPermission>

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

                                        <label><input type="checkbox" checked data-column="3">信用额度</label>

                                        <label><input type="checkbox" checked data-column="4">余额</label>

                                        <label><input type="checkbox" checked data-column="5">充值总额</label>

                                        <label><input type="checkbox" checked data-column="6">消费总额</label>

                                        <label><input type="checkbox" data-column="7">上周充值</label>

                                        <label><input type="checkbox" data-column="8">上周消费</label>

                                        <label><input type="checkbox" checked data-column="9">上月充值</label>

                                        <label><input type="checkbox" checked data-column="10">上月消费</label>
                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">

                                    <thead>
                                    <tr>
                                        <th>公司名称</th>
                                        <@shiro.hasPermission name="customer:findAllCustomer">
                                            <th>合作公司</th>
                                        </@shiro.hasPermission>
                                        <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                            <th style="display: none">合作公司</th>
                                        </@shiro.hasPermission>
                                        <th>信用额度（单位：元）</th>
                                        <th>余额（单位：元）</th>
                                        <th>充值总额（单位：元）</th>
                                        <th>消费总额（单位：元）</th>
                                        <th>${year!''}年${month!''}月第${week!''}周充值（单位：元）</th>
                                        <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                        <th>${year!''}年${month!''}月充值（单位：元）</th>
                                        <th>${year!''}年${month!''}月消费（单位：元）</th>
                                        <th>产品类型</th>
                                        <th>产品价格</th>
                                        <th>总消费额</th>
                                        <th>请求次数</th>
                                        <th>成功次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if customerFinanceList??>
                                            <#list customerFinanceList as customer>
                                            <tr>
                                                <td data-title="公司名称">${customer.companyName}</td>
                                                <@shiro.hasPermission name="customer:findAllCustomer">
                                                    <td data-title="合作公司"><a href="/finance/find-all-customer<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</a></td>
                                                </@shiro.hasPermission>
                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                    <td data-title="合作公司" style="display: none"><a href="/finance/find-all-customer-by-dept-id<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</td>
                                                </@shiro.hasPermission>
                                                <td data-title="信用额度">${(-customer.floor/100.0)?c}</td>
                                                <td data-title="账号余额"><#if customer.balance??>${(customer.balance/100.0)?c}<#else >0</#if></td>
                                                <td data-title="充值总额"><a href="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?customerId=${customer.id}&reasonId=1&companyName=${customer.companyName}"><#if customer.chargeTotleAmount??>${(customer.chargeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="消费总额"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id?customerId=${customer.id}&companyName=${customer.companyName}"><#if customer.consumeTotleAmount??>${(-customer.consumeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="上周充值"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id}&typeId=1&companyName=${customer.companyName}"><#if customer.chargeWeekTotleAmount??>${(customer.chargeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="上周消费"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id}&typeId=2&companyName=${customer.companyName}"><#if customer.consumeWeekTotleAmount??>${(-customer.consumeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="上月充值"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id}&typeId=1&companyName=${customer.companyName}"><#if customer.chargeMonthTotleAmount??>${(customer.chargeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="上月消费"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id}&typeId=2&companyName=${customer.companyName}"><#if customer.consumeMonthTotleAmount??>${(-customer.consumeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                <td data-title="产品类型">
                                                    <#if customer.companyApiList??>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.apiType??>${companyApi.apiType.name!''}<#if companyApi.mobileOperator??>--${companyApi.mobileOperator.name!''}</#if></#if><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="产品价格">
                                                    <#if customer.companyApiList??>
                                                        <#list customer.companyApiList as companyApi>
                                                            <#if companyApi.price??>${(companyApi.price/100.0)!''}</#if><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="总消费额">
                                                    <#if customer.companyApiList??>
                                                        <#list customer.companyApiList as companyApi>
                                                            ${(-companyApi.companyApiCount.sumApiTypeIdAmount/100.0)!'0'}<br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="请求次数">
                                                    <#if customer.companyApiList??>
                                                        <#list customer.companyApiList as companyApi>
                                                            ${(companyApi.companyApiCount.countTotle)!'0'}<br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="成功次数">
                                                    <#if customer.companyApiList??>
                                                        <#list customer.companyApiList as companyApi>
                                                            ${(companyApi.companyApiCount.countSuccess)!'0'}<br/>
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

        <script src="https://code.highcharts.com/highcharts.js"></script>

        <script src="https://code.highcharts.com/modules/exporting.js"></script>

        <script src="/js/myjs/customerleftbar.js"></script>

        <script src="/js/myjs/customer-finance-account.js"></script>

        <script src="/js/locales/dataTables-sort-plungin.js"></script>

        <script>
            jQuery(document).ready(function() {
                CustomerFinanceAccount.init();
                CustomerLeftBar.init();

                $('.find_all_customer').change(function () {
                    $(this).submit();
                });

                $('.find_part_customer').change(function () {
                    $(this).submit();
                });

            });
        </script>

        <script>

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
