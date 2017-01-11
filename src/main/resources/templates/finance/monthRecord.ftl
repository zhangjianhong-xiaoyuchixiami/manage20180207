
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

                    <form action="/finance/find-all-customer/find-month-record-by-customer-id" method="get">

                        <div class="clearfix margin-bottom-20">

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">客户账号Id</label>

                                <div class="controls">

                                    <input type="text" id="customerId" name="customerId" value="${customerId}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px; display: none">

                                <label class="control-label">公司名称</label>

                                <div class="controls">

                                    <input type="text" id="companyName" name="companyName" value="${companyName}" class="m-wrap medium">

                                </div>
                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">类型</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if typeIdArray??><#list typeIdArray as typeId><#if typeId==1>checked="checked"</#if></#list></#if> id="typeId" name="typeId" value="1">充值

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if typeIdArray??><#list typeIdArray as typeId><#if typeId==2>checked="checked"</#if></#list></#if> id="typeId" name="typeId" value="2">消费

                                    </label>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">年</label>

                                <div class="controls">

                                    <select id="years" name="years" class="medium m-wrap1" tabindex="1">
                                        <option value="">请选择...</option>
                                        <#if yearList??>
                                            <#list yearList as year>
                                                <option <#if years?? && year==years>selected="selected"</#if> value="${year?c}">${year?c}年</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">月</label>

                                <div class="controls">

                                    <select id="months" name="months" class="medium m-wrap1" tabindex="1">
                                        <option value="">请选择...</option>
                                        <#if monthList??>
                                            <#list monthList as month>
                                                <option <#if months?? && month==months>selected="selected"</#if> value="${month?c}">第${month?c}月</option>
                                            </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="input-append" >

                                    <button class="btn black" type="submit">搜索</button>

                                </div>

                            </div>

                        </div>

                    </form>


                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></i><#if companyName??>${companyName}</#if></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount/100.0}元</span><#else ><span>0元</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_8">
                                <thead>
                                <tr>
                                    <th style="width: 25%;">年-月</th>
                                    <th style="width: 15%;">金额（单位/元）</th>
                                    <th style="width: 20%;">开始时间</th>
                                    <th style="width: 20%;">结束时间</th>
                                    <th style="width: 20%;">类型</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if weekMonthAmountList??>
                                        <#list weekMonthAmountList as weekMonthAmount>
                                        <tr>
                                            <td><#if weekMonthAmount.years??>${weekMonthAmount.years?c}年</#if><#if weekMonthAmount.months??>第${weekMonthAmount.months}月</#if></td>
                                            <td><#if weekMonthAmount.totleAmount??>${weekMonthAmount.totleAmount/100.0}<#else >0</#if></td>
                                            <td><#if weekMonthAmount.beginTime??>${weekMonthAmount.beginTime?date}</#if></td>
                                            <td><#if weekMonthAmount.endTime??>${weekMonthAmount.endTime?date}</#if></td>
                                            <#if weekMonthAmount.tableId==1>
                                                <td>充值</td>
                                            <#else >
                                                <td>消费</td>
                                            </#if>
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
            $("#years").change(function () {
                var param = $("#years").val();
                var param1 = $("#customerId").val();
                var param2 =[];//定义一个数组
                $('input[name="typeId"]:checked').each(function(){
                    param2.push($.trim($(this).val()));
                });
                if (param !=null) {
                    $.ajax({
                        url: '/finance/find-company-customer-month-uplink-months-by-customer-id',
                        data: {"years": param, "customerId": param1, "typeId": param2},
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if(data != null){
                                $("#months ").empty();
                                $("#months").append("<option value=''>请选择...</option>");
                                for (var i=0; i<data.length; i++){
                                    var op=document.createElement("option");
                                    op.value=data[i];
                                    op.innerHTML='第'+data[i]+'月';
                                    $("#months").append(op);
                                }
                            }
                        }
                    });
                }
            });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var companyName = $('#companyName').val();
                var customerId = $('#customerId').val();
                var years = $('#years').val();
                var months = $('#months').val();
                var typeId =[];//定义一个数组
                $('input[name="typeId"]:checked').each(function(){
                    typeId.push($.trim($(this).val()));
                });
                fetch('/excel-finance/find-all-customer/find-month-record-by-customer-id?companyName='+companyName+'&typeId='+typeId+'&customerId='+customerId+'&years='+years+'&months='+months).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename = companyName+'月历史记录.xls';
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
