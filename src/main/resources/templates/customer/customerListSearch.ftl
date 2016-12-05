
<#include "layout.ftl">

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

                    <!-- Three -->
                    <@shiro.hasRole name="threeAdmin">

                    <form action="/customer/findByCustomerInfoByColumnThree" method="post" class="form-search pull-right">

                        <div class="input-append">

                            <input class="m-wrap" type="text" name="content" placeholder="Search AuthId or Name">

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
                                    <th>公司名称</th>
                                    <th>账户</th>
                                    <th class="numeric">账号类型</th>
                                    <th class="numeric">余额</th>
                                    <th class="numeric">状态</th>
                                    <th class="numeric">创建时间</th>
                                    <th class="numeric">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list customerList as customer>
                                <tr>
                                    <td>${customer.name}</td>
                                    <td>${customer.authId}</td>
                                    <td class="numeric">${customer.customerType.name}</td>
                                    <td class="numeric">${customer.balance}</td>
                                    <td class="numeric">${customer.customerStatus.name}</td>
                                    <td class="numeric">${customer.createTime}</td>
                                    <td class="numeric" style="text-align: center">

                                        <p>

                                            <a href="/customer/addCustomerIpAction/${customer.id}" class="btn blue" id="gritter-light">添加Ip</a>

                                            <a href="/customer/addCustomerApiView/${customer.id}" class="btn black" id="gritter-light">添加Api</a><br/>

                                            <a href="/customer/customerIpListAction/${customer.id}" class="btn blue" id="gritter-max">管理Ip</a>

                                            <a href="/customer/findAllCustomerApiList/${customer.id}" class="btn black" id="gritter-max">管理Api</a>

                                        </p>

                                    </td>
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
                                            <li class="next"><a href="/customer/findByCustomerInfoByColumnThree?lineSize=1&content=${content}"><span class="hidden-480">首页</span></a></li>
                                            <li class="next"><a href="/customer/findByCustomerInfoByColumnThree?lineSize=${lineSize-1}&content=${content}"><span class="hidden-480">上一页</span></a></li>
                                            </#if>
                                            <#if (lineSize<totlePage)>
                                            <li class="next"><a href="/customer/findByCustomerInfoByColumnThree?lineSize=${lineSize+1}&content=${content}"><span class="hidden-480">下一页</span></a></li>
                                            <li class="next"><a href="/customer/findByCustomerInfoByColumnThree?lineSize=${totlePage}&content=${content}"><span class="hidden-480">尾页</span></a></li>
                                            </#if>
                                        </ul>
                                    </div>
                                </div>
                                </#if>
                            </div>

                        </div>

                    </div>
                    </@shiro.hasRole>

                    <!-- Two -->
                    <@shiro.hasRole name="twoAdmin">

                        <form action="/customer/findByCustomerInfoByColumnTwo" method="post" class="form-search pull-right">

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
                                        <th>公司名称</th>
                                        <th>账户</th>
                                        <th class="numeric">账号类型</th>
                                        <th class="numeric">余额</th>
                                        <th class="numeric">状态</th>
                                        <th class="numeric">推荐人</th>
                                        <th class="numeric">创建时间</th>
                                        <th class="numeric">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#list customerList as customer>
                                        <tr>
                                            <td>${customer.name}</td>
                                            <td>${customer.authId}</td>
                                            <td class="numeric">${customer.customerType.name}</td>
                                            <td class="numeric">${customer.balance}</td>
                                            <td class="numeric">${customer.customerStatus.name}</td>
                                            <td class="numeric">${customer.user.name}</td>
                                            <td class="numeric">${customer.createTime}</td>
                                            <td class="numeric" style="text-align: center">

                                                <p>

                                                    <a href="/customer/addCustomerIpAction/${customer.id}" class="btn blue" id="gritter-light">添加Ip</a>

                                                    <a href="/customer/addCustomerApiView/${customer.id}" class="btn black" id="gritter-light">添加Api</a><br/>

                                                    <a href="/customer/customerIpListAction/${customer.id}" class="btn blue" id="gritter-max">管理Ip</a>

                                                    <a href="/customer/findAllCustomerApiList/${customer.id}" class="btn black" id="gritter-max">管理Api</a>

                                                </p>

                                            </td>
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
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnTwo?lineSize=1&content=${content}"><span class="hidden-480">首页</span></a></li>
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnTwo?lineSize=${lineSize-1}&content=${content}"><span class="hidden-480">上一页</span></a></li>
                                                    </#if>
                                                    <#if (lineSize<totlePage)>
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnTwo?lineSize=${lineSize+1}&content=${content}"><span class="hidden-480">下一页</span></a></li>
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnTwo?lineSize=${totlePage}&content=${content}"><span class="hidden-480">尾页</span></a></li>
                                                    </#if>
                                                </ul>
                                            </div>
                                        </div>
                                    </#if>
                                </div>

                            </div>

                        </div>
                    </@shiro.hasRole>

                    <!-- One -->
                    <@shiro.hasRole name="oneAdmin">

                        <form action="/customer/findByCustomerInfoByColumnOne" method="post" class="form-search pull-right">

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
                                        <th>公司名称</th>
                                        <th>账户</th>
                                        <th class="numeric">账号类型</th>
                                        <th class="numeric">余额</th>
                                        <th class="numeric">状态</th>
                                        <th class="numeric">推荐人</th>
                                        <th class="numeric">创建时间</th>
                                        <th class="numeric">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#list customerList as customer>
                                        <tr>
                                            <td>${customer.name}</td>
                                            <td>${customer.authId}</td>
                                            <td class="numeric">${customer.customerType.name}</td>
                                            <td class="numeric">${customer.balance}</td>
                                            <td class="numeric">${customer.customerStatus.name}</td>
                                            <td class="numeric">${customer.user.name}</td>
                                            <td class="numeric">${customer.createTime}</td>
                                            <td class="numeric" style="text-align: center">

                                                <p>

                                                    <a href="/customer/addCustomerIpAction/${customer.id}" class="btn blue" id="gritter-light">添加Ip</a>

                                                    <a href="/customer/addCustomerApiView/${customer.id}" class="btn black" id="gritter-light">添加Api</a><br/>

                                                    <a href="/customer/customerIpListAction/${customer.id}" class="btn blue" id="gritter-max">管理Ip</a>

                                                    <a href="/customer/findAllCustomerApiList/${customer.id}" class="btn black" id="gritter-max">管理Api</a>

                                                </p>

                                            </td>
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
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnOne?lineSize=1&content=${content}"><span class="hidden-480">首页</span></a></li>
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnOne?lineSize=${lineSize-1}&content=${content}"><span class="hidden-480">上一页</span></a></li>
                                                    </#if>
                                                    <#if (lineSize<totlePage)>
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnOne?lineSize=${lineSize+1}&content=${content}"><span class="hidden-480">下一页</span></a></li>
                                                        <li class="next"><a href="/customer/findByCustomerInfoByColumnOne?lineSize=${totlePage}&content=${content}"><span class="hidden-480">尾页</span></a></li>
                                                    </#if>
                                                </ul>
                                            </div>
                                        </div>
                                    </#if>

                                </div>

                            </div>

                        </div>
                    </@shiro.hasRole>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    </#if>
</@layout>
