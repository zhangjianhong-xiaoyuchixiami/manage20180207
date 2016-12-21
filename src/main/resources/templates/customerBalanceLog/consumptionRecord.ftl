
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

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="#">财务管理</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">财务报表</a></li>

                    </ul>

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

                                            <input type="checkbox" <#if reasonId1??>checked="checked"</#if> id="reasonId1" name="reasonId1" value="-2">弥补扣费

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if reasonId2??>checked="checked"</#if> id="reasonId2" name="reasonId2" value="-1">消费扣费

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

                                            <input type="checkbox" <#if reasonId3??>checked="checked"</#if> id="reasonId3" name="reasonId3" value="1">正常充值

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if reasonId4??>checked="checked"</#if> id="reasonId4" name="reasonId4" value="2">弥补充值

                                        </label>

                                        <label class="checkbox">

                                            <input type="checkbox" <#if reasonId5??>checked="checked"</#if> id="reasonId5" name="reasonId5" value="2">测试充值

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

                            <div class="caption"><i class="icon-cogs"></i></div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="control-group pull-left">

                                <label class="control-label">本页总计&yen;：<#if lineSizeTotleAmount??><span>${lineSizeTotleAmount}元</span><#else ><span>元0</span></#if></label>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0元</span></#if></label>

                            </div>

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 30%">金额（单位/元）</th>
                                    <th style="text-align: center; width: 30%">时间</th>
                                    <th style="text-align: center; width: 40%">理由</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerBalanceLogList??>
                                        <#list customerBalanceLogList as customerBalanceLog>
                                        <tr>
                                            <td style="text-align: center;">${customerBalanceLog.amount/100}</td>
                                            <td style="text-align: center;">${customerBalanceLog.createTime}</td>
                                            <td style="text-align: center;">${customerBalanceLog.customerBalanceModifyReason.name}</td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>
                            <#if CustomerBalanceModifyReason=="扣费">
                                <#if (count>0)>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>
                                        </div>
                                        <#if (totlePage>0)>
                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=1<#if reasonId1??>&reasonId1=${reasonId1}</#if><#if reasonId2??>&reasonId2=${reasonId2}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=${pageSize-1}<#if reasonId1??>&reasonId1=${reasonId1}</#if><#if reasonId2??>&reasonId2=${reasonId2}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=${pageSize+1}<#if reasonId1??>&reasonId1=${reasonId1}</#if><#if reasonId2??>&reasonId2=${reasonId2}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=${totlePage}<#if reasonId1??>&reasonId1=${reasonId1}</#if><#if reasonId2??>&reasonId2=${reasonId2}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">尾页</span></a></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </#if>
                                    </div>
                                </#if>
                            </#if>
                            <#if CustomerBalanceModifyReason=="充值">
                                <#if (count>0)>
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>
                                        </div>
                                        <#if (totlePage>0)>
                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customerId}?pageSize=1<#if reasonId3??>&reasonId3=${reasonId3}</#if><#if reasonId4??>&reasonId4=${reasonId4}</#if><#if reasonId5??>&reasonId5=${reasonId5}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customerId}?pageSize=${pageSize-1}<#if reasonId3??>&reasonId3=${reasonId3}</#if><#if reasonId4??>&reasonId4=${reasonId4}</#if><#if reasonId5??>&reasonId5=${reasonId5}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customerId}?pageSize=${pageSize+1}<#if reasonId3??>&reasonId3=${reasonId3}</#if><#if reasonId4??>&reasonId4=${reasonId4}</#if><#if reasonId5??>&reasonId5=${reasonId5}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/customerBalance/findAllRechargeCustomerBalanceLogByCustomerId/${customerId}?pageSize=${totlePage}<#if reasonId3??>&reasonId3=${reasonId3}</#if><#if reasonId4??>&reasonId4=${reasonId4}</#if><#if reasonId5??>&reasonId5=${reasonId5}</#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">尾页</span></a></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </#if>
                                    </div>
                                </#if>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#elseif section = "footer">

    </#if>
<script>
    $(document).ready(function() {
        $('#customerBalance').addClass('active');

        $('#customerListBalanceLog').addClass('active');

        $('#customerBalanceSelect').addClass('selected');

        $('#customerBalanceArrow').addClass('arrow open');
    });
</script>
</@layout>
