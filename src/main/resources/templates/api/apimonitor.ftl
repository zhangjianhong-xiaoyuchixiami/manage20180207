
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

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">注：本页面最近失败次数计算逻辑：请求失败，次数+1；请求成功，次数修改为0</label>

                                </div>

                            </div>

                            <div class="table-responsive">

                                <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                    <thead>
                                    <tr>
                                        <th>产品类型</th>
                                        <th>产品供应商</th>
                                        <th>最近连续失败次数</th>
                                        <th>近一小时请求次数</th>
                                        <th>近一小时失败次数</th>
                                        <th>失败率</th>
                                        <th>最后请求失败时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiBanList??>
                                            <#list apiBanList as apiBan>
                                            <tr>
                                                <td data-title="产品类型">${apiBan.apiTypeName!'无'}
                                                    <#if (apiBan.mobileOperatorList?size>0)>--
                                                        <#list apiBan.mobileOperatorList as mobileOperator>
                                                        ${mobileOperator.name}<#if (apiBan.mobileOperatorList?size>1)>,</#if>
                                                        </#list>
                                                    </#if>
                                                </td>
                                                <td data-title="产品供应商">${apiBan.vendorName!'无'}<#if apiBan.partnerId??>@${apiBan.partnerName!'无'}</#if></td>
                                                <td data-title="最近连续失败次数">${(apiBan.fc)!'0'}</td>
                                                <td data-title="近一小时请求次数">${(apiBan.totleCount)!'0'}</td>
                                                <td data-title="近一小时失败次数">${(apiBan.failCount)!'0'}</td>
                                                <td data-title="失败率">${(apiBan.failRate)!'0'}%</td>
                                                <td data-title="最后请求失败时间">${(apiBan.ts)!'无'}</td>
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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/api-monitor.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            ApiMonitor.init();
        });

    </script>

    </#if>

</@layout>
