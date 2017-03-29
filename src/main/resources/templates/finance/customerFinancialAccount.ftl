
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

                        <form action="/finance/find-all-customer-by-dept-id" class="form-bottom find_part_customer" method="get">

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

                    </@shiro.hasPermission>

                    <@shiro.hasPermission name="customer:findAllCustomer">

                        <form action="/finance/find-all-customer" class="form-bottom find_all_customer" method="get">

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

                    </@shiro.hasPermission>

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">状态正常</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">被禁用</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                            <#--状态正常-->
                                <div class="portlet box grey">

                                    <div class="portlet-title">

                                        <div class="caption"></div>

                                        <@shiro.hasPermission name="customer:findAllCustomer">
                                            <@d.tools idName="exportExcel" hrefName="/finance/find-all-customer?export=true"></@d.tools>
                                        </@shiro.hasPermission>

                                        <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                            <@d.tools idName="exportExcelByDeptId" hrefName="/finance/find-all-customer-by-dept-id?export=true"></@d.tools>
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

                                    <div class="portlet-body">

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 115px;">总统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${-allWeekConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周充值总额&yen;：${allWeekChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${-allMonthConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月充值总额&yen;：${allMonthChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${-allTotleConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">充值总额&yen;：${allTotleChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 115px;">状态正常统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${-weekConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周充值总额&yen;：${weekChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${-monthConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月充值总额&yen;：${monthChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${-totleConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">充值总额&yen;：${totleChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

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
                                                    <th>剩余信用额度（单位：元）</th>
                                                    <th>余额（单位：元）</th>
                                                    <th>充值总额（单位：元）</th>
                                                    <th>消费总额（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周充值（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                                    <th>${year!''}年${month!''}月充值（单位：元）</th>
                                                    <th>${year!''}年${month!''}月消费（单位：元）</th>
                                                    <th class="table-td-none">产品类型</th>
                                                    <th class="table-td-none">产品价格</th>
                                                    <th class="table-td-none">总消费额</th>
                                                    <th class="table-td-none">请求次数</th>
                                                    <th class="table-td-none">成功次数</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if customerFinanceList??>
                                                        <#list customerFinanceList as customer>
                                                        <tr>
                                                            <td data-title="公司名称">${customer.companyName}</td>
                                                            <@shiro.hasPermission name="customer:findAllCustomer">
                                                                <td data-title="合作公司"><a href="/finance/find-all-customer<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!'无'}</a></td>
                                                            </@shiro.hasPermission>
                                                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                                <td data-title="合作公司" style="display: none"><a href="/finance/find-all-customer-by-dept-id<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</td>
                                                            </@shiro.hasPermission>
                                                            <td data-title="信用额度">${(-customer.floor/100.0)?c}</td>
                                                            <td data-title="剩余信用额度">${(customer.surplusFloor/100.0)?c}</td>
                                                            <td data-title="账号余额"><#if customer.balance??>${(customer.balance/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="充值总额"><a href="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?customerId=${customer.id}&reasonId=1&companyName=${customer.companyName}"><#if customer.chargeTotleAmount??>${(customer.chargeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="消费总额"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id?customerId=${customer.id}&companyName=${customer.companyName}"><#if customer.consumeTotleAmount??>${(-customer.consumeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上周充值"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id}&typeId=1&companyName=${customer.companyName}"><#if customer.chargeWeekTotleAmount??>${(customer.chargeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上周消费"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id}&typeId=2&companyName=${customer.companyName}"><#if customer.consumeWeekTotleAmount??>${(-customer.consumeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上月充值"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id}&typeId=1&companyName=${customer.companyName}"><#if customer.chargeMonthTotleAmount??>${(customer.chargeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上月消费"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id}&typeId=2&companyName=${customer.companyName}"><#if customer.consumeMonthTotleAmount??>${(-customer.consumeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="产品类型" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.enabled==0>
                                                                        <span class="font-text-decoration">
                                                                        <#else >
                                                                        <span>
                                                                        </#if>
                                                                        <#if companyApi.apiType??>${companyApi.apiType.name!''}<#if companyApi.mobileOperator??>--${companyApi.mobileOperator.name!''}</#if></#if></span><br/>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品价格" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                    ${(companyApi.price/100.0)!''}<br/>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="总消费额" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.companyApiCount??>
                                                                            <#if companyApi.subTypeId?? && !(companyApi.companyApiCount.stidSumAmount??)>
                                                                                0</br>
                                                                            <#else >
                                                                            ${(-companyApi.companyApiCount.sumAmount/100.0)!'0'}<br/>
                                                                            </#if>
                                                                        <#else >
                                                                            0</br>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="请求次数" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.companyApiCount??>
                                                                            <#if companyApi.subTypeId?? && !(companyApi.companyApiCount.stidSumAmount??)>
                                                                                0</br>
                                                                            <#else >
                                                                            ${(companyApi.companyApiCount.countTotle)!'0'}<br/>
                                                                            </#if>
                                                                        <#else >
                                                                            0</br>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="成功次数" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.companyApiCount??>
                                                                            <#if companyApi.subTypeId?? && !(companyApi.companyApiCount.stidSumAmount??)>
                                                                                0</br>
                                                                            <#else >
                                                                            ${(companyApi.companyApiCount.countSuccess)!'0'}<br/>
                                                                            </#if>
                                                                        <#else >
                                                                            0</br>
                                                                        </#if>
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

                            <div class="tab-pane " id="tab_2">

                            <#--被禁用-->
                                <div class="portlet box grey">

                                    <div class="portlet-title">

                                        <div class="caption"></div>

                                        <@shiro.hasPermission name="customer:findAllCustomer">
                                            <@d.tools idName="exportExcel" hrefName="/finance/find-all-customer?export=true&dead=true"></@d.tools>
                                        </@shiro.hasPermission>

                                        <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                            <@d.tools idName="exportExcelByDeptId" hrefName="/finance/find-all-customer-by-dept-id?export=true&dead=true"></@d.tools>
                                        </@shiro.hasPermission>

                                        <div class="actions">

                                            <div class="btn-group">

                                                <a class="btn" href="#" data-toggle="dropdown">

                                                    表格显示列

                                                    <i class="icon-angle-down"></i>

                                                </a>

                                                <div id="sample_3_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
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

                                    <div class="portlet-body">

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 115px;">总统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${-allWeekConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周充值总额&yen;：${allWeekChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${-allMonthConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月充值总额&yen;：${allMonthChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${-allTotleConsumeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">充值总额&yen;：${allTotleChargeAmount/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>
                                        </div>

                                        <div class="clearfix margin-bottom-5">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label" style="width: 115px;">被禁用统计：</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周消费总额&yen;：${weekConsumeAmountDead/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">周充值总额&yen;：${weekChargeAmountDead/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月消费总额&yen;：${-monthConsumeAmountDead/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">月充值总额&yen;：${monthChargeAmountDead/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">消费总额&yen;：${-totleConsumeAmountDead/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">充值总额&yen;：${totleChargeAmountDead/100.0}元&nbsp;&nbsp;&nbsp;</label>

                                            </div>

                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_3">

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
                                                    <th>剩余信用额度（单位：元）</th>
                                                    <th>余额（单位：元）</th>
                                                    <th>充值总额（单位：元）</th>
                                                    <th>消费总额（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周充值（单位：元）</th>
                                                    <th>${year!''}年${month!''}月第${week!''}周消费（单位：元）</th>
                                                    <th>${year!''}年${month!''}月充值（单位：元）</th>
                                                    <th>${year!''}年${month!''}月消费（单位：元）</th>
                                                    <th class="table-td-none">产品类型</th>
                                                    <th class="table-td-none">产品价格</th>
                                                    <th class="table-td-none">总消费额</th>
                                                    <th class="table-td-none">请求次数</th>
                                                    <th class="table-td-none">成功次数</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if customerFinanceListDead??>
                                                        <#list customerFinanceListDead as customer>
                                                        <tr>
                                                            <td data-title="公司名称" class="font-text-decoration">${customer.companyName}</td>
                                                            <@shiro.hasPermission name="customer:findAllCustomer">
                                                                <td data-title="合作公司"><a href="/finance/find-all-customer<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!'无'}</a></td>
                                                            </@shiro.hasPermission>
                                                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                                <td data-title="合作公司" style="display: none"><a href="/finance/find-all-customer-by-dept-id<#if customer.partnerId??>?partnerId=${customer.partnerId?c}</#if>">${customer.partnerName!''}</td>
                                                            </@shiro.hasPermission>
                                                            <td data-title="信用额度">${(-customer.floor/100.0)?c}</td>
                                                            <td data-title="剩余信用额度">${(customer.surplusFloor/100.0)?c}</td>
                                                            <td data-title="账号余额"><#if customer.balance??>${(customer.balance/100.0)?c}<#else >0</#if></td>
                                                            <td data-title="充值总额"><a href="/finance/find-all-customer/find-all-customer-recharge-log-by-customer-id?customerId=${customer.id}&reasonId=1&companyName=${customer.companyName}"><#if customer.chargeTotleAmount??>${(customer.chargeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="消费总额"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id?customerId=${customer.id}&companyName=${customer.companyName}"><#if customer.consumeTotleAmount??>${(-customer.consumeTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上周充值"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id}&typeId=1&companyName=${customer.companyName}"><#if customer.chargeWeekTotleAmount??>${(customer.chargeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上周消费"><a href="/finance/find-all-customer/find-week-record-by-customer-id?customerId=${customer.id}&typeId=2&companyName=${customer.companyName}"><#if customer.consumeWeekTotleAmount??>${(-customer.consumeWeekTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上月充值"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id}&typeId=1&companyName=${customer.companyName}"><#if customer.chargeMonthTotleAmount??>${(customer.chargeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="上月消费"><a href="/finance/find-all-customer/find-month-record-by-customer-id?customerId=${customer.id}&typeId=2&companyName=${customer.companyName}"><#if customer.consumeMonthTotleAmount??>${(-customer.consumeMonthTotleAmount/100.0)?c}<#else >0</#if></a></td>
                                                            <td data-title="产品类型" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.enabled==0>
                                                                        <span class="font-text-decoration">
                                                                        <#else >
                                                                        <span>
                                                                        </#if>
                                                                        <#if companyApi.apiType??>${companyApi.apiType.name!''}<#if companyApi.mobileOperator??>--${companyApi.mobileOperator.name!''}</#if></#if></span><br/>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="产品价格" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                    ${(companyApi.price/100.0)!''}<br/>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="总消费额" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.companyApiCount??>
                                                                            <#if companyApi.subTypeId?? && !(companyApi.companyApiCount.stidSumAmount??)>
                                                                                0</br>
                                                                            <#else >
                                                                            ${(-companyApi.companyApiCount.sumAmount/100.0)!'0'}<br/>
                                                                            </#if>
                                                                        <#else >
                                                                            0</br>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="请求次数" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.companyApiCount??>
                                                                            <#if companyApi.subTypeId?? && !(companyApi.companyApiCount.stidSumAmount??)>
                                                                                0</br>
                                                                            <#else >
                                                                            ${(companyApi.companyApiCount.countTotle)!'0'}<br/>
                                                                            </#if>
                                                                        <#else >
                                                                            0</br>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </td>
                                                            <td data-title="成功次数" class="table-td-none">
                                                                <#if customer.companyApiList??>
                                                                    <#list customer.companyApiList as companyApi>
                                                                        <#if companyApi.companyApiCount??>
                                                                            <#if companyApi.subTypeId?? && !(companyApi.companyApiCount.stidSumAmount??)>
                                                                                0</br>
                                                                            <#else >
                                                                            ${(companyApi.companyApiCount.countSuccess)!'0'}<br/>
                                                                            </#if>
                                                                        <#else >
                                                                            0</br>
                                                                        </#if>
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

        <script src="/js/myjs/customer-finance-account-forbid.js"></script>

        <script src="/js/oldlocal/customer-finance-account.js"></script>

        <script src="/js/locales/dataTables-sort-plungin.js"></script>

        <script>
            jQuery(document).ready(function() {
                CustomerFinanceAccount.init();
                CustomerFinanceAccountForbid.init();
                CustomerLeftBar.init();
            });

        </script>

    </#if>

</@layout>
