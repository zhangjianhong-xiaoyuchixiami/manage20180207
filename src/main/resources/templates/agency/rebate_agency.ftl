
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

                    <div class="row-fluid invoice">

                        <div class="row-fluid">

                            <table class="table table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="width: 20%">代理人</th>
                                    <th style="width: 10%">税率</th>
                                    <th>分成规则</th>
                                    <th style="width: 15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if agencyList??>
                                    <#list agencyList as agency>
                                    <tr>
                                        <td>${agency.name!'无'}</td>
                                        <td>${agency.taxRateName!'无'}</td>
                                        <td>${agency.rule!'无'}</td>
                                        <td><a href="/finance/rebate/detail?agencyId=${agency.id?c}">分成账单</a></td>
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

    <script src="/js/myjs/rebate/rebate_agency.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function() {

            RebateAgency.init();

        });

    </script>

    </#if>

</@layout>
