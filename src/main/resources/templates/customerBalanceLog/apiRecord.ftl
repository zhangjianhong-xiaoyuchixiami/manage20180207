
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

                    <form action="/customerBalance/findAllCustomerBalanceLogByCustomerId" method="get">

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

                            <div class="control-group pull-right">

                                <div id="container">

                                </div>

                            </div>



                        </div>

                    </form>


                <#--表单-->
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

                                    <label class="control-label">周消费总额共计&yen;：<#if totleAmount??><span>${totleAmount}元</span><#else ><span>0元</span></#if></label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">月消费总额共计&yen;：0元</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">消费总额共计&yen;：0元</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left">

                                    <label class="control-label">所剩余额共计&yen;：0元</label>

                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover" id="sample_1">

                                <thead>

                                <tr>

                                    <th style="width:20%;">Api</th>

                                    <th>周消费总额（单位：元）</th>

                                    <th class="hidden-480">月消费总额（单位：元）</th>

                                    <th class="hidden-480">消费总额（单位：元）</th>

                                    <th class="hidden-480">所剩余额（单位：元）</th>

                                    <th style="width:15%;">操作</th>

                                </tr>

                                </thead>

                                <tbody>

                                <tr class="odd gradeX">
                                    <td>asas</td>
                                    <td>234</td>
                                    <td class="hidden-480">453</td>
                                    <td class="hidden-480">665</td>
                                    <td class="center hidden-480">890</td>
                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">
                                    <td>fgrg</td>
                                    <td>43543</td>
                                    <td class="hidden-480">545</td>
                                    <td class="hidden-480">4543</td>
                                    <td class="center hidden-480">4554</td>
                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td>vfg</td>
                                    <td>654</td>
                                    <td class="hidden-480">767</td>
                                    <td class="hidden-480">66675675</td>
                                    <td class="center hidden-480">65756</td>
                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>
                                </tr>

                                <tr class="odd gradeX">

                                    <td>dsfdf</td>

                                    <td>234</td>

                                    <td class="hidden-480">323</td>

                                    <td class="hidden-480">4234</td>

                                    <td class="center hidden-480">3242</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>ggg</td>

                                    <td>76</td>

                                    <td class="hidden-480">6765</td>

                                    <td class="hidden-480">767</td>

                                    <td class="center hidden-480">676</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>
                                <tr class="odd gradeX">

                                    <td>asas</td>

                                    <td>234</td>

                                    <td class="hidden-480">453</td>

                                    <td class="hidden-480">665</td>

                                    <td class="center hidden-480">890</td>

                                    <td style="text-align: center;" ><a href="/customerBalance/apiRecharageView">充值</a>|<a href="/customerBalance/findApiDetailRecord">消费明细</a></td>

                                </tr>

                                </tbody>

                            </table>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript">

        $(function () {
            // Radialize the colors
            Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
                return {
                    radialGradient: {
                        cx: 0.5,
                        cy: 0.3,
                        r: 0.7
                    },
                    stops: [
                        [0, color],
                        [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
                    ]
                };
            });

            // Build the chart
            Highcharts.chart('container', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    height: 200,
                    width: 400,
                    reflow: true,
                    size: 200,
                    type: 'pie'
                },
                title: {
                    text: 'Api消费统计结果',
                    margin: 3
                },
                exporting :{
                    enabled:false
                },
                credits:{
                    enabled:false
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
                            },
                            connectorColor: 'silver'
                        }
                    }
                },
                series: [{
                    name: 'Api',
                    data: [
                        { name: '一要素', y: 56.33 },
                        {name:  '二要素',  y: 24.03},
                        { name: '三要素', y: 10.38 },
                        { name: '四要素', y: 4.77 },
                        { name: '五要素', y: 0.91 },
                        { name: '六要素', y: 0.2 }
                    ]
                }]
            });
        });
    </script>

    <script>

        jQuery(document).ready(function() {
            TableManaged.init();
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

    <#elseif section = "footer">

    </#if>

</@layout>
