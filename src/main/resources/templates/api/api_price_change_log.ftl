
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

                                    <select class="medium m-wrap chosen" data-placeholder="请选择..." tabindex="1" id="vendorId" name="vendorId">
                                        <option value=""></option>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >合作公司</label>

                                <div class="controls">

                                    <select class="medium m-wrap chosen" data-placeholder="请选择..." tabindex="1" id="partnerId" name="partnerId">
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

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label >结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

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
                                  <button class="btn-icon black" id="batchApiPriceLog">
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
                                    <th>有效时间范围</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if apiList??>
                                        <#list apiList as api>
                                        <tr>
                                            <td data-title="选择"><input class="checkboxes" type="checkbox" id="checkBoxApiId" name="checkBoxApiId" value=""/></td>
                                            <td data-title="产品类型"></td>
                                            <td data-title="合作公司"></td>
                                            <td data-title="产品供应商"></td>
                                            <td data-title="价格（单位：元）"></td>
                                            <td data-title="有效时间范围"></td>
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

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/sweetalert/sweetalert2.min.js"></script>

    <script src="/js/sweetalert/core.js"></script>

    <script src="/js/former/select2.min.js" type="text/javascript" ></script>

    <script src="/js/former/i18n/zh-CN.js" type="text/javascript" ></script>

    <script src="/js/myjs/api-price-change-log.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            ApiPriceChangeLog.init();

            $('#apiTypeId').select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });



        });

    </script>

    </#if>

</@layout>
