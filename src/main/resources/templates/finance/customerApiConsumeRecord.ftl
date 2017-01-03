
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

                    <form action="/finance/findAllCustomerBalanceLogByCustomerId" method="get">

                        <div class="clearfix margin-bottom-20">

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

                                <div class="control-group pull-left">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>Api类型</th>
                                    <th>金额（单位/元）</th>
                                    <th style="text-align: center; width: 20%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#--<#if customerBalanceLogList??>-->
                                <#--<#list customerBalanceLogList as customerBalanceLog>-->
                                <tr class="odd gradeX">
                                    <td>三要素</td>
                                    <td>10</td>
                                    <td style="text-align: center;"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail">明细</a></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td>三要素</td>
                                    <td>10</td>
                                    <td style="text-align: center;"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail">明细</a></td>
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
