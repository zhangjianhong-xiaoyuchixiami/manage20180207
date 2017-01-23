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
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="/css/DT_bootstrap.css" />

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
    <link href="/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="/css/datepicker.css" />

    <link href="/css/search.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="/css/icon.css" />
    <link rel="shortcut icon" href="/image/favicon.ico" />

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

                            <span class="username" style="font-size: 16px;">
                                <#if Session.userInfo?exists>
                                   ${Session.userInfo.name}
                               </#if>
                            </span>

                            <i class="icon-angle-down"></i>

                        </a>

                        <ul class="dropdown-menu">

                            <li><a href="/user/updatePasswordView"><i class="icon-calendar"></i>修改密码</a></li>

                            <li><a href="/view/logout"><i class="icon-key"></i> 退出</a></li>

                        </ul>

                    </li>

                    <!-- END USER LOGIN DROPDOWN -->

                </ul>

                <span id="username" style="display: none"><@shiro.principal/></span>

            </div>

        </div>

    </div>

    <div class="page-container row-fluid">

        <div class="page-sidebar nav-collapse collapse">

            <!-- BEGIN SIDEBAR MENU -->

            <ul class="page-sidebar-menu">

                <li>

                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

                    <div class="sidebar-toggler hidden-phone"></div>

                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

                </li>

                <li>

                    <!-- 搜索框 -->

                    <form class="sidebar-search" action="#">

                        <div class="input-box">

                        <#--<a href="#" class="remove"></a>-->

                            <#--<input type="text" placeholder="Search..." />-->

                            <#--<input type="submit" class="submit" value=" " />-->

                        </div>

                    </form>

                    <!-- 搜索框结束 -->
                </li>

            <#--<@shiro.hasAnyRoles name="sell,backAdmin">-->
            <#--<li class="" id="customerManage">-->
            <#--<a href="javascript:;">-->

            <#--<i class="icon-sitemap"></i>-->

            <#--<span class="title">客户管理</span>-->

            <#--<span class="" id="customerManageSelect"></span>-->

            <#--<span class="arrow " id="customerManageArrow"></span>-->

            <#--</a>-->

            <#--<ul class="sub-menu">-->

            <#--<@shiro.hasPermission name="company:addCompanyAllDeptView">-->
            <#--<li id="addCustomer">-->
            <#--<a href="/company/addCompanyAllDeptView">-->
            <#--新增客户-->
            <#--</a>-->
            <#--</li>-->
            <#--</@shiro.hasPermission>-->

            <#--<@shiro.hasPermission name="company:addCompanyOnlyDeptView">-->
            <#--<li id="addCustomer">-->
            <#--<a href="/company/addCompanyOnlyDeptView">-->
            <#--新增客户-->
            <#--</a>-->
            <#--</li>-->
            <#--</@shiro.hasPermission>-->

            <#--<@shiro.hasPermission name="company:findAllAction">-->
            <#--<li id="customerList">-->
            <#--<a href="/company/findAllAction">-->
            <#--客户信息-->
            <#--</a>-->
            <#--</li>-->
            <#--</@shiro.hasPermission>-->

            <#--<@shiro.hasPermission name="company:findAllByDeptIdAction">-->
            <#--<li id="customerList">-->
            <#--<a href="/company/findAllByDeptIdAction">-->
            <#--客户信息-->
            <#--</a>-->
            <#--</li>-->
            <#--</@shiro.hasPermission>-->

            <#--</ul>-->
            <#--</li>-->
            <#--</@shiro.hasAnyRoles>-->

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

                        <#--<li id="partnersFinancialAccount">-->

                        <#--<a href="javascript:;">-->

                        <#--合作企业来往账目-->

                        <#--<span class="" id="partnersSelect"></span>-->

                        <#--<span class="arrow" id="partnersArrow"></span>-->

                        <#--</a>-->

                        <#--<ul class="sub-menu">-->

                        <#--<li id="partnersUp"><a href="/finance/find-all-partners-financial-account">上游企业</a></li>-->

                        <#--<li id="partnersDown"><a href="/finance/find-all-partners-financial-account">下游企业</a></li>-->

                        <#--</ul>-->

                        <#--</li>-->

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

            </ul>

        </div>

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

<script src="/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<script src="/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="/js/bootstrap.min.js" type="text/javascript"></script>

<script src="/js/excanvas.min.js"></script>

<script src="/js/respond.min.js"></script>

<script src="/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="/js/jquery.uniform.min.js" type="text/javascript" ></script>

<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>

<script src="/js/search.js"></script>

<script type="text/javascript" src="/js/jquery.validate.min.js"></script>

<script type="text/javascript" src="/js/select2.min.js"></script>

<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>

<script type="text/javascript" src="/js/ckeditor.js"></script>

<script type="text/javascript" src="/js/bootstrap-fileupload.js"></script>

<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>

<script type="text/javascript" src="/js/select2.min.js"></script>

<script type="text/javascript" src="/js/wysihtml5-0.3.0.js"></script>

<script type="text/javascript" src="/js/bootstrap-wysihtml5.js"></script>

<script type="text/javascript" src="/js/jquery.tagsinput.min.js"></script>

<script type="text/javascript" src="/js/jquery.toggle.buttons.js"></script>

<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="/js/bootstrap-datetimepicker.js"></script>

<script type="text/javascript" src="/js/clockface.js"></script>

<script type="text/javascript" src="/js/date.js"></script>

<script type="text/javascript" src="/js/daterangepicker.js"></script>

<script type="text/javascript" src="/js/bootstrap-colorpicker.js"></script>

<script type="text/javascript" src="/js/bootstrap-timepicker.js"></script>

<script type="text/javascript" src="/js/jquery.inputmask.bundle.min.js"></script>

<script type="text/javascript" src="/js/jquery.input-ip-address-control-1.0.min.js"></script>

<script type="text/javascript" src="/js/jquery.multi-select.js"></script>

<script src="/js/bootstrap-modal.js" type="text/javascript" ></script>

<script src="/js/bootstrap-modalmanager.js" type="text/javascript" ></script>

<script src="/js/app.js"></script>

<script src="/js/form-validation.js"></script>

<script src="/js/form-components.js"></script>

<script>

    jQuery(document).ready(function() {
        App.init();

        Search.init();

    });
</script>

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();
</script>

    <#nested "privateJs" />
</body>
</html>
</#macro>