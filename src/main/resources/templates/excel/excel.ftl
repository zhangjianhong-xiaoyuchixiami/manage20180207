
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

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">上游供应商对账</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">合作公司对账</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">下游客户对账</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                                <form action="" class="form-bottom form-top" method="get">

                                    <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                                        <div class="pull-left head-search-bottom">

                                            <label >合作公司</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="pid" name="pid">
                                                    <option value=""></option>
                                                    <option value="">英齐</option>
                                                    <option value="">证信</option>
                                                </select>

                                            </div>

                                        </div>

                                        <div class="pull-left head-search-bottom">

                                            <label >对账对象</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="obj" name="obj">
                                                    <option value=""></option>
                                                    <option value="1">对方消费我方源</option>
                                                    <option value="2">我方消费对方源</option>
                                                    <option value="3">对方售卖</option>
                                                </select>

                                            </div>

                                        </div>

                                        <div class="pull-left head-search-bottom">

                                            <label >统计方式</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="wid" name="wid">
                                                    <option value=""></option>
                                                    <option value="1">汇总</option>
                                                    <option value="2">按月汇总</option>
                                                    <option value="3">按天汇总</option>
                                                </select>
                                            </div>

                                        </div>

                                        <div class="pull-left head-search-bottom">

                                            <label >不统计的公司</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" multiple id="cid" name="cid">
                                                    <option value=""></option>
                                                    <option value="">考拉</option>
                                                    <option value="">和实信</option>
                                                    <option value="">豆子</option>
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

                                                    <button class="btn black" type="submit">确定</button>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </form>

                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group pull-right">
                                                <button class="btn-icon black" id="batchId">
                                                    <i class="icon-share icon-black"></i>导出Excel
                                                </button>
                                            </div>
                                        </div>


                                        <table class="table table-hover table-condensed" id="sample_1">
                                            <thead>
                                            <tr>
                                                <th>产品类型</th>
                                                <th>产品供应商</th>
                                                <th>进货价（单位：元）</th>
                                                <th>计费次数</th>
                                                <th>金额（单位：元）</th>
                                                <th>统计区间</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                    <tr>
                                                        <td data-title="产品类型">三要素</td>
                                                        <td data-title="产品供应商">小视</td>
                                                        <td data-title="进货价（单位：元）">0.23</td>
                                                        <td data-title="计费次数">1000</td>
                                                        <td data-title="金额（单位：元）">123</td>
                                                        <td data-title="统计区间">2017.06.01-2017.06.30</td>
                                                    </tr>
                                                    <tr>
                                                        <td data-title="产品类型">三要素</td>
                                                        <td data-title="产品供应商">小视</td>
                                                        <td data-title="进货价（单位：元）">0.23</td>
                                                        <td data-title="计费次数">1000</td>
                                                        <td data-title="金额（单位：元）">123</td>
                                                        <td data-title="统计区间">2017.06.01-2017.06.30</td>
                                                    </tr>
                                                    <tr>
                                                        <td data-title="产品类型">三要素</td>
                                                        <td data-title="产品供应商">小视</td>
                                                        <td data-title="进货价（单位：元）">0.23</td>
                                                        <td data-title="计费次数">1000</td>
                                                        <td data-title="金额（单位：元）">123</td>
                                                        <td data-title="统计区间">2017.06.01-2017.06.30</td>
                                                    </tr>
                                            </tbody>
                                        </table>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane " id="tab_2">



                            </div>

                            <div class="tab-pane " id="tab_3">



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

            $("#cid").select2({

                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true

            })

        });


    </script>

    </#if>

</@layout>
