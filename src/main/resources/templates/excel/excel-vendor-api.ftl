
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

                    <form action="/excel/extra-account-vendor" id="submit_form" class="form-bottom-excel form-top" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="">请选择供应商<span class="required">*</span></label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="vid" name="vid">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as company>
                                                <option <#if vid?? && vid==company.id>selected="selected"</#if> value="${company.id}">${company.name}<#if company.partner??>@${(company.partner.name)!''}</#if></option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="">统计方式<span class="required">*</span></label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="wid" name="wid">
                                        <option value=""></option>
                                        <option <#if wid?? && wid == 1>selected="selected"</#if> value="1">汇总</option>
                                        <option <#if wid?? && wid == 2>selected="selected"</#if> value="2">按月汇总</option>
                                        <option <#if wid?? && wid == 3>selected="selected"</#if> value="3">按天汇总</option>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >产品类型</label>

                                <div id="tid_choose" class="controls">

                                    <select class="medium m-wrap" multiple id="tid" name="tid">
                                        <option value=""></option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if tid??><#list tid as tid><#if tid?? && tid == apiType.id>selected="selected"</#if></#list></#if> value="${apiType.id}">${apiType.name!''}</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label >起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap" size="16" type="text" style="width: 150px;">

                                        <span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap " size="16" type="text" style="width: 150px;">

                                        <span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

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

                            <div class="pull-right">
                                <a class="btn grey hidden-print" id="exportExcel" href="/excel/extra-account-vendor?export=true">导出Excel  <i class="icon-share icon-black"></i></a>
                            </div>

                            <table class="table table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th>产品类型</th>
                                    <th class="hidden-480">成本价（单位：元）</th>
                                    <th>计费次数</th>
                                    <th class="hidden-480">金额（单位：元）</th>
                                    <th class="hidden-480">统计区间</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if exportExcelList ??>
                                        <#list exportExcelList as excel>
                                        <tr>
                                            <td>${excel.apiName!''}</td>
                                            <td class="hidden-480">${excel.cost!''}</td>
                                            <td>${excel.totalCount!''}</td>
                                            <td class="hidden-480">${excel.amount!''}</td>
                                            <td class="hidden-480">${excel.consuTime!''}</td>
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

                                    <li><strong>金额总计:</strong> ￥${sumCost!'0'}</li>

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

    <script src="/js/myjs/excel/extra-excel-vendor.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            ExtraExcelVendor.init();

        });

    </script>

    </#if>

</@layout>
