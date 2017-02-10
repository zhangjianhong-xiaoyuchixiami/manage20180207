
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/api/find-all-api-record/detail" method="get">

                        <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">ApiId</label>

                                <div class="controls">

                                    <input type="text" id="apiId" name="apiId" value="${apiId?c}" class="m-wrap medium">

                                </div>
                            </div>
                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">apiTypeName</label>

                                <div class="controls">

                                    <input type="text" id="apiTypeName" name="apiTypeName" value="${apiTypeName}" class="m-wrap medium">

                                </div>
                            </div>
                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">mobileOperatorName</label>

                                <div class="controls">

                                    <input type="text" id="mobileOperatorName" name="mobileOperatorName" <#if mobileOperatorName??>value="${mobileOperatorName}"</#if> class="m-wrap medium">

                                </div>
                            </div>
                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">vendorName</label>

                                <div class="controls">

                                    <input type="text" id="vendorName" name="vendorName" value="${vendorName}" class="m-wrap medium">

                                </div>
                            </div>


                            <div class="control-group pull-left margin-right-20" style="margin-bottom: -20px;">

                                <label class="control-label">起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

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

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i><#if apiTypeName??>${apiTypeName!''}<#if mobileOperatorName??>——${mobileOperatorName!''}</#if></#if><#if vendorName??>@${vendorName!''}</#if></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">消费金额共计&yen;：<#if totleAmount??><span>${totleAmount/100.0}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_10">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">公司名称</th>
                                    <th style="text-align: center;">消费金额（单位：元）</th>
                                    <th style="text-align: center;">响应时间（单位：秒）</th>
                                    <th style="text-align: center;">创建时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if apiRequestLogList??>
                                <#list apiRequestLogList as apiRequestLog>
                                <tr class="odd gradeX">
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

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/customer-company.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>

    <script type="text/javascript">

        $(document).ready(function() {

            $('#exportExcel').on('click', function () {
                var apiId = $('#apiId').val();
                var beginDate = $('#beginDate').val();
                var endDate = $('#endDate').val();
                fetch('/excel-api-finance/find-all-api-record/detail?apiId='+apiId+'&beginDate='+beginDate+'&endDate='+endDate).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename ='产品消费明细账单.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });
        });
    </script>

    <script>
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#apiRecordLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');
        });
    </script>
    </#if>

</@layout>
