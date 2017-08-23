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

                    <form action="/company/balance-monitor" class="form-bottom" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-top">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls">

                                    <div class="input-append">

                                        <input class="m-wrap" <#if content??>value="${content}" </#if> type="text" id="companyName" name="content" placeholder="请输入公司名称">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                    <div class="portlet box grey">

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-hover table-bordered table-condensed table-full-width cf" id="Sample_1">
                                <thead class="cf">
                                <tr>
                                    <th><input type="checkbox" id="allChecked" class="group-checkable" data-set="#Sample_1 .checkboxes"/></th>
                                    <th>公司名称</th>
                                    <th>是否开启报警</th>
                                    <th>是否发送对方</th>
                                    <th>余额可用几天提醒</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td data-title="多选框"></td>
                                    <td data-title="公司名称"></td>
                                    <td data-title="是否开启报警"></td>
                                    <td data-title="是否发送对方"></td>
                                    <td data-title="余额可用几天提醒"></td>
                                    <td data-title="操作"></td>
                                </tr>
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

    <script src="/js/myjs/company-balance-monitor.js?v=${ver}"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            CompanyBalanceMonitor.init();

        });

    </script>

    </#if>

</@layout>