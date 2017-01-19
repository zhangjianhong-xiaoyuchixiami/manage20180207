
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

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">产品名称</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiVendorApiList??>
                                            <#list apiVendorApiList as apiVendor>
                                                <option <#if apiId?? && apiId==apiVendor.apiId>selected="selected"</#if> value="${apiVendor.apiId}">${apiVendor.apiName}</option>
                                            </#list>
                                        </#if>
                                    </select>

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

                            <div class="caption"><i class="icon-cogs"></i></div>

                            <@d.tools idName="exportExcel"></@d.tools>

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

                            <#--总消费-->
                                <div class="control-group pull-right" style="margin-bottom: -10px;">

                                    <label class="control-label">

                                        <a id="tipInfo" href="#form_modal3" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>总消费

                                        </a>

                                    </label>

                                    <div id="form_modal3" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">

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

                            <#--表字段总额-->
                                <div class="control-group pull-right" style="margin-bottom: -10px;">

                                    <label class="control-label">

                                        <a id="columnHistogram" href="#form_modal7" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>总消费

                                        </a>&nbsp;&nbsp;&nbsp;

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

                            <#--总额共计-->
                            <#--<div class="control-group pull-right">-->

                            <#--<label class="control-label">-->

                            <#--<a id="countResult" href="#form_modal6" data-toggle="modal">-->

                            <#--<i class="icon-reorder"></i>计算统计结果-->

                            <#--</a>-->

                            <#--</label>-->

                            <#--<div id="form_modal6" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel6" aria-hidden="true">-->

                            <#--<div class="modal-header">-->

                            <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>-->

                            <#--<h3 id="myModalLabel6">统计结果</h3>-->

                            <#--</div>-->

                            <#--<div class="modal-body">-->

                            <#--<table class="table table-striped table-bordered table-hover">-->
                            <#--<tr>-->
                            <#--<th width="50%">列名</th>-->
                            <#--<td width="50%">金额（单位：元）</td>-->
                            <#--</tr>-->
                            <#--<tr>-->
                            <#--<td>周消费总额</td>-->
                            <#--<td>2345</td>-->
                            <#--</tr>-->
                            <#--<tr>-->
                            <#--<td>月消费总额</td>-->
                            <#--<td>5678</td>-->
                            <#--</tr>-->
                            <#--<tr>-->
                            <#--<td>消费总额</td>-->
                            <#--<td>8907</td>-->
                            <#--</tr>-->
                            <#--<tr>-->
                            <#--<td>所剩余额</td>-->
                            <#--<td>456</td>-->
                            <#--</tr>-->

                            <#--</table>-->
                            <#--</div>-->

                            <#--</div>-->

                            <#--</div>-->

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 20%;">产品名称</th>
                                    <th style="text-align: center; width: 15%;">产品类型</th>
                                    <th style="text-align: center; width: 10%;">产品供应商</th>
                                    <th style="width: 11%;">周消费总额（单位：元）</th>
                                    <th style="width: 11%;">月消费总额（单位：元）</th>
                                    <th style="width: 11%;">消费总额（单位：元）</th>
                                    <th style="width: 11%;">所剩余额（单位：元）</th>
                                    <th style="text-align: center; width: 11%;"></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if apiFinanceList??>
                                        <#list apiFinanceList as apiFinance>
                                        <tr>
                                            <td data-title="产品名称">${apiFinance.apiName}</td>
                                            <td data-title="产品类型">${apiFinance.apiTypeName}</td>
                                            <td data-title="产品供应商">${apiFinance.vendorName}</td>
                                            <td data-title="周消费总额"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                            <td data-title="月消费总额"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="所剩余额"><#if apiFinance.balance??>${(apiFinance.balance/100.0)?c}<#else >0</#if></td>
                                            <td data-title="操作" style="text-align: center;" >
                                                <ul class="nav nav-tabs" style="margin-bottom: 0px; min-width: 94px; border-bottom: 0px solid #f4f4f4;">
                                                    <li class="dropdown" style="float: none;">
                                                        <a class="dropdown-toggle" style=" padding-bottom: 0px; padding-top: 0px;" data-toggle="dropdown" href="#">
                                                            操作 <span class="caret"></span>
                                                        </a>
                                                        <ul class="dropdown-menu" style="min-width: 105px; font-size: 13px;">
                                                            <li style="text-align: left"><a style="color: #08c;" href="#form_modal4" onclick="charge(1)" data-toggle="modal">充值</a></li>
                                                            <li style="text-align: left"><a style="color: #08c;" href="/api/find-all-api-record/detail?apiId=${apiFinance.apiId?c}&apiName=${apiFinance.apiName}&apiTypeName=${apiFinance.apiTypeName}&vendorName=${apiFinance.vendorName}">消费明细</a></li>
                                                        </ul>
                                                    </li>
                                                </ul>
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

                                        <#if msg??>

                                            <div class="alert alert-error show">

                                                <button class="close" data-dismiss="alert"></button>

                                            ${msg}

                                            </div>

                                        </#if>

                                        <div class="control-group">

                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                            <div id="amount-controls" class="controls"></div>

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

    <script type="text/javascript">
        $(document).ready(function () {
            function charge (apiId) {
                $("#amount-controls").empty();
                $("#remark-controls").empty();
                $("#amount-controls").append('<input type="text" id="amount" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span class="help-block">只能输入数字类型并且金额大于0</span>');
                $("#remark-controls").append('<textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea>');
                var amount = $("#amount").val();
                var remark = $("#remark").text();
                alert(apiId);
                alert(amount);
                alert(remark);
            }
//            $.ajax({
//                type: "post",
//                url: "/api/find-all-api-record/charge",
//                data: {"apiId":apiId,"amount":amount,"remark":remark},
//                dataType: "json",
//                success: function (result) {
//                    if(result.amount != null){
//                        $("#amount-controls").empty();
//                        $("#amount-controls").append('<input type="text" id="amount" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span class="help-line">'+result.amount+'</span><span class="help-block">只能输入数字类型并且金额大于0</span>');
//                    }else {
//                        window.location.href="/api/find-all-api-record"
//                    }
//                }
//            });


        });
    </script>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>

    <script type="text/javascript">

        $(document).ready(function() {

            $('#tipInfo').on("click", function () {

                $.ajax({
                    type: "post",
                    url: "/api/find-all-api-record/count-result",
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
                                type: 'column'
                            },
                            title: {
                                text: '产品消费统计结果',
                                margin: 15
                            },
                            xAxis: {
                                type: 'category',
                                labels: {
                                    rotation: -45,
                                    style: {
                                        fontSize: '13px',
                                        fontFamily: 'Verdana, sans-serif'
                                    }
                                }
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '消费总额（单位：元）'
                                }
                            },
                            legend: {
                                enabled: false
                            },
                            tooltip: {
                                pointFormat: '<b>{point.y:.1f} 元</b>'
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{
                                name: '产品',
                                data: jsondata,
                                dataLabels: {
                                    enabled: true,
                                    rotation: -90,
                                    color: '#FFFFFF',
                                    align: 'right',
                                    format: '{point.y:.1f}', // one decimal
                                    y: 10, // 10 pixels down from the top
                                    style: {
                                        fontSize: '13px',
                                        fontFamily: 'Verdana, sans-serif'
                                    }
                                }
                            }]
                        });
                    }
                });
            });
        });

    </script>

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

    <script type="text/javascript">

        $(document).ready(function () {

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



    </script>





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
