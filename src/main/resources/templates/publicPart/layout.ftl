<#macro layout>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>

    <meta charset="utf-8" />
    <title>平台管理后台</title>
    <meta content="width=device-width, height=device-height, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <link rel="stylesheet" type="text/css" href="/css/local/progress.css"/>
    <link rel="stylesheet" type="text/css" href="/css/local/main-response-table.css"/>
    <link rel="stylesheet" type="text/css" href="/css/local/customer-style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/local/bootstrap-multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style-metro.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/css/default.css" id="style_color"/>
    <link rel="stylesheet" type="text/css" href="/css/uniform.default.css"/>
    <link rel="stylesheet" type="text/css" href="/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-fileupload.css" />
    <link rel="stylesheet" type="text/css" href="/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="/css/chosen.css" />
    <link rel="stylesheet" type="text/css" href="/css/select2_metro.css" />
    <link rel="stylesheet" type="text/css" href="/css/jquery.tagsinput.css" />
    <link rel="stylesheet" type="text/css" href="/css/clockface.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-wysihtml5.css" />
    <link rel="stylesheet" type="text/css" href="/css/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="/css/timepicker.css" />
    <link rel="stylesheet" type="text/css" href="/css/colorpicker.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-toggle-buttons.css" />
    <link rel="stylesheet" type="text/css" href="/css/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="/css/datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="/css/multi-select-metro.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-modal.css"/>
    <link rel="stylesheet" type="text/css" href="/css/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="/css/search.css"/>
    <link rel="stylesheet" type="text/css" href="/css/icon.css" />
    <link rel="shortcut icon" href="/image/favicon.ico" />

    <#--加载中动态显示控制-->
    <link rel="stylesheet" type="text/css" href="/css/sweetlert/spinners.css" />
    <#--弹框样式控制-->
    <link rel="stylesheet" type="text/css" href="/css/sweetlert/sweet-alert2.css" />
    <#--文件上传样式控制-->
   <#-- <link rel="stylesheet" type="text/css" href="/css/upload/jquery-ui-1.8.14.custom.css" />
    <link rel="stylesheet" type="text/css" href="/css/upload/reset.css" />
    <link rel="stylesheet" type="text/css" href="/css/upload/fileUploader.css" />-->

    <style>
        a:hover {text-decoration: none;}
    </style>

</head>

<body class="page-header-fixed">

<div id="head"><#nested "head" />

    <div class="header navbar navbar-inverse navbar-fixed-top">

        <div class="navbar-inner">

            <div class="container-fluid">

                <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                    <img src="/image/menu-toggler.png" alt="" />

                </a>

                <ul class="nav pull-right">

                    <li class="dropdown user">

                        <a href="javaScript:;" class="dropdown-toggle" data-toggle="dropdown">

                            <span class="username"><@shiro.principal/></span>

                            <i class="icon-angle-down"></i>

                        </a>

                        <ul class="dropdown-menu">

                            <li><a href="/user/updatePasswordView"><i class="icon-calendar"></i>修改密码</a></li>

                            <li><a href="/view/logout"><i class="icon-key"></i> 退出</a></li>

                        </ul>

                    </li>

                </ul>

            </div>

        </div>

    </div>

    <div class="page-container row-fluid">

        <div class="page-sidebar nav-collapse collapse">

            <ul class="page-sidebar-menu">

                <li>

                    <div class="sidebar-toggler hidden-phone"></div>

                </li>
            <#--客户管理-->
                <@shiro.hasAnyRoles name="sell,backAdmin">

                    <li class="" id="customerManage">
                        <a href="javascript:;">

                            <i class="icon-group"></i>

                            <span class="title">客户管理</span>

                            <span class="" id="customerManageSelect"></span>

                            <span class="arrow " id="customerManageArrow"></span>

                        </a>

                        <ul class="sub-menu">

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="customerList">
                                    <a href="/company/find-all-company-customer">
                                        客户信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                <li id="customerList">
                                    <a href="/company/find-all-company-customer-by-dept-id">
                                        客户信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                        </ul>
                    </li>

                </@shiro.hasAnyRoles>

            <#--财务管理-->
                <@shiro.hasAnyRoles name="sell,backAdmin">

                    <li class="" id="customerBalance">
                        <a href="javascript:;">

                            <i class="icon-bar-chart"></i>

                            <span class="title">财务管理</span>

                            <span class="" id="customerBalanceSelect"></span>

                            <span class="arrow " id="customerBalanceArrow"></span>

                        </a>

                        <ul class="sub-menu">

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="customerListBalanceLog">
                                    <a href="/finance/find-all-customer">
                                        客户财务账单
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="customerListCache">
                                    <a href="/cache/find-all-customer">
                                        客户调用缓存情况
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomerByDeptNo">
                                <li id="customerListBalanceLog">
                                    <a href="/finance/find-all-customer-by-dept-id">
                                        客户财务账单
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="apiRecordLog">
                                    <a href="/api/find-all-api-record">
                                        产品消费账单
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="apiVendorRecordLog">
                                    <a href="/api/find-all-api-vendor-consume">
                                        供应商财务账单
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="partnersFinancialAccount">
                                    <a href="/partner/find-all-partner-financial-account">
                                        合作公司来往账目
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                          <#--  <li id="">
                                <a href="/file-upload">
                                    上传文件
                                </a>
                            </li>-->

                        <#-- <@shiro.hasPermission name="customer:findAllCustomer">
                             <li id="partnersFinancialAccount">
                                 <a href="/partner/export-excel">
                                     合作公司Excel导出
                                 </a>
                             </li>
                         </@shiro.hasPermission>-->

                        </ul>

                    </li>

                </@shiro.hasAnyRoles>

            <#--产品管理-->
                <@shiro.hasAnyRoles name="backAdmin,finance">

                    <li id="apiProduct">
                        <a href="javascript:;">

                            <i class="icon-barcode"></i>

                            <span class="title">产品管理</span>

                            <span class="" id="apiProductSelect"></span>

                            <span class="arrow " id="apiProductArrow"></span>

                        </a>

                        <ul class="sub-menu">

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="apiProductList">
                                    <a href="/api/api-message">
                                        产品信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="apiMonitor">
                                    <a href="/api/api-monitor">
                                        产品监控
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="api:api-price-change-log">
                                <li id="apiPriceChangeLog">
                                    <a href="/api/api-price-change-log">
                                        产品价格变动日志
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="apiCompanyProductList">
                                    <a href="/api/api-message-by-company">
                                        客户产品信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                        </ul>

                    </li>

                </@shiro.hasAnyRoles>

            <#--客户请求日志-->
                <@shiro.hasAnyRoles name="backAdmin">

                    <li id="CustomerRequestLog">
                        <a href="/customer/find-all-customer-request-log">
                            <i class="icon-cloud"></i
                            <span class="title">客户请求日志</span>
                        </a>
                    </li>

                </@shiro.hasAnyRoles>

            <#--日志管理-->
                <@shiro.hasAnyRoles name="backAdmin">

                    <li id="logManage">
                        <a href="javascript:;">

                            <i class="icon-barcode"></i>

                            <span class="title">日志管理</span>

                            <span class="" id="logSelect"></span>

                            <span class="arrow " id="logArrow"></span>

                        </a>

                        <ul class="sub-menu">

                            <@shiro.hasPermission name="customer:findAllCustomer">
                                <li id="logMessage">
                                    <a href="/log/log-message">
                                        日志信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                        </ul>

                    </li>

                </@shiro.hasAnyRoles>

            <#--用户管理-->
                <@shiro.hasAnyRoles name="backAdmin,technology">

                    <li class="" id="userManage">
                        <a href="javascript:;">

                            <i class="icon-user"></i>

                            <span class="title">管理员</span>

                            <span class="" id="userManageSelect"></span>

                            <span class="arrow " id="userManageArrow"></span>

                        </a>

                        <ul class="sub-menu">
                            <@shiro.hasPermission name="user:addUserView">
                                <li id="addUser">
                                    <a href="/user/addUserView">
                                        新增管理员
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="user:addUserViewCommon">
                                <li id="addUser">
                                    <a href="/user/addUserViewCommon">
                                        新增管理员
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="user:findAllUser">
                                <li id="userList">
                                    <a href="/user/findAllUser">
                                        管理员信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="user:findAllUserCommon">
                                <li id="userList">
                                    <a href="/user/findAllUserCommon">
                                        管理员信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                        </ul>

                    </>

                </@shiro.hasAnyRoles>

            <#--部门管理-->
                <@shiro.hasAnyRoles name="backAdmin">

                    <li class="" id="deptManage">
                        <a href="javascript:;">

                            <i class="icon-th"></i>

                            <span class="title">部门管理</span>

                            <span class="" id="deptManageSelect"></span>

                            <span class="arrow " id="deptManageArrow"></span>

                        </a>

                        <ul class="sub-menu">
                            <@shiro.hasPermission name="dept:addDeptView">
                                <li id="addDept">
                                    <a href="/dept/addDeptView">
                                        新增部门
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="dept:findAllDept">
                                <li id="deptList">
                                    <a href="/dept/findAllDept">
                                        部门信息
                                    </a>
                                </li>
                            </@shiro.hasPermission>

                        </ul>

                    </li>

                </@shiro.hasAnyRoles>

            <#-- &lt;#&ndash;消息通知&ndash;&gt;
                 <@shiro.hasAnyRoles name="backAdmin">

                     <li>

                         <a href="index.html">

                             <i class="icon-bullhorn"></i>

                             <span class="title">消息通知</span>

                         </a>

                     </li>
                 </@shiro.hasAnyRoles>&ndash;&gt;-->

            </ul>

        </div>

       <#-- <div class="cube">
            <div class="tg-qe-progress-bar myactive">
                <div class="bar"></div>
            </div>
        </div>-->

    </div>

</div>

<div id="content"><#nested "content" />

</div>

<div id="footer"><#nested "footer" />

    <div class="footer">

        <div class="footer-inner">

        <#--2016 &copy;-->
        </div>

        <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

        </div>

    </div>

</div>

    <#nested "publicJs" />

    <#nested "privateJs" />
</body>
</html>
</#macro>