
<#include "../publicPart/layout.ftl">

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

                    <form action="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id" class="customer_consume" method="get">

                        <div class="clearfix margin-bottom-20 head-search-clearfix-top">

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" value="${customerId?c}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom head-search-display">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" value="${companyName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="pull-left head-search-bottom">

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select id="apiTypeId" name="apiTypeId" class="medium m-wrap1" tabindex="1" >
                                        <option value="">请选择...</option>
                                        <#if customerApiTypes??>
                                            <#list customerApiTypes as apiType>
                                                <option <#if apiTypeId?? && apiType.id==apiTypeId>selected="selected"</#if> value="${apiType.id?c}">${apiType.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>

                            <@shiro.hasPermission name="customer:findAllCustomer">

                                <div class="pull-left head-search-bottom">

                                    <label class="control-label">产品供应商</label>

                                    <div class="controls">
                                        <select id="apiVendorId" name="apiVendorId" class="medium m-wrap1" tabindex="1">
                                            <option value="">请选择...</option>
                                            <#if customerApiVendors??>
                                                <#list customerApiVendors as vendor>
                                                    <option <#if apiVendorId?? && vendor.id==apiVendorId>selected="selected"</#if> value="${vendor.id?c}">${vendor.name!''}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>

                                </div>

                            </@shiro.hasPermission>

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

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i><#if companyName??>${companyName}</#if></div>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <@d.tools idName="exportExcel"></@d.tools>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                <@d.tools idName="exportExcelByDeptId"></@d.tools>
                            </@shiro.hasPermission>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="pull-left table-top-bottom">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${(-totleAmount/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-hover table-bordered table-condensed" id="sample_5">
                                    <thead>
                                    <tr>
                                        <th>产品类型</th>
                                        <@shiro.hasPermission name="customer:findAllCustomer">
                                            <th>产品供应商</th>
                                        </@shiro.hasPermission>
                                        <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                            <th style="display: none">产品供应商</th>
                                        </@shiro.hasPermission>
                                        <th>金额（单位/元）</th>
                                        <th style="text-align: center; width: 15%">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if customerApiTypeList??>
                                            <#list customerApiTypeList as customerApiType>
                                            <tr class="odd gradeX">
                                                <td data-title="产品类型">${customerApiType.apiTypeName}</td>
                                                <@shiro.hasPermission name="customer:findAllCustomer">
                                                    <td data-title="产品供应商"><a id="tipInfo" href="#form_modal3" onclick="vendorConsume(${customerApiType.apiTypeId?c})" data-toggle="modal"><#if customerApiType.customerApiVendors??><#list customerApiType.customerApiVendors as vendor><#if vendor.vendorName??>${vendor.vendorName}，&nbsp;</#if></#list></#if></a></td>
                                                </@shiro.hasPermission>
                                                <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                                    <td data-title="产品供应商" style="display: none"><a id="tipInfo" href="#form_modal3" onclick="vendorConsume(${customerApiType.apiTypeId?c})" data-toggle="modal"><#if customerApiType.customerApiVendors??><#list customerApiType.customerApiVendors as vendor><#if vendor.vendorName??>${vendor.vendorName}，&nbsp;</#if></#list></#if></a></td>
                                                </@shiro.hasPermission>
                                                <td data-title="金额（单位/元）"><#if customerApiType.totlePrice??>${(-customerApiType.totlePrice/100.0)?c}<#else >0</#if></td>
                                                <td data-title="操作" style="text-align: center;"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail?customerId=${customerId?c}&apiTypeId=${customerApiType.apiTypeId?c}&reasonId=-1<#if companyName??>&companyName=${companyName}</#if>&apiTypeName=${customerApiType.apiTypeName}">明细</a></td>
                                            </tr>
                                            </#list>
                                        </#if>
                                    </tbody>
                                </table>
                            </div>
                            <div id="form_modal3" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">

                                <div class="modal-header">

                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                    <h3 id="myModalLabel3">&nbsp;</h3>

                                </div>
                                <div class="modal-body">

                                    <div id="container">

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

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/myjs/customer-api-consume.js"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {

            CustomerApiConsume.init();

            CustomerLeftBar.init();

        });

        function vendorConsume(apiTypeId){
            var param = apiTypeId;
            var param1 = $("#customerId").val();
            $.ajax({
                type: "post",
                url: "/finance/find-all-vendor-record/count-result",
                data: {"apiTypeId": param,"customerId": param1},
                dataType: 'json',
                success: function (result) {
                    var json = result;
                    var jsondata = [];
                    for (var i in json) {
                        jsondata.push([i, json[i]]);
                    }

                    var chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container',
                            type: 'pie'
                        },
                        title: {
                            text: '各供应商消费统计',
                            margin: 15
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                    style: {
                                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                    }
                                }
                            }
                        },
                        exporting: {
                            enabled: false
                        },
                        credits: {
                            enabled: false
                        },
                        series: [{
                            name: '供应商',
                            data: jsondata
                        }]
                    });
                }
            });
        }

        $(document).ready(function() {
            $('#exportExcelByDeptId').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var apiTypeId = $('#apiTypeId').val();
                fetch('/excel-finance/find-all-customer-by-dept/find-all-customer-api-consume-record-by-customer-id?companyName='+companyName+'&customerId='+customerId+'&apiTypeId='+apiTypeId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'消费记录.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });

            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var apiTypeId = $('#apiTypeId').val();
                var apiVendorId = $('#apiVendorId').val();
                fetch('/excel-finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id?companyName='+companyName+'&customerId='+customerId+'&apiTypeId='+apiTypeId+'&apiVendorId='+apiVendorId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'消费记录.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });

        });

    </script>

    </#if>

</@layout>
