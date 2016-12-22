
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

                        <#--<li><a href="#">账号详细信息</a></li>-->

                    <#--</ul>-->

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">


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

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 10%">账号类型</th>
                                    <th style="text-align: center; width: 15%">公司名称</th>
                                    <th style="text-align: center; width: 15%">账号</th>
                                    <th style="text-align: center; width: 20%">密码</th>
                                    <th style="text-align: center; width: 10%">余额（单位/元）</th>
                                    <th style="text-align: center; width: 15%">创建时间</th>
                                    <th style="text-align: center; width: 10%">部门</th>
                                    <th style="text-align: center; width: 5%">状态</th>
                                </tr>
                                </thead>
                                <#if customer??>
                                <tbody>
                                <tr>
                                    <td>${customer.customerType.name}</td>
                                    <td>${customer.company.name}</td>
                                    <td>${customer.authId}</td>
                                    <td>${customer.authPass}</td>
                                    <td>${customer.balance/100.0}</td>
                                    <td>${customer.createTime}</td>
                                    <td>${customer.dept.deptName}</td>
                                    <td>${customer.customerStatus.name}</td>
                                </tr>
                                </tbody>
                                </#if>

                            </table>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    </#if>
<script>
    $(document).ready(function() {
        $('#customerBalance').addClass('active');

        $('#customerListBalanceLog').addClass('active');

        $('#customerBalanceSelect').addClass('selected');

        $('#customerBalanceArrow').addClass('arrow open');
    });
</script>
</@layout>
