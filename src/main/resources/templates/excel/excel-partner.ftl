
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

                    <form action="/excel/extra-account-partner" id="submit_form" class="form-bottom-excel form-top" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label >合作公司</label>

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

                                <label >统计方式</label>

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

                                <label >不统计的客户</label>

                                <div id="cid_choose" class="controls">

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

                            <div class="pull-left">
                                <span><h4>${pim!'对方'}使用我方源</h4></span>
                            </div>

                            <div class="pull-right">
                                <a class="btn grey hidden-print" id="exportExcel" href="/excel/extra-account-partner?export=true">导出Excel  <i class="icon-share icon-black"></i></a>
                            </div>

                            <table class="table table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th>产品类型</th>
                                    <th class="hidden-480">产品供应商</th>
                                    <th class="hidden-480">进货价（单位：元）</th>
                                    <th>计费次数</th>
                                    <th class="hidden-480">金额（单位：元）</th>
                                    <th class="hidden-480">统计区间</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if exportExcelListPartnerUserOur ??>
                                        <#list exportExcelListPartnerUserOur as excel>
                                        <tr>
                                            <td>${excel.apiTypeName_stidName!''}</td>
                                            <td class="hidden-480">${excel.vendorName!''}</td>
                                            <td class="hidden-480">${excel.cost!''}</td>
                                            <td>${excel.sumCount!''}</td>
                                            <td class="hidden-480">${excel.sumCost!''}</td>
                                            <td class="hidden-480">${excel.consuTime!''}</td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                        </div>

                        <div class="row-fluid">

                            <div class="pull-left">
                                <span><h4>我方使用${pim!'对方'}源</h4></span>
                            </div>

                            <table class="table table-hover table-condensed" id="sample_2">
                                <thead>
                                <tr>
                                    <th>产品类型</th>
                                    <th class="hidden-480">产品供应商</th>
                                    <th class="hidden-480">进货价（单位：元）</th>
                                    <th class="hidden-480">计费次数</th>
                                    <th>金额（单位：元）</th>
                                    <th>统计区间</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if exportExcelListOurUserPartner ??>
                                        <#list exportExcelListOurUserPartner as excel>
                                        <tr>
                                            <td class="hidden-480">${excel.apiTypeName_stidName!''}</td>
                                            <td>${excel.vendorName!''}</td>
                                            <td>${excel.cost!''}</td>
                                            <td class="hidden-480">${excel.sumCount!''}</td>
                                            <td>${excel.sumCost!''}</td>
                                            <td>${excel.consuTime!''}</td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                        </div>

                        <div class="row-fluid">

                            <div class="pull-left">
                                <span><h4>${pim!'对方'}使用我方源售卖</h4></span>
                            </div>

                            <table class="table table-hover table-condensed" id="sample_3">
                                <thead>
                                <tr>
                                    <th class="hidden-480">产品类型</th>
                                    <th>产品供应商</th>
                                    <th>售卖价（单位：元）</th>
                                    <th class="hidden-480">计费次数</th>
                                    <th>金额（单位：元）</th>
                                    <th>统计区间</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if exportExcelListPartnerUserOurSell ??>
                                        <#list exportExcelListPartnerUserOurSell as excel>
                                        <tr>
                                            <td class="hidden-480">${excel.apiTypeName_stidName!''}</td>
                                            <td>${excel.vendorName!''}</td>
                                            <td>${excel.cost!''}</td>
                                            <td class="hidden-480">${excel.sumCount!''}</td>
                                            <td>${excel.sumCost!''}</td>
                                            <td>${excel.consuTime!''}</td>
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

                                    <li><strong>${pim!'对方'}使用我方源金额总计:</strong> ￥${partnerUserOurTotle!'0'}</li>

                                    <li><strong>我方使用${pim!'对方'}源金额总计:</strong> ￥${ourUserPartnerTotle!'0'}</li>

                                    <li><strong>${pim!'对方'}使用我方源售卖金额总计:</strong> ￥${partnerUserOurSellTotle!'0'}</li>

                                    <#if sum?? && (sum >= 0) >

                                        <li><strong>${pim!'对方'}应付我方金额总计:</strong> ￥${sum!'0'}</li>

                                    </#if>

                                    <#if sum?? && (sum < 0) >

                                        <li><strong>我方应付${pim!'对方'}金额总计:</strong> ￥${sum!'0'}</li>

                                    </#if>

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

    <script src="/js/myjs/excel/extra-excel.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            ExtraExcel.init();

        });


    </script>

    </#if>

</@layout>
