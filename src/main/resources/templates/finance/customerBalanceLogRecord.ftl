
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <#if CustomerBalanceModifyReason=="充值">
                        <form action="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customerId}" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -20px;">

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

                                <div class="control-group pull-left margin-right-20" style="margin-bottom: -20px;">

                                    <label class="control-label">起始日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">

                                            <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -20px;">

                                    <label class="control-label">结束日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -20px;">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="controls" >

                                        <div class="input-append">

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

                            <div class="caption"><i class="icon-cogs"></i>北风分期分期付</div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">金额总计&yen;：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_6">
                                <thead>
                                <tr>
                                    <th style="width: 30%">金额（单位：元）</th>
                                    <th style="width: 40%">时间</th>
                                    <th style="width: 30%">理由</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerBalanceLogList??>
                                        <#list customerBalanceLogList as customerBalanceLog>
                                        <tr>
                                            <td>${customerBalanceLog.amount/100.0}</td>
                                            <td>${customerBalanceLog.createTime}</td>
                                            <td>${customerBalanceLog.customerBalanceModifyReason.name}</td>
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

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>
    <script>
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#customerListBalanceLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');
        });
    </script>
    </#if>

</@layout>
