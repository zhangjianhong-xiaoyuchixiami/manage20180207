
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box grey">

                        <div class="portlet-body">

                            <div class="table-responsive">

                                <table class="table table-bordered table-hover table-condensed" id="sample_2">
                                    <thead>
                                        <tr>
                                            <th>产品名称</th>
                                            <th>供应商名称</th>
                                            <th>当日请求条数</th>
                                            <th>请求失败率</th>
                                            <th>响应时间小于500ms的数量</th>
                                            <th hidden>请求成功条数</th>
                                            <th hidden>请求失败条数</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th hidden>响应时间小于500ms的数量</th>
                                            <th>响应时间走势</th>
                                            <th>给此产品添加标签</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiResponseConditions??>
                                            <#list apiResponseConditions as condition>
                                                <tr>

                                                    <td data-title="产品名称" rowspan="1" >
                                                        <#if condition.apiName??>
                                                        (apiId = ${condition.apiId})
                                                        ${condition.apiName!'未知'}
                                                        </#if>
                                                    </td>
                                                    <td data-title="供应商名称">${condition.vendorName}</td>
                                                    <td data-title="当日请求条数">${condition.totalCount}</td>
                                                    <td data-title="请求失败率">${condition.failPercent}</td>
                                                    <td data-title="响应时间小于500ms的数量">${condition.count1}</td>
                                                    <td data-title="请求成功条数" hidden>${condition.successCount}</td>
                                                    <td data-title="请求失败条数" hidden>${condition.failCount}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count1}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count2}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count3}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count4}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count5}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count6}</td>
                                                    <td data-title="响应时间小于500ms的数量" hidden>${condition.count7}</td>
                                                    <td data-title="响应时间走势"><a href="/api-response-condition/api-response-time-trends?apiId=${condition.apiId}"  data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="响应时间走势">查看</a></td>
                                                    <td data-title="给此产品添加标签"><a href="#api_tag" onclick="addApiTags(${condition.apiId})" data-toggle="modal" data-toggle="tooltip" data-placement="bottom" title="给此产品添加标签">给此产品添加标签</a></td>

                                                </tr>

                                            </#list>
                                        </#if>
                                    </tbody>

                                </table>

                            </div>

                        </div>

                    </div>

                <#--&lt;#&ndash;产品添加标签&ndash;&gt;
                    <div id="api_tag" class="modal hide fade myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_5" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_5"></span>为该产品添加标签</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="yestHourConsumerIncomeCondition">

                                <input type="text" id="abc">
                                <input type="button" value="提交">

                            </table>
                        </div>
                    </div>-->





                    <div id="api_tag" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_api_tags" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="add-api-tags">为该产品添加标签</h3>
                        </div>

                        <div class="modal-body">
                            <div class="clearfix">
                                    <div class="btn-group" style="margin-bottom: 10px;">
                                        <a id="api_tags_new" class="btn black">
                                            添加<i class="icon-plus"></i>
                                        </a>
                                    </div>
                            </div>
                            <span id="api_tags" style="display: none;">

                            </span>
                            <div id="error_alert_api_tags" style="margin-bottom: -15px;margin-top: 10px;">

                            </div>
                            <table class="table table-striped table-hover table-bordered table-condensed" id="api_tag_list">
                                <thead>
                                <tr>
                                    <th>产品标签</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <#--<div id="api-nearly-time-trends" class="modal hide fade myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_4" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 id="myModalLabel_4"></span>响应时间走势</h4>
                        </div>

                        <div class="modal-body">

                            <table class="table table-striped table-hover table-bordered table-condensed" id="apiNearTimeTrends">

                                <div id="container"></div>

                            </table>
                        </div>
                    </div>-->

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/myjs/api-response-condition.js?v=${ver}"></script>

    <script src="/js/myjs/api_finance_condition_left_bar.js?v=${ver}"></script>

    <script src="/js/oldlocal/api-tags-operation.js?v=${ver}"></script>

    <script>
        jQuery(document).ready(function() {
            ApiResponseCondition.init();
            ApiFinanceConditionLeftBar.init();

        });

    </script>

</#if>

</@layout>
