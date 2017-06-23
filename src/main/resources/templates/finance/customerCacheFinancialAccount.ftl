
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

                    <form action="/finance/find-all-customer" class="form-bottom find_all_customer" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">公司状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="0">checked="checked"</#if></#list></#if> id="status" name="status" value="0">状态正常

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="-1">checked="checked"</#if></#list></#if> id="status" name="status" value="-1">被禁用

                                    </label>

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
                                        <th>调用总数（${beginDate!'开通后'}--${endDate!'至今'})</th>
                                        <th>${currYear!''}年${currMonth!''}月调用缓存次数</th>
                                        <th>${currYear!''}年${currMonth!''}月${currDay!''}日调用缓存次数</th>
                                        <th class="table-td-none">产品类型</th>
                                        <th class="table-td-none">调用缓存次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td data-title="公司名称">北京千眼数合科技有限公司</td>
                                        <td data-title="合作公司">无</td>
                                        <td data-title="调用总数">2000</td>
                                        <td data-title="本月调用次数">1000</td>
                                        <td data-title="当天调用次数"><a>100</a></td>
                                        <td data-title="产品类型" class="table-td-none">手机三要素--移动</td>
                                        <td data-title="调用缓存次数" class="table-td-none">100</td>
                                    </tr>
                                    </tbody>

                                </table>

                            </div>

                        <#--客户当天产品类型消费弹框-->
                            <div id="form_modal_customer_curr_day_api_type_consume" class="modal hide fade myModalCurrDayApiTypeConsume" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_customer_curr_day_api_type_consume" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 id="myModalLabel_customer_curr_day_api_type_consume"><span id="customer_company_name"></span>当天各产品类型消费情况</h4>
                                </div>

                                <div class="modal-body">

                                    <table class="table table-striped table-hover table-bordered table-condensed" id="simple_customer_curr_day_api_type_consume">
                                        <thead>
                                        <tr>
                                            <th>产品类型</th>
                                            <th>当前价格</th>
                                            <th>总消费额（单位：元）</th>
                                            <th>请求次数</th>
                                            <th>成功次数</th>
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

    <script>
        jQuery(document).ready(function () {

            CustomerCacheFinanceAccount.init();
            CustomerCacheLeftBar.init();

        });
    </script>

    </#if>

</@layout>
