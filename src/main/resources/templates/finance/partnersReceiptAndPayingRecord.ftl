
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

                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i>厦门掌讯</div>

                            <@d.tools idName="exportExcelByDeptId"></@d.tools>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_4">

                                <thead>

                                <tr>
                                    <th>金额（单位：元）</th>
                                    <th>创建时间</th>
                                    <th>类型</th>
                                    <th style="text-align: center;">备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td data-title="金额">23</td>
                                    <td data-title="创建时间">2016.12.23 23:45:45</td>
                                    <td data-title="类型">支出</td>
                                    <td data-title="备注">2016年给gmgc付款30000元</td>
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

    </#if>

</@layout>
