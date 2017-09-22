
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <div class="alert alert-success">

                        <button class="close" data-dismiss="alert"></button>

                        1、本页面与金额有关字段单位都是元<br>

                    <#-- <span class="label label-important">NOTE:</span>&nbsp;This feature is supported by Internet Explorer 10, Latest Firefox, Chrome, Opera and Safari
-->
                    </div>

                    <form action="/finance/rebate" id="submit_form" class="form-bottom-excel form-top" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if rate??> checked="checked" </#if> id="rate" name="rate" value="1">包含税率

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if cache??> checked="checked" </#if> id="cache" name="cache" value="1">包含缓存

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="">请选择代理人</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="agency" name="agency">
                                        <option value=""></option>
                                        <#if agencyList??>
                                            <#list agencyList as agency>
                                                <option <#if agencyId?? && agencyId == agency.id>selected="selected"</#if> value="${agency.id}">${agency.name}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="">请选择代理客户</label>

                                <div class="controls" id="cid_chosen">

                                    <select class="medium m-wrap" multiple id="cid" name="cid">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as company>
                                                <option <#if companyArray??><#list companyArray as array><#if array?? && array == company.companyId>selected="selected"</#if></#list></#if> value="${company.companyId}">${company.companyName}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >请选择周期</label>

                                <div id="tid_choose" class="controls">

                                    <select class="medium m-wrap" multiple id="cyc" name="cyc">
                                        <option value=""></option>
                                        <#if cycleList??>
                                            <#list cycleList as cycle>
                                                <option <#if cycleArray??><#list cycleArray as array><#if array?? && array == cycle>selected="selected"</#if></#list></#if> value="${cycle}">${cycle}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" id="submit" type="submit">确定</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="row-fluid invoice">

                        <div class="row-fluid">

                            <div class="portlet-title" style="margin-bottom: -15px">

                                <div class="actions">

                                    <#--<a class="btn grey hidden-print pull-right" style="margin-top: -9px" id="exportExcel" href="/excel/extra-account-customer?export=true">导出Excel  <i class="icon-share icon-black"></i></a>-->

                                    <div class="btn-group">

                                        <a class="btn" href="#" data-toggle="dropdown">

                                            表格显示列

                                            <i class="icon-angle-down"></i>

                                        </a>

                                        <div id="sample_1_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                            <label><input type="checkbox" checked data-column="0">代理人</label>

                                            <label><input type="checkbox" checked data-column="1">成本</label>

                                            <label><input type="checkbox" checked data-column="2">销售额</label>

                                            <label><input type="checkbox" checked data-column="3">毛利润</label>

                                            <label><input type="checkbox" checked data-column="4">税率</label>

                                            <label><input type="checkbox" checked data-column="5">首次业务回扣</label>

                                            <label><input type="checkbox" checked data-column="6">二次业务回扣</label>

                                            <label><input type="checkbox" checked data-column="7">净利润</label>

                                            <label><input type="checkbox" checked data-column="8">详情</label>

                                        </div>

                                    </div>

                                </div>

                            </div>

                            <table class="table table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th>代理人</th>
                                    <th>成本</th>
                                    <th>销售额</th>
                                    <th>毛利润</th>
                                    <th>税率</th>
                                    <th>首次业务回扣</th>
                                    <th>二次业务回扣</th>
                                    <th>净利润</th>
                                    <th>详情</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if billList??>
                                    <#list billList as bill>
                                    <tr>
                                        <td>${bill.agencyName!'无'}</td>
                                        <td>${bill.costMoney!'0'}</td>
                                        <td>${bill.priceMoney!'0'}</td>
                                        <td>${bill.grossProfit!'0'}</td>
                                        <td>${bill.taxRateResult!'--'}</td>
                                        <td>${bill.firstRebate!'--'}</td>
                                        <td>${bill.twiceRebate!'0'}</td>
                                        <td>${bill.netProfit!'0'}</td>
                                        <td><a href="/finance/rebate/detail?agencyId=${bill.agencyId}">详情</a></td>
                                    </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>

                        </div>

                        <div class="row-fluid">

                            <div class="span4">

                            </div>

                            <div class="span8 invoice-block">

                                <ul class="unstyled amounts">

                                    <li><strong>净利润总计:</strong> ￥${netProfitTot!'0'}</li>

                                </ul>

                                </br>

                                </br>

                                </br>

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

    <script src="/js/myjs/rebate/rebate.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            Rebate.init();

        });

    </script>

    </#if>

</@layout>
