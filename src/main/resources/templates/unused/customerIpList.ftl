
<#include "../publicPart/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                       客户Ip

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

                        <li><a href="#">Ip列表</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box grey">

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

                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th style="text-align: center">起始段</th>
                                    <th style="text-align: center">结束段</th>
                                    <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if customerIpList??>
                                        <#list customerIpList as customerIp>
                                        <tr align="center">
                                            <td>${customerIp.beginIpRaw}</td>
                                            <td>${customerIp.endIpRaw}</td>
                                            <td style="text-align: center">
                                                <p>
                                                    <a href="/customerIp/deleteIp/${customerIp.id}/${customerIp.customerId}" class="btn red" id="gritter-light"><i class="icon-trash icon-white">Delete</i></a>
                                                </p>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>

                            </table>

                            <#if (count>0)>

                                <div class="row-fluid">

                                    <div class="span6">

                                        <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>
                                    </div>

                                    <#if (totlePage>1)>
                                        <div class="span6" align="right">
                                            <div class="dataTables_paginate paging_bootstrap pagination">
                                                <ul>
                                                    <#if (pageSize>1)>
                                                        <li class="next"><a href="/customerIp/customerIpListAction/${customerId}?pageSize=1"><span class="hidden-480">首页</span></a></li>
                                                        <li class="next"><a href="/customerIp/customerIpListAction/${customerId}?pageSize=${pageSize-1}"><span class="hidden-480">上一页</span></a></li>
                                                    </#if>
                                                    <#if (pageSize<totlePage)>
                                                        <li class="next"><a href="/customerIp/customerIpListAction/${customerId}?pageSize=${pageSize+1}"><span class="hidden-480">下一页</span></a></li>
                                                        <li class="next"><a href="/customerIp/customerIpListAction/${customerId}?pageSize=${pageSize}"><span class="hidden-480">尾页</span></a></li>
                                                    </#if>
                                                </ul>
                                            </div>
                                        </div>
                                    </#if>
                                </div>

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