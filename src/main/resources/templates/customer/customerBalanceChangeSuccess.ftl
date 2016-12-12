
<#include "layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        客户信息

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="#">客户管理</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">客户列表</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                <#--表单-->
                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i></div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <table class="table-bordered table-striped table-condensed cf">
                                <thead class="cf">
                                <tr>
                                    <th class="numeric">公司名称</th>
                                    <th class="numeric">账号类型</th>
                                    <th class="numeric">账户</th>
                                    <th class="numeric">余额</th>
                                    <th class="numeric">状态</th>

                                    <th class="numeric">部门</th>
                                    <th class="numeric">创建者</th>

                                    <th class="numeric">创建时间</th>
                                    <th class="numeric">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                        <tr>
                                            <td>${customer.name}</td>
                                            <td>${customer.customerType.name}</td>
                                            <td>${customer.authId}</td>
                                            <td>${customer.balance}</td>
                                            <td>${customer.customerStatus.name}</td>

                                            <td>${customer.dept.deptName}</td>
                                            <td>${customer.user.name}</td>

                                            <td>${customer.createTime}</td>
                                            <td class="numeric" style="text-align: center">

                                                <p>

                                                    <a href="/customerIp/addCustomerIpView/${customer.id}" class="btn blue" id="gritter-light">添加Ip</a>

                                                    <a href="/customerApi/addCustomerApiView/${customer.id}" class="btn black" id="gritter-light">添加Api</a><br/>

                                                    <a href="/customerIp/customerIpListAction/${customer.id}" class="btn blue" id="gritter-max">管理Ip</a>

                                                    <a href="/customerApi/customerApiListAction/${customer.id}" class="btn black" id="gritter-max">管理Api</a>

                                                </p>

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

    </#if>
<script>
    $(document).ready(function() {
        $('#customerManage').addClass('active');

        $('#customerList').addClass('active');

        $('#customerManageSelect').addClass('selected');

        $('#customerManageArrow').addClass('arrow open');
    });
</script>
</@layout>
