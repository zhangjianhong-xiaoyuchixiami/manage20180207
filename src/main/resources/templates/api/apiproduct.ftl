
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

                    <form action="/api/api-message" class="form-bottom api_product_record" method="get">

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

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="tabbable tabbable-custom boxless">

                        <ul class="nav nav-tabs">

                            <li class="active"><a href="#tab_1" data-toggle="tab">正在使用的产品</a></li>

                            <li><a class="" href="#tab_2" data-toggle="tab">已停用的产品</a></li>

                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane active" id="tab_1">

                            <#--正在使用的产品-->
                                <div class="portlet box grey">

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
                                                    <th>产品类型</th>
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

                                                            <td data-title="产品类型">${api.apiType.name}
                                                                <#if (api.mobileOperatorList?size>0)>--
                                                                    <#list api.mobileOperatorList as mobileOperator>
                                                                    ${mobileOperator.name}<#if (api.mobileOperatorList?size>1)>,</#if>
                                                                    </#list>
                                                                </#if>
                                                                <#if api.proxyApi.proxyApiTypeName??>（调用：${api.proxyApi.proxyApiTypeName!''}）</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (api.apiVendor.partner.id)??>
                                                                    <a href="/api/api-message?partnerId=${(api.apiVendor.partner.id)!''}">${(api.apiVendor.partner.name)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
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

                            <div class="tab-pane " id="tab_2">

                            <#--已停用的产品-->
                                <div class="portlet box grey">

                                    <div class="portlet-body no-more-tables">
                                        <div class="clearfix margin-bottom-20">

                                            <div class="pull-left table-top-bottom">

                                                <label class="control-label">注：用绿色标注的行是产品主通道</label>

                                            </div>

                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_product_2">
                                                <thead>
                                                <tr>
                                                    <th>产品类型</th>
                                                    <th>合作公司</th>
                                                    <th>产品供应商</th>
                                                    <th>价格（单位：元）</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if apiListDead??>
                                                        <#list apiListDead as api>
                                                            <#if api.proxyApi.minCost==api.cost>
                                                            <tr class="success">
                                                            <#else >
                                                            <tr>
                                                            </#if>
                                                            <td data-title="产品类型" class="font-text-decoration">${api.apiType.name}
                                                                <#if (api.mobileOperatorList?size>0)>--
                                                                    <#list api.mobileOperatorList as mobileOperator>
                                                                    ${mobileOperator.name}<#if (api.mobileOperatorList?size>1)>,</#if>
                                                                    </#list>
                                                                </#if>
                                                                <#if api.proxyApi.proxyApiTypeName??>（调用：${api.proxyApi.proxyApiTypeName!''}）</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (api.apiVendor.partner.id)??>
                                                                    <a href="/api/api-message?partnerId=${(api.apiVendor.partner.id)!''}">${(api.apiVendor.partner.name)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
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

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/myjs/api-product.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            ApiProduct.init();
        });

    </script>

    </#if>

</@layout>
