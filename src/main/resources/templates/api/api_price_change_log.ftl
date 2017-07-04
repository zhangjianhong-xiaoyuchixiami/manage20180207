
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

                <#--搜索框-->

                    <form action="/api/api-price-change-log" class="form-bottom api_price_change_log" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label >产品类型</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="apiTypeId" name="apiTypeId">
                                        <option value=""></option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                    </select>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >产品供应商</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="vendorId" name="vendorId">
                                        <option value=""></option>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >合作公司</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="partnerId" name="partnerId">
                                        <option value=""></option>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >价格区间</label>

                                <div class="controls">

                                    <input type="text" placeholder="最低价" class="m-wrap small">

                                    --

                                    <input type="text" placeholder="最高价" class="m-wrap small">

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

                    <div id="form_modal_add_price_change_log" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_add_price_change_log" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel_add_price_change_log">请填写信息</h3>
                        </div>
                        <div class="modal-body">
                            <form action="#" class="form-horizontal">

                                <span id="add_price_change_log_apiId" style="display: none;">

                                </span>

                                <div id="" class="control-group">
                                    <label class="control-label">请选择产品类型<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="add_price_change_log_apiTypeId" name="add_price_change_log_apiTypeId" class="medium m-wrap" tabindex="1">
                                            <option value=""></option>
                                        </select>
                                        <span id="add_price_change_log_apiTypeIdMsg" class="help-inline"></span>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请选择产品供应商<span class="required">*</span></label>
                                    <div class="controls">
                                        <select id="add_price_change_log_vendorId" name="add_price_change_log_vendorId" class="medium m-wrap" tabindex="1">
                                            <option value=""></option>
                                        </select>
                                        <span id="add_price_change_log_vendorIdMsg" class="help-inline"></span>
                                    </div>
                                </div>

                                <div id="" class="control-group">
                                    <label class="control-label">请输入金额<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" id="add_price_change_log_amount" name="add_price_change_log_amount" class="m-wrap medium" placeholder="（单位/元）">
                                        <span id="add_price_change_log_amountMsg" class="help-line"></span>
                                        <span class="help-block">说明：只能输入数字类型并且金额大于0</span>
                                    </div>
                                </div>

                                <div class="control-group">

                                    <label class="control-label">请选择生效时间</label>

                                    <div class="controls">

                                        <div class="input-append date form_datetime" data-date-viewmode="years" data-date-minviewmode="months">

                                            <input id="add_price_change_log_Date" name="add_price_change_log_Date" size="16" type="text" value="" readonly class="m-wrap">

                                            <span class="add-on"><i class="icon-remove"></i></span>

                                            <span class="add-on"><i class="icon-calendar"></i></span>

                                        </div>

                                    </div>

                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                            <button class="btn black btn-primary" id="add-price-change-log-btn-black-btn-primary" type="button">提交</button>
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

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/sweetalert/sweetalert2.min.js"></script>

    <script src="/js/sweetalert/core.js"></script>

    <script src="/js/former/select2/select2.min.js"></script>

    <script src="/js/former/select2/i18n/zh-CN.js"></script>

    <script src="/js/former/bootstrap-datetimepicker.js"></script>

    <script src="/js/myjs/api-price-change-log.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            ApiPriceChangeLog.init();

        });

    </script>

    </#if>

</@layout>
