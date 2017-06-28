
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

                    <form action="/cache/find-all-customer" class="form-bottom find_all_customer" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">请输入公司名称</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if companyName??>value="${companyName}" </#if> type="text" id="companyName" name="companyName" placeholder="请输入公司名称">

                                    </div>

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

                        <div class="portlet-body">

                            <div class="table-responsive">

                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                    <thead>
                                    <tr>
                                        <th>公司名称</th>
                                        <th>合作公司</th>
                                        <th>调用缓存总数（${beginDate!'开通后'}--${endDate!'至今'})</th>
                                        <th>${currYear!''}年${currMonth!''}月调用缓存次数</th>
                                        <th>${currYear!''}年${currMonth!''}月${currDay!''}日调用缓存次数</th>
                                        <th class="table-td-none">产品类型</th>
                                        <th class="table-td-none">调用缓存次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if customerCacheConsumeList??>
                                        <#list customerCacheConsumeList as customerCacheConsume>
                                        <tr>
                                            <td data-title="公司名称">${customerCacheConsume.companyName}</td>
                                            <td data-title="合作公司"><#if customerCacheConsume.partnerId??><a href="/cache/find-all-customer?partnerId=${customerCacheConsume.partnerId}">${customerCacheConsume.partnerName}</a><#else >无</#if></td>
                                            <td data-title="调用缓存总数"><#if customerCacheConsume.cacheCount??>${customerCacheConsume.cacheCount?c}<#else >0</#if></td>
                                            <td data-title="本月调用缓存次数"><#if customerCacheConsume.currMonthCacheCount??>${customerCacheConsume.currMonthCacheCount?c}<#else >0</#if></td>
                                            <td data-title="当天调用缓存次数"><a href="#form_modal_customer_curr_day_api_type_consume" data-toggle="modal" onclick="currDayCacheApiTypeConsume(${customerCacheConsume.customerId})" data-toggle="tooltip" data-placement="bottom" title="点击查看当天缓存调用情况"><#if customerCacheConsume.currDayCacheCount??>${customerCacheConsume.currDayCacheCount?c}<#else >0</#if></a></td>
                                            <td data-title="产品类型" class="table-td-none">
                                                <#if customerCacheConsume.customerCacheApiTypeConsumeList ??>
                                                    <#list customerCacheConsume.customerCacheApiTypeConsumeList as customerCacheApiTypeConsume>
                                                        <span>${customerCacheApiTypeConsume.apiTypeName_stidName!''}</span>
                                                        </br>
                                                    </#list>
                                                </#if>
                                            </td>
                                            <td data-title="调用缓存次数" class="table-td-none">
                                                <#if customerCacheConsume.customerCacheApiTypeConsumeList ??>
                                                    <#list customerCacheConsume.customerCacheApiTypeConsumeList as customerCacheApiTypeConsume>
                                                        <span><#if customerCacheApiTypeConsume.cacheCount??>${customerCacheApiTypeConsume.cacheCount?c}<#else >0</#if></span>
                                                        </br>
                                                    </#list>
                                                </#if>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>

                                    </tbody>

                                </table>

                            </div>

                        <#--客户当天产品类型消费弹框-->
                            <div id="form_modal_customer_curr_day_api_type_consume" class="modal hide fade myModalCurrDayApiTypeConsume" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_customer_curr_day_api_type_consume" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 id="myModalLabel_customer_curr_day_api_type_consume"><span id="customer_company_name"></span>当天各产品调用缓存情况</h4>
                                </div>

                                <div class="modal-body">

                                    <table class="table table-striped table-hover table-bordered table-condensed" id="simple_customer_curr_day_api_type_consume">
                                        <thead>
                                        <tr>
                                            <th>产品类型</th>
                                            <th>调用缓存次数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/locales/dataTables-sort-plungin.js"></script>

    <script src="/js/sweetalert/sweetalert2.min.js"></script>

    <script src="/js/sweetalert/core.js"></script>

    <script src="/js/myjs/customer-cache-finance-account.js"></script>

    <script src="/js/myjs/customer-cache-left-bar.js"></script>

    <script src="/js/oldlocal/customer-finance-curr-day-cache-api-type-consume.js"></script>

    <script>
        jQuery(document).ready(function () {

            CustomerCacheFinanceAccount.init();
            CustomerCacheLeftBar.init();

        });
    </script>

    </#if>

</@layout>
