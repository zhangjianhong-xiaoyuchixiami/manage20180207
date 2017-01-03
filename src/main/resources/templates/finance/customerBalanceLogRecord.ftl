
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                    <#--客户信息-->

                    </h3>

                    <#--<ul class="breadcrumb">-->

                        <#--<li>-->

                            <#--<i class="icon-home"></i>-->

                            <#--<a href="/view/successUrl">首页</a>-->

                            <#--<i class="icon-angle-right"></i>-->

                        <#--</li>-->

                        <#--<li>-->

                            <#--<a href="#">财务管理</a>-->

                            <#--<i class="icon-angle-right"></i>-->

                        <#--</li>-->

                        <#--<li><a href="#">财务报表</a></li>-->

                    <#--</ul>-->

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->
                    <#if CustomerBalanceModifyReason=="扣费">
                        <form action="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}" method="get">

                            <div class="clearfix margin-bottom-20">


                                <div class="control-group pull-left">

                                    <label class="control-label">消费理由</label>

                                    <div class="controls">



                                        <label class="checkbox">

                                            <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-2">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-2">弥补扣费

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-1">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-1">消费扣费

                                        </label>



                                    </div>

                                </div>

                                <div class="control-group pull-left margin-right-20">

                                    <label class="control-label">起始日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">

                                            <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">结束日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="input-append" >

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </form>
                    </#if>
                    <#if CustomerBalanceModifyReason=="充值">
                        <form action="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customerId}" method="get">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left">

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

                                <div class="control-group pull-left margin-right-20">

                                    <label class="control-label">起始日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">

                                            <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">结束日期</label>

                                    <div class="controls">

                                        <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;</label>

                                    <div class="input-append" >

                                        <button class="btn black" type="submit">搜索</button>

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

                            <div class="control-group pull-left">

                                <label class="control-label">金额总计&yen;：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0元</span></#if></label>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th class="sorting" style="text-align: center; width: 30%">金额（单位：元）</th>
                                    <th class="sorting" style="text-align: center; width: 30%">时间</th>
                                    <th style="text-align: center; width: 40%">理由</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerBalanceLogList??>
                                        <#list customerBalanceLogList as customerBalanceLog>
                                        <tr class="odd gradeX">
                                            <td style="text-align: center;">${customerBalanceLog.amount/100.0}</td>
                                            <td style="text-align: center;">${customerBalanceLog.createTime}</td>
                                            <td style="text-align: center;">${customerBalanceLog.customerBalanceModifyReason.name}</td>
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
