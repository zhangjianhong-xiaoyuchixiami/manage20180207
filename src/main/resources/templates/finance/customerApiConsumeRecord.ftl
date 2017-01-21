
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

                    <form action="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id" method="get">

                        <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

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

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select id="apiTypeId" name="apiTypeId" class="medium m-wrap1" tabindex="1" >
                                        <option value="">请选择...</option>
                                        <#if customerApiTypes??>
                                            <#list customerApiTypes as customerApiType>
                                                <option <#if apiTypeId?? && customerApiType.apiTypeId==apiTypeId>selected="selected"</#if> value="${customerApiType.apiTypeId?c}">${customerApiType.apiTypeName}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>

                            <#--<div class="control-group pull-left" style="margin-bottom: -20px;">-->

                                <#--<label class="control-label">产品供应商</label>-->

                                <#--<div class="controls">-->
                                    <#--<select id="apiVendorId" name="apiVendorId" class="medium m-wrap1" tabindex="1">-->
                                        <#--<option value="">请选择...</option>-->
                                        <#--<#if customerApiVendors??>-->
                                            <#--<#list customerApiVendors as vendor>-->

                                                <#--<option <#if apiVendorId?? &&vendor.vendorId?? && vendor.vendorId==apiVendorId>selected="selected"</#if> value="<#if vendor.vendorId??>${vendor.vendorId?c}</#if>"><#if vendor.vendorName??>${vendor.vendorName}</#if></option>-->

                                            <#--</#list>-->
                                        <#--</#if>-->
                                    <#--</select>-->
                                <#--</div>-->

                            <#--</div>-->

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

                            <div class="caption"><i class="icon-user"></i><#if companyName??>${companyName}</#if></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${(-totleAmount/100.0)?c}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_5">
                                <thead>
                                <tr>
                                    <th>产品类型</th>
                                    <#--<th>产品供应商</th>-->
                                    <th style="width: 20%">金额（单位/元）</th>
                                    <th style="text-align: center; width: 15%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerApiTypeList??>
                                        <#list customerApiTypeList as customerApiType>
                                        <tr>
                                            <td data-title="产品类型">${customerApiType.apiTypeName}</td>
                                            <#--<td data-title="产品供应商"><#if customerApiType.customerApiVendors??><#list customerApiType.customerApiVendors as vendor><#if vendor.vendorName??>${vendor.vendorName}，&nbsp;</#if></#list></#if></td>-->
                                            <td data-title="金额（单位/元）"><#if customerApiType.totlePrice??>${(-customerApiType.totlePrice/100.0)?c}<#else >0</#if></td>
                                            <td data-title="操作" style="text-align: center;"><a href="/finance/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail?customerId=${customerId?c}&apiTypeId=${customerApiType.apiTypeId?c}&reasonId=-1<#if companyName??>&companyName=${companyName}</#if>&apiTypeName=${customerApiType.apiTypeName}">明细</a></td>
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

    <script src="/js/myjs/json2.js" type="text/javascript"></script>

    <script src="/js/myjs/customerleftbar.js"></script>

    <script type="text/javascript">
        jQuery(document).ready(function() {

            TableManaged.init();

            /*下拉框*/
            $("#apiTypeId").change(function () {
                var param = $("#apiTypeId").val();
                var param1 = $("#customerId").val();
                if (param !=null) {
                    $.ajax({
                        url: '/finance/find-api-vendor-by-api-type-id',
                        data: {"apiTypeId": param, "customerId": param1},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                $("#apiVendorId ").empty();
                                $("#apiVendorId").append("<option value=''>请选择...</option>");
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].vendorId;
                                    op.innerHTML=data[i].vendorName;
                                    $("#apiVendorId").append(op);
                                }
                            }
                        }
                    });
                }
            });
        });
    </script>

    <#--导出Excel-->
    <script type="text/javascript">
        $(document).ready(function() {
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
