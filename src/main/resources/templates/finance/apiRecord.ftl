
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/api/find-all-api-record" method="get">

                        <div class="clearfix margin-bottom-20" style="margin-top: -18px;">

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">产品类型</label>

                                <div class="controls">

                                    <select id="apiTypeId" name="apiTypeId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiFinanceList??>
                                            <#list apiFinanceList as apiFinance>
                                                <option <#if apiTypeId?? && apiTypeId==apiFinance.apiTypeId>selected="selected"</#if> value="${apiFinance.apiTypeId}">${apiFinance.apiTypeName}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">产品供应商</label>

                                <div class="controls">

                                    <select id="vendorId" name="vendorId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiVendorApiList??>
                                            <#list apiVendorApiList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.vendorId>selected="selected"</#if> value="${apiVendor.vendorId}">${apiVendor.vendorName}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <#--<div class="control-group pull-left" style="margin-bottom: -20px;">-->

                                <#--<label class="control-label">产品名称</label>-->

                                <#--<div class="controls">-->

                                    <#--<select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1" style="width: 105px;">-->
                                        <#--<option value="">请选择...</option>-->
                                        <#--<#if apiVendorApiList??>-->
                                            <#--<#list apiVendorApiList as apiVendor>-->
                                                <#--<option <#if apiId?? && apiId==apiVendor.apiId>selected="selected"</#if> value="${apiVendor.apiId}">${apiVendor.apiName}</option>-->
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

                            <div class="caption"><i class="icon-user"></i></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_1_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                        <label><input type="checkbox" checked data-column="0">产品类型</label>

                                        <label><input type="checkbox" checked data-column="1">产品供应商</label>

                                        <label><input type="checkbox" data-column="2">产品名称</label>

                                        <label><input type="checkbox" checked data-column="3">消费总额</label>

                                        <label><input type="checkbox" checked data-column="4">所剩余额</label>

                                        <label><input type="checkbox" data-column="5">周消费总额</label>

                                        <label><input type="checkbox" checked data-column="6">月消费总额</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">周消费总额&yen;：<#if weekTotleAmount??><span>${(weekTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">月消费总额&yen;：<#if monthTotleAmount??><span>${(monthTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">消费总额&yen;：<#if consumeTotleAmount??><span>${(consumeTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">所剩余额&yen;：<#if totleBalance??><span>${(totleBalance/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                            <#--表字段总额-->
                                <div class="control-group pull-right" style="margin-bottom: -10px;">

                                    <label class="control-label">

                                        <a id="columnHistogram" href="#form_modal7" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>总消费

                                        </a>

                                    </label>

                                    <div id="form_modal7" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel7">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="columnHistogramContainer">

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">产品类型</th>
                                    <th style="text-align: center;">产品供应商</th>
                                    <th style="text-align: center;">产品名称</th>
                                    <th>消费总额（单位：元）</th>
                                    <th>所剩余额（单位：元）</th>
                                    <th>周消费总额（单位：元）</th>
                                    <th>月消费总额（单位：元）</th>
                                    <th style="text-align: center; width: 13%;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if apiFinanceList??>
                                        <#list apiFinanceList as apiFinance>
                                        <tr>
                                            <td data-title="产品类型">${apiFinance.apiTypeName}</td>
                                            <td data-title="产品供应商">${apiFinance.vendorName}</td>
                                            <td data-title="产品名称">${apiFinance.apiName}</td>
                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="所剩余额"><#if apiFinance.balance??>${(apiFinance.balance/100.0)?c}<#else >0</#if></td>
                                            <td data-title="周消费总额"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                            <td data-title="月消费总额"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                            <td data-title="操作" style="text-align: center;" >
                                                <a href="#form_modal4" onclick="charge(${apiFinance.apiId})" data-toggle="modal">充值</a>
                                                |
                                                <a href="/api/find-all-api-record/detail?apiId=${apiFinance.apiId?c}&apiName=${apiFinance.apiName}&apiTypeName=${apiFinance.apiTypeName}&vendorName=${apiFinance.vendorName}">消费明细</a>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                            <div id="form_modal4" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true">

                                <div class="modal-header">

                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                    <h3 id="myModalLabel4">请填写信息</h3>

                                </div>

                                <div class="modal-body">

                                    <form action="#" class="form-horizontal">

                                        <div class="control-group"></div>

                                        <div class="control-group"></div>

                                        <div id="error-alert"></div>

                                        <div id="apiId-controls" class="controls" style="display: none;"></div>

                                        <div class="control-group">

                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                            <div id="amount-controls" class="controls"></div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">充值日期<span class="required">*</span></label>

                                            <div class="controls">

                                                <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                                    <input id="chargeDate" name="chargeDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                                </div>

                                            </div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                            <div id="remark-controls" class="controls"></div>

                                        </div>

                                    </form>

                                </div>

                                <div class="modal-footer">

                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                    <button class="btn black btn-primary" id="btn-black-btn-primary" type="button">提交</button>

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

    <script src="/js/table-managed.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>

    <#--导出Excel-->
    <script type="text/javascript">

        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var apiTypeId = $('#apiTypeId').val();
                var vendorId = $('#vendorId').val();
                var apiId = $('#apiId').val();
                fetch('/excel-api-finance/find-all-api-record?apiTypeId='+apiTypeId+'&vendorId='+vendorId+'&apiId='+apiId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename ='产品消费账单.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });
        });
    </script>

    <#--级联-->
    <script type="text/javascript">
        $(document).ready(function() {

            $("#apiTypeId").change(function () {
                var param = $("#apiTypeId").val();
                if (param !=null) {
                    $.ajax({
                        url: '/api/find-api-vendor-by-api-type-id',
                        data: {"apiTypeId": param},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                $("#vendorId ").empty();
                                $("#vendorId").append("<option value=''>请选择...</option>");
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].vendorId;
                                    op.innerHTML=data[i].vendorName;
                                    $("#vendorId").append(op);
                                }
                            }
                        }
                    });
                }
            });

            $("#vendorId").change(function () {
                var param = $("#apiTypeId").val();
                var param1 = $("#vendorId").val();
                if (param !=null && param1 != null) {
                    $.ajax({
                        url: '/api/find-api-by-api-type-id',
                        data: {"apiTypeId": param, "vendorId": param1},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                $("#apiId ").empty();
                                $("#apiId").append("<option value=''>请选择...</option>");
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i].apiId;
                                    op.innerHTML=data[i].apiName;
                                    $("#apiId").append(op);
                                }
                            }
                        }
                    });
                }
            });
        });


    </script>

    <#--总消费柱状图-->
    <script type="text/javascript">
        $(document).ready(function () {

            $("#columnHistogram").on("click",function () {
                $.ajax({
                    type: 'post',
                    url: '/api/find-all-api-record/bar-chart',
                    dataType: 'json',
                    success: function (result) {
                        var json = result;
                        var chart = new Highcharts.Chart({
                            chart: {
                                renderTo: 'columnHistogramContainer',
                                type: 'column'
                            },
                            title: {
                                text: ''
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            xAxis: {
                                categories: json.xList
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '消费总额（单位：元）'
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f} 元</b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            },
                            series: json.yList
                        });
                    }
                });
            });
        });
    </script>

    <#--充值-->
    <script type="text/javascript">

        function charge(apiId) {
            $("#apiId-controls").empty();
            $("#amount-controls").empty();
            $("#remark-controls").empty();
            $("#error-alert").empty();
            var op=document.createElement("input");
            op.value=apiId;
            op.type="text";
            op.id="apiIdCharge";
            op.name="apiIdCharge";
            $("#apiId-controls").append(op);
            $("#amount-controls").append('<input type="text" id="amount" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span id="amount-message"></span><span class="help-block">说明：只能输入数字类型并且金额大于0</span>');
            $("#remark-controls").append('<textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea><span class="help-block" style="font-size: 12px;">说明：只能输入255个字符</span>');

        }

        $("#btn-black-btn-primary").on("click",function () {
            var apiIdCharge=$("#apiIdCharge").val();
            var amount=$("#amount").val();
            var chargeDate=$("#chargeDate").val();
            var remark=$("#remark").attr("value");
            $.ajax({
                type: "post",
                url: "/api/find-all-api-record/charge",
                data: {"apiIdCharge":apiIdCharge,"amount":amount,"chargeDate":chargeDate,"remark":remark},
                dataType: "json",
                success: function (result) {
                    if(result.amountMessage != null){
                        $("#amount-message").empty();
                        $("#amount-message").append('<span class="help-line"><font color="red">'+result.amountMessage+'</font></span>');
                    }
                    if(result.successMessage != null){
                        window.location.href="/api/find-all-api-record"
                    }
                    if(result.errorMessage != null) {
                        $("#error-alert").empty();
                        $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                    }
                }
            });
        });
    </script>

    <#--左侧导航-->
    <script type="text/javascript">
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#apiRecordLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

        });
    </script>

    </#if>

</@layout>
