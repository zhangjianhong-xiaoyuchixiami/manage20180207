
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

                    <form action="/customerBalance/findAllCustomerBalanceLogByCustomerId" method="get">

                        <div class="clearfix margin-bottom-20">

                            <div class="control-group pull-left"
                            >

                                <label class="control-label">状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-2">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-2">成功

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-1">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-1">失败

                                    </label>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">Api类型</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">二要素</option>
                                        <option value="">三要素</option>
                                        <option value="">四要素</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">Api供应商</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">移动</option>
                                        <option value="">联通</option>
                                        <option value="">电信</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">Api</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">sdsds</option>
                                        <option value="">gfdgfd</option>
                                        <option value="">gdfgfd</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left margin-right-20">

                                <label class="control-label">起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

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

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">成功次数：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>23次</span></#if></label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">失败次数：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0次</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 20%">Api类型</th>
                                    <th class="sorting" style="text-align: center; width: 30%">
                                        金额（单位/元）
                                    </th>
                                    <th class="sorting" style="text-align: center; width: 30%">
                                        创建时间
                                    </th>
                                    <th style="text-align: center; width: 20%">状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#--<#if customerBalanceLogList??>-->
                                <#--<#list customerBalanceLogList as customerBalanceLog>-->
                                <tr>
                                    <td style="text-align: center;">三要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">二要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">四要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">三要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">二要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">四要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">三要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">二要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;">四要素</td>
                                    <td style="text-align: center;">10</td>
                                    <td style="text-align: center;">2016.12.12</td>
                                    <td style="text-align: center;">成功</td>
                                </tr>
                                <#--</#list>-->
                                <#--</#if>-->
                                </tbody>
                            </table>

                            <div class="row-fluid">

                                <div class="span6">

                                    <div class="dataTables_info" id="sample_1_info">共1234条记录 当前显示第 1 页 共 10 页</div>

                                </div>

                                <div class="span6">

                                    <div class="dataTables_paginate paging_bootstrap pagination pull-right">

                                        <ul>

                                            <li class="prev disabled"><a href="#">首页</a></li>

                                            <li class="prev disabled"><a href="#">← <span class="hidden-480">上一页</span></a></li>

                                            <#--<li class="active"><a href="#">1</a></li>-->

                                            <#--<li><a href="#">2</a></li>-->

                                            <#--<li><a href="#">3</a></li>-->

                                            <#--<li><a href="#">4</a></li>-->

                                            <#--<li><a href="#">5</a></li>-->

                                            <li class="next"><a href="#"><span class="hidden-480">下一页</span> → </a></li>

                                            <li class="next"><a href="#">尾页</a></li>

                                        </ul>

                                    </div>

                                </div>

                            </div>

                        <#--<#if CustomerBalanceModifyReason=="扣费">-->
                        <#--<#if (count>0)>-->
                        <#--<div class="row-fluid">-->
                        <#--<div class="span6">-->
                        <#--<div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>-->
                        <#--</div>-->
                        <#--<#if (totlePage>0)>-->
                        <#--<div class="span6" align="right">-->
                        <#--<div class="dataTables_paginate paging_bootstrap pagination">-->
                        <#--<ul>-->
                        <#--<#if (pageSize>1)>-->
                        <#--<li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=1<#if reasonIdArray??><#list reasonIdArray as reasonId>&reasonId=${reasonId}</#list></#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">首页</span></a></li>-->
                        <#--<li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=${pageSize-1}<#if reasonIdArray??><#list reasonIdArray as reasonId>&reasonId=${reasonId}</#list></#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">上一页</span></a></li>-->
                        <#--</#if>-->
                        <#--<#if (pageSize<totlePage)>-->
                        <#--<li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=${pageSize+1}<#if reasonIdArray??><#list reasonIdArray as reasonId>&reasonId=${reasonId}</#list></#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">下一页</span></a></li>-->
                        <#--<li class="next"><a href="/customerBalance/findAllCustomerBalanceLogByCustomerId/${customerId}?pageSize=${totlePage}<#if reasonIdArray??><#list reasonIdArray as reasonId>&reasonId=${reasonId}</#list></#if><#if beginDate??>&beginDate=${beginDate}</#if><#if endDate??>&endDate=${endDate}</#if>" ><span class="hidden-480">尾页</span></a></li>-->
                        <#--</#if>-->
                        <#--</ul>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--</#if>-->
                        <#--</div>-->
                        <#--</#if>-->
                        <#--</#if>-->

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
