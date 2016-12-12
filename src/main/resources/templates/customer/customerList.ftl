
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

                <#--搜索框-->
                    <#if deptNoList??>
                        <form action="/customer/findAllCustomerByDeptNo" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search AuthId or Name">

                                <button class="btn blue" type="submit">搜索</button>

                            </div>

                        </form>
                    <#else >
                        <form action="/customer/findAllCustomer" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search AuthId or Name">

                                <button class="btn blue" type="submit">搜索</button>

                            </div>

                        </form>
                    </#if>
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

                                    <#if customerList??>
                                        <#list customerList as customer>
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
                                        </#list>
                                    </#if>

                                </tbody>

                            </table>
                            <#if deptNoList??>

                                <#if (count>0)>
                                    <div class="row-fluid">

                                        <div class="span6">

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>

                                        </div>

                                        <#if (totlePage>0)>
                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=1<#if content??>&content=${content}</#if>"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=${pageSize-1}<#if content??>&content=${content}</#if>"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=${pageSize+1}<#if content??>&content=${content}</#if>"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomerByDeptNo?pageSize=${totlePage}<#if content??>&content=${content}</#if>"><span class="hidden-480">尾页</span></a></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </#if>

                                    </div>
                                </#if>
                            <#else >
                                <#if (count>0)>
                                    <div class="row-fluid">

                                        <div class="span6">

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>

                                        </div>

                                        <#if (totlePage>0)>
                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=1<#if content??>&content=${content}</#if>"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=${pageSize-1}<#if content??>&content=${content}</#if>"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=${pageSize+1}<#if content??>&content=${content}</#if>"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/customer/findAllCustomer?pageSize=${totlePage}<#if content??>&content=${content}</#if>"><span class="hidden-480">尾页</span></a></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </#if>

                                    </div>
                                </#if>
                            </#if>

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
