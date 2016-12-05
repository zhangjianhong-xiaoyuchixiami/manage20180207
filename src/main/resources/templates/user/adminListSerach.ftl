
<#include "../customer/layout.ftl">

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">


        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>portlet Settings</h3>

            </div>

            <div class="modal-body">

                <p>Here will be a configuration form</p>

            </div>

        </div>

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN STYLE CUSTOMIZER -->

                    <div class="color-panel hidden-phone">

                        <div class="color-mode-icons icon-color"></div>

                        <div class="color-mode-icons icon-color-close"></div>

                        <div class="color-mode">

                            <p>THEME COLOR</p>

                            <ul class="inline">

                                <li class="color-black current color-default" data-style="default"></li>

                                <li class="color-blue" data-style="blue"></li>

                                <li class="color-brown" data-style="brown"></li>

                                <li class="color-purple" data-style="purple"></li>

                                <li class="color-grey" data-style="grey"></li>

                                <li class="color-white color-light" data-style="light"></li>

                            </ul>

                            <label>

                                <span>Layout</span>

                                <select class="layout-option m-wrap small">

                                    <option value="fluid" selected>Fluid</option>

                                    <option value="boxed">Boxed</option>

                                </select>

                            </label>

                            <label>

                                <span>Header</span>

                                <select class="header-option m-wrap small">

                                    <option value="fixed" selected>Fixed</option>

                                    <option value="default">Default</option>

                                </select>

                            </label>

                            <label>

                                <span>Sidebar</span>

                                <select class="sidebar-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>

                                </select>

                            </label>

                            <label>

                                <span>Footer</span>

                                <select class="footer-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>

                                </select>

                            </label>

                        </div>

                    </div>

                    <!-- END BEGIN STYLE CUSTOMIZER -->

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        Responsive Tables <small>responsive table samples</small>

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

                        <li><a href="#">管理员信息</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                        <form action="/user/findAllByColumn" method="post" class="form-search pull-right">

                            <div class="input-append">

                                <input class="m-wrap" type="text" name="content" placeholder="Search Mail">

                                <button class="btn blue" type="submit">Search</button>

                            </div>

                        </form>

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
                                        <th>姓名</th>
                                        <th>登录名</th>
                                        <th class="numeric">密码</th>
                                        <th class="numeric">电话</th>
                                        <th class="numeric">部门</th>
                                        <th class="numeric">角色</th>
                                        <th class="numeric">状态</th>
                                        <th class="numeric">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                 <#list adminList as user>
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.loginName}</td>
                                    <td class="numeric">${user.password}</td>
                                    <td class="numeric">${user.tel}</td>
                                    <td class="numeric">${user.dept.deptName}</td>
                                    <td class="numeric">${user.role.name}</td>
                                    <#if user.status=0>
                                        <td class="numeric">正在使用</td>
                                    <#else >
                                        <td class="numeric">被禁用</td>
                                    </#if>
                                    <#if user.status=0>
                                        <td class="numeric"><a style="color: red" href="/user/statusForbid/${user.loginName}">禁用</a></td>
                                    <#else >
                                        <td class="numeric"><a style="color: blue" href="/user/statusStart/${user.loginName}">启用</a></td>
                                     </#if>
                                </tr>
                                 </#list>
                                    </tbody>

                                </table>

                                    <div class="row-fluid">

                                        <div class="span6">

                                            <div class="dataTables_info" id="sample_1_info">当前显示第 ${lineSize} 页 共 ${totlePage} 页</div>
                                        </div>

                                        <#if (totlePage>1)>
                                            <div class="span6">
                                                <div class="dataTables_paginate paging_bootstrap pagination">
                                                    <ul>
                                                        <#if (lineSize>1)>
                                                            <li class="next"><a href="/user/findAllByColumn?lineSize=1&content=${content}"><span class="hidden-480">首页</span></a></li>
                                                            <li class="next"><a href="/user/findAllByColumn?lineSize=${lineSize-1}&content=${content}"><span class="hidden-480">上一页</span></a></li>
                                                        </#if>
                                                        <#if (lineSize<totlePage)>
                                                            <li class="next"><a href="/user/findAllByColumn?lineSize=${lineSize+1}&content=${content}"><span class="hidden-480">下一页</span></a></li>
                                                            <li class="next"><a href="/user/findAllByColumn?lineSize=${totlePage}&content=${content}"><span class="hidden-480">尾页</span></a></li>
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

</@layout>
