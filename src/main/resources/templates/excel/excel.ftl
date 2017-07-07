
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

                            <li class="active"><a href="#tab_1" data-toggle="tab">上游供应商</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">合作公司</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">下游客户</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                                <form action="/api/api-price-change-log" class="form-bottom form-top api_price_change_log" method="get">

                                    <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                                        <div class="pull-left head-search-bottom">

                                            <label >对账对象</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="apiTypeId" name="apiTypeId">
                                                    <option value=""></option>
                                                    <option value="">上游供应商</option>
                                                    <option value="">合作伙伴</option>
                                                    <option value="">下游客户</option>
                                                </select>

                                            </div>

                                        </div>

                                        <div class="pull-left head-search-bottom">

                                            <label >对账方式</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="vendorId" name="vendorId">
                                                    <option value=""></option>
                                                    <option value="">汇总</option>
                                                    <option value="">按月汇总</option>
                                                    <option value="">按天汇总</option>
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

                                                    <button class="btn black" type="submit">搜索</button>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </form>

                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group pull-left">
                                                <button class="btn-icon black" id="batchApiPriceLog" data-target="#form_modal_add_price_change_log" data-toggle="modal">
                                                    <i class="icon-plus-sign"></i>添加
                                                </button>
                                            </div>
                                        </div>

                                        <table class="table table-striped table-bordered table-hover table-condensed" id="sample_api_price_change_log">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_api_price_change_log .checkboxes"/></th>
                                                <th>产品类型</th>
                                                <th>合作公司</th>
                                                <th>产品供应商</th>
                                                <th>价格（单位：元）</th>
                                                <th>生效区间</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apclList??>
                                                    <#list apclList as apcl>
                                                    <tr>
                                                        <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxApiId" name="checkBoxApiId" value="${apcl.apiId}"/></td>
                                                        <td data-title="产品类型">${(apcl.apiType.name)!'无'}</td>
                                                        <td data-title="合作公司">${(apcl.partner.name)!'无'}</td>
                                                        <td data-title="产品供应商">${(apcl.apiVendor.name)!'无'}</td>
                                                        <td data-title="价格（单位：元）">${apcl.price/100.0}</td>
                                                        <td data-title="生效区间">${(apcl.timeRange)!'无'}</td>
                                                    </tr>
                                                    </#list>
                                                </#if>
                                            </tbody>
                                        </table>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane " id="tab_2">

                                <form action="/api/api-price-change-log" class="form-bottom form-top api_price_change_log" method="get">

                                    <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                                        <div class="pull-left head-search-bottom">

                                            <label >对账方式</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="vendorId" name="vendorId">
                                                    <option value=""></option>
                                                    <option value="">汇总</option>
                                                    <option value="">按月汇总</option>
                                                    <option value="">按天汇总</option>
                                                </select>
                                            </div>

                                        </div>

                                        <div class="pull-left margin-right-20 head-search-bottom">

                                            <label >起始日期</label>

                                            <div class="controls">

                                                <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                                    <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap" size="16" type="text" readonly style="width: 150px;">

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

                                                    <button class="btn black" type="submit">搜索</button>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </form>

                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group pull-left">
                                                <button class="btn-icon black" id="batchApiPriceLog" data-target="#form_modal_add_price_change_log" data-toggle="modal">
                                                    <i class="icon-plus-sign"></i>添加
                                                </button>
                                            </div>
                                        </div>

                                        <table class="table table-striped table-bordered table-hover table-condensed" id="sample_api_price_change_log">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_api_price_change_log .checkboxes"/></th>
                                                <th>产品类型</th>
                                                <th>合作公司</th>
                                                <th>产品供应商</th>
                                                <th>价格（单位：元）</th>
                                                <th>生效区间</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apclList??>
                                                    <#list apclList as apcl>
                                                    <tr>
                                                        <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxApiId" name="checkBoxApiId" value="${apcl.apiId}"/></td>
                                                        <td data-title="产品类型">${(apcl.apiType.name)!'无'}</td>
                                                        <td data-title="合作公司">${(apcl.partner.name)!'无'}</td>
                                                        <td data-title="产品供应商">${(apcl.apiVendor.name)!'无'}</td>
                                                        <td data-title="价格（单位：元）">${apcl.price/100.0}</td>
                                                        <td data-title="生效区间">${(apcl.timeRange)!'无'}</td>
                                                    </tr>
                                                    </#list>
                                                </#if>
                                            </tbody>
                                        </table>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane " id="tab_3">

                                <form action="/api/api-price-change-log" class="form-bottom form-top api_price_change_log" method="get">

                                    <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                                        <div class="pull-left head-search-bottom">

                                            <label >对账对象</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="apiTypeId" name="apiTypeId">
                                                    <option value=""></option>
                                                    <option value="">上游供应商</option>
                                                    <option value="">合作伙伴</option>
                                                    <option value="">下游客户</option>
                                                </select>

                                            </div>

                                        </div>

                                        <div class="pull-left head-search-bottom">

                                            <label >对账方式</label>

                                            <div class="controls">

                                                <select class="medium m-wrap" id="vendorId" name="vendorId">
                                                    <option value=""></option>
                                                    <option value="">汇总</option>
                                                    <option value="">按月汇总</option>
                                                    <option value="">按天汇总</option>
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

                                                    <button class="btn black" type="submit">搜索</button>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </form>

                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">

                                        <div class="clearfix margin-bottom-5">
                                            <div class="btn-group pull-left">
                                                <button class="btn-icon black" id="batchApiPriceLog" data-target="#form_modal_add_price_change_log" data-toggle="modal">
                                                    <i class="icon-plus-sign"></i>添加
                                                </button>
                                            </div>
                                        </div>

                                        <table class="table table-striped table-bordered table-hover table-condensed" id="sample_api_price_change_log">
                                            <thead>
                                            <tr>
                                                <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#sample_api_price_change_log .checkboxes"/></th>
                                                <th>产品类型</th>
                                                <th>合作公司</th>
                                                <th>产品供应商</th>
                                                <th>价格（单位：元）</th>
                                                <th>生效区间</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if apclList??>
                                                    <#list apclList as apcl>
                                                    <tr>
                                                        <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxApiId" name="checkBoxApiId" value="${apcl.apiId}"/></td>
                                                        <td data-title="产品类型">${(apcl.apiType.name)!'无'}</td>
                                                        <td data-title="合作公司">${(apcl.partner.name)!'无'}</td>
                                                        <td data-title="产品供应商">${(apcl.apiVendor.name)!'无'}</td>
                                                        <td data-title="价格（单位：元）">${apcl.price/100.0}</td>
                                                        <td data-title="生效区间">${(apcl.timeRange)!'无'}</td>
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

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/former/bootstrap-datetimepicker.js"></script>

    </#if>

</@layout>
