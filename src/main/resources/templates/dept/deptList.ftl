
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">
                    <h3 class="page-title">

                        部门信息

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="#">部门管理</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">部门信息</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i>Table</div>

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

                                    <th class="numeric">部门名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if deptList??>
                                    <#list deptList as dept>
                                    <tr align="center">
                                        <td>${dept.deptName}</td>
                                    </tr>
                                    </#list>
                                </#if>
                                </tbody>

                            </table>

                            <div class="row-fluid">

                                <div class="span6">

                                    <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 共 ${totlePage} 页</div>
                                </div>

                                <#if (totlePage>0)>

                                    <div class="span6" align="right">
                                        <div class="dataTables_paginate paging_bootstrap pagination">
                                            <ul>
                                                <#if (pageSize>1)>
                                                    <li class="next"><a href="/dept/findAllDept?pageSize=1"><span class="hidden-480">首页</span></a></li>
                                                    <li class="next"><a href="/dept/findAllDept?pageSize=${pageSize-1}"><span class="hidden-480">上一页</span></a></li>
                                                </#if>
                                                <#if (pageSize<totlePage)>
                                                    <li class="next"><a href="/dept/findAllDept?pageSize=${pageSize+1}"><span class="hidden-480">下一页</span></a></li>
                                                    <li class="next"><a href="/dept/findAllDept?pageSize=${totlePage}"><span class="hidden-480">尾页</span></a></li>
                                                </#if>
                                            </ul>
                                        </div>
                                    </div>
                                </#if>
                            </div>

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
        $('#deptManage').addClass('active');

        $('#deptList').addClass('active');

        $('#deptManageSelect').addClass('selected');

        $('#deptManageArrow').addClass('arrow open');
    });
</script>
</@layout>
