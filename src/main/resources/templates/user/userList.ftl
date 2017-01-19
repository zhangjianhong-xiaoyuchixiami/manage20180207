
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                    <#if deptIdList??>

                        <form action="/user/findAllUserCommon" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search">

                                <button class="btn black" type="submit">搜索</button>

                            </div>

                        </form>
                    <#else>

                        <form action="/user/findAllUser" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search">

                                <button class="btn black" type="submit">搜索</button>

                            </div>

                        </form>

                    </#if>

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


                            <table class="table table-striped table-bordered table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th style="text-align: center; width: 20%;">姓名</th>

                                    <th style="text-align: center">用户名</th>

                                    <th style="text-align: center">电话</th>

                                    <th style="text-align: center">部门</th>

                                    <th style="text-align: center">状态</th>

                                    <th style="text-align: center; width: 12%;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if userDeptList??>
                                        <#list userDeptList as user>
                                        <tr>
                                            <td>${user.name}</td>
                                            <td>${user.username}</td>
                                            <td>${user.tel}</td>
                                            <td>
                                                <#list user.dept as dept>
                                                ${dept.deptName}<br/>
                                                </#list>
                                            </td>
                                            <#if user.status=0>
                                                <td>正在使用</td>
                                            <#else >
                                                <td>被禁用</td>
                                            </#if>
                                            <#if user.status=0>
                                                <td style="text-align: center">
                                                    <ul class="nav nav-tabs" style="margin-bottom: 0px; min-width: 94px; border-bottom: 0px solid #f4f4f4;">
                                                        <li class="dropdown" style="float: none;">
                                                            <a class="dropdown-toggle" style=" padding-bottom: 0px; padding-top: 0px;" data-toggle="dropdown" href="#">
                                                                操作 <span class="caret"></span>
                                                            </a>
                                                            <ul class="dropdown-menu" style="min-width: 105px; font-size: 13px;">
                                                                <@shiro.hasPermission name="user:statusForbid">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/statusForbid/${user.username}">禁用账号</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="user:statusForbidCommon">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/statusForbidCommon/${user.username}">禁用账号</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="dept:allotDeptView">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/dept/allotDeptView/${user.username}">分配部门</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="user:resetPassword">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/resetPassword/${user.username}">重置密码</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="user:resetPasswordCommon">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/resetPasswordCommon/${user.username}">重置密码</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="role:allotRoleView">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/role/allotRoleView/${user.username}">分配角色</a></li>
                                                                </@shiro.hasPermission>
                                                            </ul>
                                                        </li>
                                                    </ul>
                                                </td>
                                            <#else >
                                                <td style="text-align: center">
                                                    <ul class="nav nav-tabs" style="margin-bottom: 0px; min-width: 94px; border-bottom: 0px solid #f4f4f4;">
                                                        <li class="dropdown" style="float: none;">
                                                            <a class="dropdown-toggle" style=" padding-bottom: 0px; padding-top: 0px;" data-toggle="dropdown" href="#">
                                                                操作 <span class="caret"></span>
                                                            </a>
                                                            <ul class="dropdown-menu" style="min-width: 105px; font-size: 13px;">
                                                                <@shiro.hasPermission name="user:statusStart">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/statusStart/${user.username}">启用账号</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="user:statusStartCommon">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/statusStartCommon/${user.username}">启用账号</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="dept:allotDeptView">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/dept/allotDeptView/${user.username}">分配部门</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="user:resetPassword">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/resetPassword/${user.username}">重置密码</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="user:resetPasswordCommon">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/user/resetPasswordCommon/${user.username}">重置密码</a></li>
                                                                </@shiro.hasPermission>
                                                                <@shiro.hasPermission name="role:allotRoleView">
                                                                    <li style="text-align: left"><a style="color: #08c;" href="/role/allotRoleView/${user.username}">分配角色</a></li>
                                                                </@shiro.hasPermission>
                                                            </ul>
                                                        </li>
                                                    </ul>
                                                </td>
                                            </#if>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>

                            </table>
                            <#if deptIdList??>

                                <#if (count>0)>
                                    <div class="row-fluid">

                                        <div class="span6">

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 </div>
                                        </div>

                                        <#if (totlePage>0)>

                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/user/findAllUserCommon?pageSize=1<#if content??>&content=${content}</#if>"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/user/findAllUserCommon?pageSize=${pageSize-1}<#if content??>&content=${content}</#if>"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/user/findAllUserCommon?pageSize=${pageSize+1}<#if content??>&content=${content}</#if>"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/user/findAllUserCommon?pageSize=${totlePage}<#if content??>&content=${content}</#if>"><span class="hidden-480">尾页</span></a></li>
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

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${pageSize} 页 </div>
                                        </div>

                                        <#if (totlePage>0)>

                                            <div class="span6" align="right">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (pageSize>1)>
                                                            <li class="next"><a href="/user/findAllUser?pageSize=1<#if content??>&content=${content}</#if>"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/user/findAllUser?pageSize=${pageSize-1}<#if content??>&content=${content}</#if>"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (pageSize<totlePage)>
                                                            <li class="next"><a href="/user/findAllUser?pageSize=${pageSize+1}<#if content??>&content=${content}</#if>"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/user/findAllUser?pageSize=${totlePage}<#if content??>&content=${content}</#if>"><span class="hidden-480">尾页</span></a></li>
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

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script>
        $(document).ready(function() {
            $('#userManage').addClass('active');

            $('#userList').addClass('active');

            $('#userManageSelect').addClass('selected');

            $('#userManageArrow').addClass('arrow open');
        });
    </script>

    </#if>

</@layout>
