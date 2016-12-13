
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        管理员信息

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="/view/successUrl">首页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="#">管理员</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">用户信息</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <#if deptIdList??>

                        <form action="/user/findAllUserCommon" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search">

                                <button class="btn blue" type="submit">搜索</button>

                            </div>

                        </form>
                    <#else>

                        <form action="/user/findAllUser" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search">

                                <button class="btn blue" type="submit">搜索</button>

                            </div>

                        </form>

                    </#if>

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
                                    <th class="numeric">姓名</th>

                                    <th class="numeric">用户名</th>

                                    <th class="numeric">电话</th>

                                    <th class="numeric">部门</th>

                                    <th class="numeric">状态</th>

                                    <th class="numeric">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list userDeptList as user>
                                    <tr>
                                        <td>${user.name}</td>
                                        <td>${user.username}</td>
                                        <td>${user.tel}</td>
                                        <td>
                                            <#list user.dept as dept>
                                            ${dept.deptName}，
                                            </#list>
                                        </td>

                                        <#if user.status=0>
                                            <td>正在使用</td>
                                        <#else >
                                            <td>被禁用</td>
                                        </#if>
                                        <#if user.status=0>
                                            <td class="numeric" style="text-align: center">

                                                <p>
                                                    <@shiro.hasRole name="backAdmin">
                                                        <@shiro.hasPermission name="user:statusForbid">
                                                            <a href="/user/statusForbid/${user.username}" class="btn blue" id="gritter-light">禁用账号</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>

                                                    <@shiro.hasRole name="technology">
                                                        <@shiro.hasPermission name="user:statusForbidCommon">
                                                            <a href="/user/statusForbidCommon/${user.username}" class="btn blue" id="gritter-light">禁用账号</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>


                                                    <@shiro.hasPermission name="dept:allotDeptView">
                                                        <a href="/dept/allotDeptView/${user.username}" class="btn black" id="gritter-light">分配部门</a><br/>
                                                    </@shiro.hasPermission>


                                                    <@shiro.hasRole name="backAdmin">
                                                        <@shiro.hasPermission name="user:resetPassword">
                                                            <a href="/user/resetPassword/${user.username}" class="btn blue" id="gritter-max">重置密码</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>
                                                    <@shiro.hasRole name="technology">
                                                        <@shiro.hasPermission name="user:resetPasswordCommon">
                                                            <a href="/user/resetPasswordCommon/${user.username}" class="btn blue" id="gritter-max">重置密码</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>



                                                    <@shiro.hasPermission name="role:allotRoleView">
                                                        <a href="/role/allotRoleView/${user.username}" class="btn black" id="gritter-max">分配角色</a>
                                                    </@shiro.hasPermission>


                                                </p>

                                            </td>
                                        <#else >
                                            <td class="numeric" style="text-align: center">

                                                <p>
                                                    <@shiro.hasRole name="backAdmin">
                                                        <@shiro.hasPermission name="user:statusStart">
                                                            <a href="/user/statusStart/${user.username}" class="btn blue" id="gritter-light">启用账号</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>
                                                    <@shiro.hasRole name="technology">
                                                        <@shiro.hasPermission name="user:statusStartCommon">
                                                            <a href="/user/statusStartCommon/${user.username}" class="btn blue" id="gritter-light">启用账号</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>

                                                    <@shiro.hasPermission name="dept:allotDeptView">
                                                        <a href="/dept/allotDeptView/${user.username}" class="btn black" id="gritter-light">分配部门</a><br/>
                                                    </@shiro.hasPermission>

                                                    <@shiro.hasRole name="backAdmin">
                                                        <@shiro.hasPermission name="user:resetPassword">
                                                            <a href="/user/resetPassword/${user.username}" class="btn blue" id="gritter-max">重置密码</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>
                                                    <@shiro.hasRole name="technology">
                                                        <@shiro.hasPermission name="user:resetPasswordCommon">
                                                            <a href="/user/resetPasswordCommon/${user.username}" class="btn blue" id="gritter-max">重置密码</a>
                                                        </@shiro.hasPermission>
                                                    </@shiro.hasRole>

                                                    <@shiro.hasPermission name="role:allotRoleView">
                                                        <a href="/role/allotRoleView/${user.username}" class="btn black" id="gritter-max">分配角色</a>
                                                    </@shiro.hasPermission>

                                                </p>

                                            </td>
                                        </#if>
                                    </tr>
                                    </#list>
                                </tbody>

                            </table>
                            <#if deptIdList??>
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
                            <#else >
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
        $('#userManage').addClass('active');

        $('#userList').addClass('active');

        $('#userManageSelect').addClass('selected');

        $('#userManageArrow').addClass('arrow open');
    });
</script>
</@layout>
