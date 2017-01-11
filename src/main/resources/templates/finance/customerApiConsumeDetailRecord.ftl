
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

                    <div class="clearfix margin-bottom-20">

                        <form action="" method="get">

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">api类型Id</label>

                                <div class="controls">

                                    <input type="text" id="apiTypeId" name="apiTypeId" value="${apiTypeId}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" value="${customerId?c}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" value="${companyName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">api类型名称</label>

                                <div class="controls">

                                    <input type="text" id="apiTypeName" name="apiTypeName" value="${apiTypeName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">消费理由</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-1">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-1">消费扣费

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-2">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-2">弥补扣费

                                    </label>

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

                        </form>

                    </div>

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i><#if companyName??>${companyName}</#if><#if apiTypeName??>--${apiTypeName}</#if></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount/100.0}元&nbsp;&nbsp;&nbsp;</span><#else ><span>0元&nbsp;&nbsp;&nbsp;</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_7">
                                <thead>
                                <tr>
                                    <th style="width: 35%">产品供应商</th>
                                    <th style="width: 45%">产品名称</th>
                                    <th style="width: 20%">金额（单位/元）</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerApiVendorList??>
                                        <#list customerApiVendorList as customerApiVendor>
                                            <#if customerApiVendor.vendorName??>
                                            <tr>
                                            <#else >
                                            <tr class="danger">
                                            </#if>
                                            <td>${customerApiVendor.vendorName!'未知产品供应商'}</td>
                                            <td>${customerApiVendor.apiName!'未知产品'}</td>
                                            <td><#if customerApiVendor.totlePrice??>${customerApiVendor.totlePrice/100.0}<#else >0</#if></td>
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

    <script src="/js/table-managed.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var apiTypeId = $('#apiTypeId').val();
                var apiTypeName = $('#apiTypeName').val();
                fetch('/excel-finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail?companyName='+companyName+'&customerId='+customerId+'&apiTypeId='+apiTypeId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'-'+apiTypeName+'消费明细记录.xls';
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

            $('#customerListBalanceLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');
        });
    </script>
    </#if>

</@layout>
