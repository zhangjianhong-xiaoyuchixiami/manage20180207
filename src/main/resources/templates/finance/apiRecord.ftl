
<#include "../customer/layout.ftl">

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                    <#--客户信息-->

                    </h3>

                <#--<ul class="breadcrumb">-->

                <#--<li>-->

                <#--<i class="icon-home"></i>-->

                <#--<a href="/view/successUrl">首页</a>-->

                <#--<i class="icon-angle-right"></i>-->

                <#--</li>-->

                <#--<li>-->

                <#--<a href="#">财务管理</a>-->

                <#--<i class="icon-angle-right"></i>-->

                <#--</li>-->

                <#--<li><a href="#">财务报表</a></li>-->

                <#--</ul>-->

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/finance/find-all-api-record" method="get">

                        <div class="clearfix margin-bottom-20">

                            <div class="control-group pull-left">

                                <label class="control-label">Api类型</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">二要素</option>
                                        <option value="">三要素</option>
                                        <option value="">四要素</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">Api供应商</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">移动</option>
                                        <option value="">联通</option>
                                        <option value="">电信</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">Api</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">sdsds</option>
                                        <option value="">gfdgfd</option>
                                        <option value="">gdfgfd</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">&nbsp;&nbsp;</label>

                            </div>

                            <div class="control-group pull-left">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="input-append" >

                                    <button class="btn black" type="submit">搜索</button>

                                </div>

                            </div>

                        </div>

                    </form>

                <#--表格-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i></div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left">

                                    <label class="control-label">周消费总额&yen;123元&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">月消费总额&yen;123元&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">消费总额&yen;123元&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">所剩余额&yen;123元&nbsp;&nbsp;&nbsp;</label>

                                </div>

                            <#--消费总额-->
                                <div class="control-group pull-right">

                                    <label class="control-label">

                                        <a id="tipInfo" href="#form_modal3" data-toggle="modal">

                                            <img src="/image/t04.png" alt="" />消费总额

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
                                <div class="control-group pull-right">

                                    <label class="control-label">

                                        <a id="columnHistogram" href="#form_modal7" data-toggle="modal">

                                            <img src="/image/t04.png" alt="" />表字段统计

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

                            <#--总额共计-->
                                <div class="control-group pull-right">

                                    <label class="control-label">

                                        <a id="countResult" href="#form_modal6" data-toggle="modal">

                                            <i class="icon-reorder"></i>计算统计结果

                                        </a>

                                    </label>

                                    <div id="form_modal6" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel6" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel6">统计结果</h3>

                                        </div>

                                        <div class="modal-body">

                                            <table class="table table-striped table-bordered table-hover">
                                                <tr>
                                                    <th width="50%">列名</th>
                                                    <td width="50%">金额（单位：元）</td>
                                                </tr>
                                                <tr>
                                                    <td>周消费总额</td>
                                                    <td>2345</td>
                                                </tr>
                                                <tr>
                                                    <td>月消费总额</td>
                                                    <td>5678</td>
                                                </tr>
                                                <tr>
                                                    <td>消费总额</td>
                                                    <td>8907</td>
                                                </tr>
                                                <tr>
                                                    <td>所剩余额</td>
                                                    <td>456</td>
                                                </tr>

                                            </table>
                                        </div>

                                    </div>

                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_1">

                                <thead>
                                <tr>
                                    <th style="width:20%; text-align: center;">Api</th>
                                    <th>周消费总额（单位：元）</th>
                                    <th>月消费总额（单位：元）</th>
                                    <th>消费总额（单位：元）</th>
                                    <th>所剩余额（单位：元）</th>
                                    <th style="width:15%; text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td data-title="Api">asas</td>
                                    <td data-title="周消费总额">234</td>
                                    <td data-title="月消费总额">453</td>
                                    <td data-title="消费总额">523</td>
                                    <td data-title="所剩余额">890</td>
                                    <td data-title="操作" style="text-align: center;" >
                                        <a href="#form_modal4"  data-toggle="modal">充值</a>

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
                                                    <div class="control-group" style="display: none;">

                                                        <label class="control-label">公司id<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                        </div>

                                                    </div>

                                                    <div class="control-group">

                                                        <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                            <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                        </div>

                                                    </div>

                                                </form>

                                            </div>

                                            <div class="modal-footer">

                                                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                            </div>

                                        </div>
                                        |
                                        <a href="/finance/find-all-api-record/detail">消费明细</a>
                                    </td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td data-title="Api">asas</td>
                                    <td data-title="周消费总额">234</td>
                                    <td data-title="月消费总额">453</td>
                                    <td data-title="消费总额">523</td>
                                    <td data-title="所剩余额">890</td>
                                    <td data-title="操作" style="text-align: center;" >
                                        <a href="#form_modal4"  data-toggle="modal">充值</a>

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
                                                    <div class="control-group" style="display: none;">

                                                        <label class="control-label">公司id<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                        </div>

                                                    </div>

                                                    <div class="control-group">

                                                        <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                            <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                        </div>

                                                    </div>

                                                </form>

                                            </div>

                                            <div class="modal-footer">

                                                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                            </div>

                                        </div>
                                        |
                                        <a href="/customer-balance/find-all-api-record/detail">消费明细</a>
                                    </td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td data-title="Api">asas</td>
                                    <td data-title="周消费总额">234</td>
                                    <td data-title="月消费总额">453</td>
                                    <td data-title="消费总额">523</td>
                                    <td data-title="所剩余额">890</td>
                                    <td data-title="操作" style="text-align: center;" >
                                        <a href="#form_modal4"  data-toggle="modal">充值</a>

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
                                                    <div class="control-group" style="display: none;">

                                                        <label class="control-label">公司id<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                        </div>

                                                    </div>

                                                    <div class="control-group">

                                                        <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                            <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                        </div>

                                                    </div>

                                                </form>

                                            </div>

                                            <div class="modal-footer">

                                                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                            </div>

                                        </div>
                                        |
                                        <a href="/customer-balance/find-all-api-record/detail">消费明细</a>
                                    </td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td data-title="Api">asas</td>
                                    <td data-title="周消费总额">234</td>
                                    <td data-title="月消费总额">453</td>
                                    <td data-title="消费总额">523</td>
                                    <td data-title="所剩余额">890</td>
                                    <td data-title="操作" style="text-align: center;" >
                                        <a href="#form_modal4"  data-toggle="modal">充值</a>

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
                                                    <div class="control-group" style="display: none;">

                                                        <label class="control-label">公司id<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="authId" name="authId" class="m-wrap medium">

                                                        </div>

                                                    </div>

                                                    <div class="control-group">

                                                        <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                                        <div class="controls">

                                                            <input type="text" id="amount" name="amount" <#if amount??>value="${amount}"</#if> placeholder="（单位/元）" class="m-wrap medium">

                                                            <span class="help-inline" id="amountMsg"><#if CustomerMessageAmount??><font color="red">${CustomerMessageAmount}</font></#if></span>

                                                            <span class="help-block">只能输入数字类型并且金额大于0</span>

                                                        </div>

                                                    </div>

                                                </form>

                                            </div>

                                            <div class="modal-footer">

                                                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                                <button class="btn green btn-primary" data-dismiss="modal">提交</button>

                                            </div>

                                        </div>
                                        |
                                        <a href="/customer-balance/find-all-api-record/detail">消费明细</a>
                                    </td>
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

    <script type="text/javascript">

        $(document).ready(function() {

            $('#tipInfo').on("click", function () {

                $.ajax({
                    type: "post",
                    url: "/finance/find-all-api-record/count-result",
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
                                text: 'Api消费统计结果',
                                margin: 15
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
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
                                        ,
                                        connectorColor: 'silver'
                                    }
                                }
                            },
                            series: [{
                                name: 'Api',
                                data: jsondata
                            }]
                        });
                    }
                });
            });
        });

    </script>

    <script type="text/javascript">

        $(document).ready(function () {

            $(function () {

                var chart = new Highcharts.Chart({
                    chart: {
                        renderTo: 'columnHistogramContainer',
                        type: 'column'
                    },
                    title: {
                        text: '表字段总额统计'
                    },
                    exporting: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    xAxis: {
                        categories: ['一要素','二要素', '三要素', '四要素', '五要素', '六要素', '七要素', '八要素', '就要素'],
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '总额（元）'
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
                    series: [{
                        name: '周消费总额',
                        data: [800, 456, 106, 6789, 144, 176, 234, 679, 4563]

                    }, {
                        name: '月消费总额',
                        data: [8360, 7880, 9850, 9340, 1060, 8450, 1050, 1043, 9120]

                    }, {
                        name: '消费总额',
                        data: [4890, 3880, 3930, 4140, 4700, 4830, 5900, 5960, 5240]

                    }, {
                        name: '所剩余额',
                        data: [424, 332, 345, 397, 526, 755, 574, 604, 476]

                    }]
                });
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
