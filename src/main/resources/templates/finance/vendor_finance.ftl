
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

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

                        本页面与金额有关字段单位都是元<br>

                    </div>

                <#--搜索框-->

                    <div class="pull-left head-search-bottom head-search-display">

                        <label class="control-label">合作公司Id</label>

                        <div class="controls">

                            <input type="text" id="partnerId" name="partnerId" <#if partnerId??>value="${partnerId?c}"</#if> class="m-wrap medium">

                        </div>
                    </div>

                    <form action="/api/find-all-api-vendor-consume" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label>供应商状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="0">checked="checked"</#if></#list></#if> id="status" name="status" value="0">正在供应

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if statusArray??><#list statusArray as status><#if status=="-1">checked="checked"</#if></#list></#if> id="status" name="status" value="-1">已停用

                                    </label>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>产品供应商</label>

                                <div class="controls">

                                    <select class="medium m-wrap" id="vendorId" name="vendorId">
                                        <option value=""></option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label>起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label>结束日期</label>

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

                        <div class="portlet-title">

                            <div class="caption"></div>

                            <@d.tools idName="exportExcel" hrefName="/api/find-all-api-vendor-consume?export=true"></@d.tools>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_11_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                        <label><input type="checkbox" checked data-column="1">供应商</label>

                                        <label><input type="checkbox" checked data-column="2">合作公司</label>

                                        <label><input type="checkbox" checked data-column="3">充值总额</label>

                                        <label><input type="checkbox" checked data-column="4">消费总额</label>

                                        <label><input type="checkbox" checked data-column="5">余额</label>

                                        <label><input type="checkbox" data-column="6">上周消费</label>

                                        <label><input type="checkbox" data-column="7">上月消费</label>

                                        <label><input type="checkbox" checked data-column="8">本月消费</label>

                                        <label><input type="checkbox" checked data-column="9">当天消费</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body">

                            <div class="clearfix margin-bottom-5">

                                <table class="table-condensed flip-content" style="width: 100%">
                                    <tbody>
                                    <tr>
                                        <td>上周消费总额：${lastWeekConsume!'0.0'}</td>
                                        <td>上月消费总额：${lastMonthConsume!'0.0'}</td>
                                        <td>当月消费总额：${currMonthConsume!'0.0'}</td>
                                    </tr>
                                    <tr>
                                        <td>当天消费总额：${currDayConsume!'0.0'}</td>
                                        <td>消费总额：${totleConsume!'0.0'}</td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_11">
                                    <thead>
                                    <tr>
                                        <th>供应商</th>
                                        <th>合作公司</th>
                                        <th>充值总额</th>
                                        <th>消费总额（开通后--至昨天)</th>
                                        <th>余额（至昨天）</th>
                                        <th>上周消费</th>
                                        <th>上月消费</th>
                                        <th>当月消费（不包括昨天）</th>
                                        <th >当天消费</th>
                                        <th class="table-td-none">类型</th>
                                        <th class="table-td-none">当前价格</th>
                                        <th class="table-td-none">消费总额</th>
                                        <th class="table-td-none">调用成功次数</th>
                                        <th class="table-td-none">扣费次数</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if financeList??>
                                            <#list financeList as finance>
                                            <tr>
                                                <td data-title="供应商" <#if finance.status != 0> class="font-text-decoration" </#if> ><a href="/api/find-all-api-record<#if finance.vendorId??>?vendorId=${finance.vendorId}</#if>">${finance.vendorName}</a></td>
                                                <td data-title="合作公司">
                                                    <#if finance.partnerId??>
                                                        <a href="/api/find-all-api-vendor-consume?partnerId=${finance.partnerId?c}">${finance.partnerName!'无'}</a>
                                                    <#else>
                                                        无
                                                    </#if>
                                                </td>
                                                <td data-title="充值总额">${finance.charge!'0'}</td>
                                                <td data-title="消费总额">${finance.consume!'0'}</td>
                                                <td data-title="余额">${finance.balance!'0'}</td>
                                                <td data-title="上周消费">${finance.lastWeekConsume!'0'}</td>
                                                <td data-title="上月消费">${finance.lastMonthConsume!'0'}</td>
                                                <td data-title="本月消费">${finance.currMonthConsume!'0'}</td>
                                                <td data-title="当天消费"><a href="#form_modal_vendor_curr_day_api_type_consume" data-toggle="modal" onclick="currDayApiTypeConsume(${finance.vendorId!'0'})" data-toggle="tooltip" data-placement="bottom" title="点击查看当天消费情况">${finance.currDayConsume!'0'}</a></td>
                                                <td data-title="类型" class="table-td-none">
                                                    <#if finance.typeConsumeList??>
                                                        <#list finance.typeConsumeList as type>
                                                            <span <#if type.status == -1> class="font-text-decoration" </#if> >${type.apiTypeName!''}</span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="当前价格" class="table-td-none">
                                                    <#if finance.typeConsumeList??>
                                                        <#list finance.typeConsumeList as type>
                                                            <span>${type.cost!'0'}</span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="消费总额" class="table-td-none">
                                                    <#if finance.typeConsumeList??>
                                                        <#list finance.typeConsumeList as type>
                                                            <span>${type.consume!'0'}</span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <#--<td data-title="调用成功次数" class="table-td-none">
                                                    <#if finance.typeConsumeList??>
                                                        <#list finance.typeConsumeList as type>
                                                            <span>${type.userCount!'0'}</span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>-->
                                                <td data-title="扣费次数" class="table-td-none">
                                                    <#if finance.typeConsumeList??>
                                                        <#list finance.typeConsumeList as type>
                                                            <span>${type.feeCount!'0'}</span><br/>
                                                        </#list>
                                                    </#if>
                                                </td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>
                            </div>

                        <#--供应商当天产品类型消费弹框-->
                            <div id="form_modal_vendor_curr_day_api_type_consume" class="modal hide fade myModalCurrDayApiTypeConsume" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_vendor_curr_day_api_type_consume" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 id="myModalLabel_vendor_curr_day_api_type_consume"><span id="vendor_name"></span>当天各产品类型消费情况</h4>
                                </div>

                                <div class="modal-body">

                                    <table class="table table-striped table-hover table-bordered table-condensed" id="simple__curr_day_api_type_consume">
                                        <thead>
                                        <tr>
                                            <th>产品类型</th>
                                            <th>当前价格</th>
                                            <th>总消费额（单位：元）</th>
                                        <#--<th>请求次数</th>-->
                                            <th>扣费次数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/api-vendor.js?v=${ver}"></script>

    <script src="/js/myjs/api-vendor-two.js?v=${ver}"></script>

    <script src="/js/oldlocal/api-vendor-Record.js?v=${ver}"></script>

    <script src="/js/oldlocal/vendor-finance-curr-day-api-type-consume.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            ApiVendorRecord.init();

            $("#vendorId").select2({
                language: "zh-CN",
                placeholder: "请选择",
                allowClear: true
            });

        });

    </script>

    </#if>

</@layout>
