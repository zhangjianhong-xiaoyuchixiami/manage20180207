
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

                    <form action="/finance/customer-history-bill" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>公司状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if status??><#list status as status><#if status == 0>checked="checked"</#if></#list></#if> id="status" name="status" value="0">状态正常

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if status??><#list status as status><#if status== -1>checked="checked"</#if></#list></#if> id="status" name="status" value="-1">被禁用

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>请选择公司</label>

                                <div class="controls">

                                    <select class="medium m-wrap" multiple id="cid" name="cid">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as company>
                                                <option <#if cid??><#list cid as cid><#if cid?? && cid == company.id>selected="selected"</#if></#list></#if> value="${company.id}">${company.name}</option>
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

                            <#--<div class="pull-left head-search-bottom">-->

                                <#--<label >信用额度区间</label>-->

                                <#--<div class="controls">-->

                                    <#--<input type="text" id="LCredit" name="LCredit" <#if LCredit??> value="${LPic}" </#if> placeholder="最低值" class="m-wrap small">-->

                                    <#------>

                                    <#--<input type="text" id="HCredit" name="HCredit" <#if HCredit??> value="${HPic}" </#if> placeholder="最高值" class="m-wrap small">-->

                                <#--</div>-->

                            <#--</div>-->

                            <#--<div class="pull-left head-search-bottom">-->

                                <#--<label >可用额度区间</label>-->

                                <#--<div class="controls">-->

                                    <#--<input type="text" id="LUserAble" name="LUserAble" <#if LUserAble??> value="${LPic}" </#if> placeholder="最低值" class="m-wrap small">-->

                                    <#------>

                                    <#--<input type="text" id="HUserAble" name="HUserAble" <#if HUserAble??> value="${HPic}" </#if> placeholder="最高值" class="m-wrap small">-->

                                <#--</div>-->

                            <#--</div>-->

                            <#--<div class="pull-left head-search-bottom">-->

                                <#--<label >消费总额区间</label>-->

                                <#--<div class="controls">-->

                                    <#--<input type="text" id="LConsu" name="LConsu" <#if LConsu??> value="${LPic}" </#if> placeholder="最低值" class="m-wrap small">-->

                                    <#------>

                                    <#--<input type="text" id="HConsu" name="HConsu" <#if HConsu??> value="${HPic}" </#if> placeholder="最高值" class="m-wrap small">-->

                                <#--</div>-->

                            <#--</div>-->

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

                            <#--<div class="pull-left head-search-bottom">-->

                                <#--<label>请选择结束月份</label>-->

                                <#--<div class="controls">-->

                                    <#--<select class="medium m-wrap" id="end_month" name="end_month">-->
                                        <#--<option value=""></option>-->
                                        <#--<#if conTimeList??>-->
                                            <#--<#list conTimeList as conTime>-->
                                                <#--<option <#if end_month?? && end_month==conTime>selected="selected"</#if> value="${conTime}">${conTime}</option>-->
                                            <#--</#list>-->
                                        <#--</#if>-->
                                    <#--</select>-->

                                <#--</div>-->

                            <#--</div>-->

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

                        <div class="portlet-body">

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
                                    <th>公司名称</th>
                                    <th>合作公司</th>
                                    <th>信用额度</th>
                                    <th>可用额度</th>
                                    <th>充值总额</th>
                                    <th>消费总额(不包含当月)</th>
                                    <th>余额</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if billList??>
                                    <#list billList as bill>
                                    <tr>
                                        <td>${bill.companyName!'无'}</td>
                                        <td>${bill.partnerName!'无'}</td>
                                        <td>${bill.floor!'0'}</td>
                                        <td>${bill.userFloor!'0'}</td>
                                        <td>${bill.chargeAmount!'0'}</td>
                                        <td><a href="/finance/customer-history-bill/detail?cid=${bill.customerId}&name=${bill.companyName!'无'}" data-toggle="tooltip" data-placement="bottom" title="点击查看消费记录">${bill.consumeAmount!'0'}</a></td>
                                        <td>${bill.balance!'0'}</td>
                                        <td><a href="/finance/customer-history-bill/trend?cid=${bill.customerId}&name=${bill.companyName!'无'}">消费走势</a></td>
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

    <script src="/js/myjs/customer-history_bill.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            CustomerHistoryBill.init();
        })

    </script>

    </#if>

</@layout>
