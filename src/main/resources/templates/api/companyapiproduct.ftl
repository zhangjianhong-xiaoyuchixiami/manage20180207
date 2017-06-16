
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

                    <form action="/api/api-message-by-company" class="form-bottom api_company_product_record" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select class="medium m-wrap chosen" data-placeholder="请选择..." tabindex="1" id="apiTypeId" name="apiTypeId">
                                        <option value=""></option>
                                        <#if apiTypeList??>
                                            <#list apiTypeList as apiType>
                                                <option <#if apiTypeId?? && apiTypeId==apiType.id>selected="selected"</#if> value="${apiType.id}">${apiType.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <select class="medium m-wrap chosen" data-placeholder="请选择..." tabindex="1" id="companyId" name="companyId">
                                        <option value=""></option>
                                        <#if companyList??>
                                            <#list companyList as company>
                                                <option <#if companyId?? && companyId==company.id>selected="selected"</#if> value="${company.id}">${company.name}<#if company.partner??>@${(company.partner.name)!''}</#if></option>
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

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_company_product_1">
                                                <thead>
                                                <tr>
                                                    <th>产品类型</th>
                                                    <th>公司名称</th>
                                                    <th>合作公司</th>
                                                    <th>价格（单位：元）</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if companyApiList??>
                                                        <#list companyApiList as company>
                                                        <tr>
                                                            <td data-title="产品类型">${company.apiTypeName}<#if company.subTypeName??>--${company.subTypeName!''}</#if></td>
                                                            <#if company.companyStatus?? && company.companyStatus == -1>
                                                            <td data-title="公司名称" class="font-text-decoration">
                                                            <#else >
                                                            <td data-title="公司名称">
                                                            </#if>
                                                                ${(company.companyName)!''}<#if company.partnerName??>@${company.partnerName}</#if>
                                                            </td>
                                                            <td data-title="合作公司">
                                                                <#if (company.partnerId)??>
                                                                    <a href="/api/api-message-by-company?partnerId=${(company.partnerId)!''}">${(company.partnerName)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
                                                            <td data-title="价格（单位：元）">${(company.price/100.0)?c}</td>
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

                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_company_product_2">
                                                <thead>
                                                <tr>
                                                    <th>产品类型</th>
                                                    <th>公司名称</th>
                                                    <th>合作公司</th>
                                                    <th>价格（单位：元）</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <#if companyApiListDead??>
                                                        <#list companyApiListDead as company>
                                                        <tr>
                                                            <td data-title="产品类型" class="font-text-decoration">${company.apiTypeName}<#if company.subTypeName??>--${company.subTypeName!''}</#if></td>
                                                            <td data-title="公司名称">${(company.companyName)!''}<#if company.partnerName??>@${company.partnerName}</#if></td>
                                                            <td data-title="合作公司">
                                                                <#if (company.partnerId)??>
                                                                    <a href="/api/api-message-by-company?partnerId=${(company.partnerId)!''}">${(company.partnerName)!'无'}</a>
                                                                <#else >
                                                                    无
                                                                </#if>
                                                            </td>
                                                            <td data-title="价格（单位：元）">${(company.price/100.0)?c}</td>
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

    <script src="/js/myjs/company-api-product.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            CompanyApiProduct.init();
        });

    </script>

    </#if>

</@layout>
