
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

                    <form action="/finance/vendor-history-bill" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>供应商状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if status??><#list status as status><#if status == 0>checked="checked"</#if></#list></#if> id="status" name="status" value="0">正在供应

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if status??><#list status as status><#if status== -1>checked="checked"</#if></#list></#if> id="status" name="status" value="-1">已停用

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择供应商</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="vid" name="vid">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as vendor>
                                                <option <#if vid??><#list vid as vid><#if vid?? && vid == vendor.id>selected="selected"</#if></#list></#if> value="${vendor.id}">${vendor.name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择合作公司</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="pid" name="pid">
                                        <option value=""></option>
                                        <#if partnerList??>
                                            <#list partnerList as partner>
                                                <option <#if pid?? && pid==partner.id>selected="selected"</#if> value="${partner.id}">${partner.name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择月份</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="beg_month" name="beg_month">
                                        <option value=""></option>
                                        <#if conTimeList??>
                                            <#list conTimeList as conTime>
                                                <option <#if beg_month??><#list beg_month as beg><#if beg?? && beg == conTime> selected="selected"</#if></#list></#if> value="${conTime}">${conTime}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-5">
                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">充值总额&yen;：${chargeTot!'0.0'}元</label>

                                </div>

                                <div class="pull-left label-margin-bottom label-margin-right">

                                    <label class="control-label">消费总额&yen;：${consumeTot!'0.0'}元</label>

                                </div>

                                <div class="pull-right tip-remark">
                                    <span>注：本页面与金额相关数字单位都为：元</span>
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>
                                    <th>供应商</th>
                                    <th>合作公司</th>
                                    <th>充值总额</th>
                                    <th>消费总额(截止上月末)</th>
                                    <th>余额</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if billList??>
                                    <#list billList as bill>
                                    <tr>
                                        <td data-title="供应商" <#if bill.status == -1> class="font-text-decoration" </#if>>${bill.vendorName!'无'}</td>
                                        <td data-title="合作公司">${bill.partnerName!'无'}</td>
                                        <td data-title="充值总额">${bill.chargeAmount!'0'}</td>
                                        <td data-title="消费总额(截止上月末)"><a href="/finance/vendor-history-bill/detail?vid=${bill.vendorId}&name=${bill.vendorName}" data-toggle="tooltip" data-placement="bottom" title="点击查看消费记录">${bill.consumeAmount!'0'}</a></td>
                                        <td data-title="余额">${bill.balance!'0'}</td>
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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/vendor-history_bill.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            VendorHistoryBill.init();
        })

    </script>

    </#if>

</@layout>
