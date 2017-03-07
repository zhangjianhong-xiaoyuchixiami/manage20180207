
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/api/api-message" class="api_product_record" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select id="apiTypeId" name="apiTypeId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if apiTypeId?? && apiTypeId==apiType.id>selected="selected"</#if> value="${apiType.id}">${apiType.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品供应商</label>

                                <div class="controls">

                                    <select id="vendorId" name="vendorId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}<#if apiVendor.partner??>@${apiVendor.partner.name}</#if></option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                <#--表格-->
                    <div class="portlet box grey">

                    <#-- <div class="portlet-title">

                         <div class="caption"><i class="icon-user"></i></div>

                     </div>-->

                        <div class="portlet-body no-more-tables">
                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">注：用绿色标注的行是产品主通道</label>

                                </div>

                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_product_1">
                                    <thead>
                                    <tr>
                                        <th>产品</th>
                                        <th>合作公司</th>
                                        <th>产品供应商</th>
                                        <th>价格（单位：元）</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiList??>
                                            <#list apiList as api>
                                                <#if api.proxyApi.minCost==api.cost>
                                                <tr class="success">
                                                <#else >
                                                <tr>
                                                </#if>

                                                <td data-title="产品">
                                                ${api.apiType.name}
                                                    <#if api.mobileOperator??>--${api.mobileOperator.name!''}</#if>
                                                    <#if api.proxyApi.proxyApiTypeName??>（调用：${api.proxyApi.proxyApiTypeName!''}
                                                        <#if api.proxyApi.proxyMobileOperatorName??>--${api.proxyApi.proxyMobileOperatorName!''}</#if>）
                                                    </#if>
                                                </td>
                                                <td data-title="合作公司">${(api.apiVendor.partner.name)!''}</td>
                                                <td data-title="产品供应商">${api.apiVendor.name}<#if api.apiVendor.partner??>@${api.apiVendor.partner.name}</#if></td>
                                                <td data-title="价格（单位：元）">${(api.cost/100.0)?c}</td>
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/api-product.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            ApiProduct.init();
        });

    </script>

    </#if>

</@layout>
