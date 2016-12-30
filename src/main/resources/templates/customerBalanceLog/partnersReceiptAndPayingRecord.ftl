
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

                <#--<li><a href="#">账号列表</a></li>-->

                <#--</ul>-->

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i>厦门掌讯</div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-bordered table-hover" id="sample_4">

                                <thead>

                                <tr>
                                    <th>金额（单位：元）</th>
                                    <th>创建时间</th>
                                    <th>类型</th>
                                    <th style="text-align: center;">备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td data-title="金额">23</td>
                                    <td data-title="创建时间">2016.12.23 23:45:45</td>
                                    <td data-title="类型">支出</td>
                                    <td data-title="备注">2016年给gmgc付款30000元</td>
                                </tr>
                                <tr>
                                    <td data-title="金额">78</td>
                                    <td data-title="创建时间">2016.12.23 23:45:45</td>
                                    <td data-title="类型">收入</td>
                                    <td data-title="备注">2016年gmgc给千眼付款30000元</td>
                                </tr>
                                <tr>
                                    <td data-title="金额">54</td>
                                    <td data-title="创建时间">2016.12.23 23:45:45</td>
                                    <td data-title="类型">支出</td>
                                    <td data-title="备注">2016年给gmgc付款30000元</td>
                                </tr>
                                <tr>
                                    <td data-title="金额">67</td>
                                    <td data-title="创建时间">2016.12.23 23:45:45</td>
                                    <td data-title="类型">收入</td>
                                    <td data-title="备注">2016年gmgc给千眼付款30000元</td>
                                </tr>
                                <tr>
                                    <td data-title="金额">90</td>
                                    <td data-title="创建时间">2016.12.23 23:45:45</td>
                                    <td data-title="类型">付款</td>
                                    <td data-title="备注">2016年gmgc给千眼付款30000元</td>
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

    <script>

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>

    <script>
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#partnersFinancialAccount').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

        });




    </script>

    <#elseif section = "footer">

    </#if>

</@layout>
