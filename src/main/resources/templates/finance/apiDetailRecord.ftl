
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

                <#--搜索框-->

                    <form action="/api/find-all-api-record/detail" class="form-bottom api_record_detail" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">ApiId</label>

                                <div class="controls">

                                    <input type="text" id="apiId" name="apiId" value="${apiId?c}" class="m-wrap medium">

                                </div>
                            </div>
                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">apiTypeName</label>

                                <div class="controls">

                                    <input type="text" id="apiTypeName" name="apiTypeName" value="${apiTypeName}" class="m-wrap medium">

                                </div>
                            </div>
                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">mobileOperatorName</label>

                                <div class="controls">

                                    <input type="text" id="mobileOperatorName" name="mobileOperatorName" <#if mobileOperatorName??>value="${mobileOperatorName}"</#if> class="m-wrap medium">

                                </div>
                            </div>
                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">vendorName</label>

                                <div class="controls">

                                    <input type="text" id="vendorName" name="vendorName" value="${vendorName}" class="m-wrap medium">

                                </div>
                            </div>
                            <div class="pull-left margin-right-20 head-search-bottom">

                                <label class="control-label">起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>
                            <div class="pull-left head-search-bottom">

                                <label class="control-label">结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

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

                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i><#if apiTypeName??>${apiTypeName!''}<#if mobileOperatorName??>——${mobileOperatorName!''}</#if></#if><#if vendorName??>@${vendorName!''}</#if></div>

                            <@d.tools idName="exportExcel" hrefName="/api/find-all-api-record/detail?export=true"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">消费金额共计&yen;：<#if totleAmount??><span>${totleAmount/100.0}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed" id="sample_10">
                                    <thead>
                                    <tr>
                                        <th>公司名称</th>
                                        <th>消费金额（单位：元）</th>
                                        <th>响应时间（单位：秒）</th>
                                        <th>创建时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if apiRequestLogList??>
                                            <#list apiRequestLogList as apiRequestLog>
                                            <tr>
                                                <td data-title="公司名称"><#if apiRequestLog.company??>${apiRequestLog.company.name!''}<#else></#if></td>
                                                <td data-title="消费金额（单位：元）"><#if apiRequestLog.apiResponseLog??>${(apiRequestLog.apiResponseLog.cost/100.0)!''}<#else></#if></td>
                                                <td data-title="响应时间（单位：秒）"><#if apiRequestLog.apiResponseLog??>${(apiRequestLog.apiResponseLog.resTime/1000.0)!''}<#else></#if></td>
                                                <td data-title="创建时间">${apiRequestLog.createTime!''}</td>
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

    <script type="text/javascript" src="/js/former/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/former/DT_bootstrap.js"></script>

    <script src="/js/myjs/api-detail.js"></script>

    <script src="/js/oldlocal/api-detail-Record.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            ApiDetailRecord.init();
        });

    </script>

    </#if>

</@layout>
