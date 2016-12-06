<#macro layout>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<head>
    <meta charset="utf-8" />
    <title>千眼数合</title>
    <meta content="width=device-width, height=device-height, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/font-awesome.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="/css/select2_metro.css" />

    <link rel="stylesheet" href="/css/DT_bootstrap.css" />

    <link rel="shortcut icon" href="/image/favicon.ico" />
</head>

<body class="page-header-fixed">

<div id="head"><#nested "head" />

    <div class="header navbar navbar-inverse navbar-fixed-top">

        <!-- BEGIN TOP NAVIGATION BAR -->

        <div class="navbar-inner">

            <div class="container-fluid">

                <!-- BEGIN LOGO -->

                <a class="brand" href="index.html">

                    <img src="/image/logo.png" alt="logo" />

                </a>

                <!-- END LOGO -->

                <!-- BEGIN RESPONSIVE MENU TOGGLER -->

                <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                    <img src="/image/menu-toggler.png" alt="" />

                </a>

                <!-- END RESPONSIVE MENU TOGGLER -->

                <!-- BEGIN TOP NAVIGATION MENU -->

                <ul class="nav pull-right">

                    <!-- BEGIN NOTIFICATION DROPDOWN -->

                    <li class="dropdown" id="header_notification_bar">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <i class="icon-warning-sign"></i>

                            <span class="badge">6</span>

                        </a>

                        <ul class="dropdown-menu extended notification">

                            <li>

                                <p>You have 14 new notifications</p>

                            </li>

                            <li>

                                <a href="#">

                                    <span class="label label-success"><i class="icon-plus"></i></span>

                                    New user registered.

                                    <span class="time">Just now</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

                                    <span class="label label-important"><i class="icon-bolt"></i></span>

                                    Server #12 overloaded.

                                    <span class="time">15 mins</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

                                    <span class="label label-warning"><i class="icon-bell"></i></span>

                                    Server #2 not respoding.

                                    <span class="time">22 mins</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

                                    <span class="label label-info"><i class="icon-bullhorn"></i></span>

                                    Application error.

                                    <span class="time">40 mins</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

                                    <span class="label label-important"><i class="icon-bolt"></i></span>

                                    Database overloaded 68%.

                                    <span class="time">2 hrs</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

                                    <span class="label label-important"><i class="icon-bolt"></i></span>

                                    2 user IP blocked.

                                    <span class="time">5 hrs</span>

                                </a>

                            </li>

                            <li class="external">

                                <a href="#">See all notifications <i class="m-icon-swapright"></i></a>

                            </li>

                        </ul>

                    </li>

                    <!-- END NOTIFICATION DROPDOWN -->

                    <!-- BEGIN INBOX DROPDOWN -->

                    <li class="dropdown" id="header_inbox_bar">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <i class="icon-envelope"></i>

                            <span class="badge">5</span>

                        </a>

                        <ul class="dropdown-menu extended inbox">

                            <li>

                                <p>You have 12 new messages</p>

                            </li>

                            <li>

                                <a href="inbox.html?a=view">

                                    <span class="photo"><img src="/image/avatar2.jpg" alt="" /></span>

                                    <span class="subject">

								<span class="from">Lisa Wong</span>

								<span class="time">Just Now</span>

								</span>

                                    <span class="message">

								Vivamus sed auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="inbox.html?a=view">

                                    <span class="photo"><img src="/image/avatar3.jpg" alt="" /></span>

                                    <span class="subject">

								<span class="from">Richard Doe</span>

								<span class="time">16 mins</span>

								</span>

                                    <span class="message">

								Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="inbox.html?a=view">

                                    <span class="photo"><img src="/image/avatar1.jpg" alt="" /></span>

                                    <span class="subject">

								<span class="from">Bob Nilson</span>

								<span class="time">2 hrs</span>

								</span>

                                    <span class="message">

								Vivamus sed nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                                </a>

                            </li>

                            <li class="external">

                                <a href="inbox.html">See all messages <i class="m-icon-swapright"></i></a>

                            </li>

                        </ul>

                    </li>

                    <!-- END INBOX DROPDOWN -->

                    <!-- BEGIN TODO DROPDOWN -->

                    <li class="dropdown" id="header_task_bar">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <i class="icon-tasks"></i>

                            <span class="badge">5</span>

                        </a>

                        <ul class="dropdown-menu extended tasks">

                            <li>

                                <p>You have 12 pending tasks</p>

                            </li>

                            <li>

                                <a href="#">

								<span class="task">

								<span class="desc">New release v1.2</span>

								<span class="percent">30%</span>

								</span>

                                    <span class="progress progress-success ">

								<span style="width: 30%;" class="bar"></span>

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

								<span class="task">

								<span class="desc">Application deployment</span>

								<span class="percent">65%</span>

								</span>

                                    <span class="progress progress-danger progress-striped active">

								<span style="width: 65%;" class="bar"></span>

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

								<span class="task">

								<span class="desc">Mobile app release</span>

								<span class="percent">98%</span>

								</span>

                                    <span class="progress progress-success">

								<span style="width: 98%;" class="bar"></span>

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

								<span class="task">

								<span class="desc">Database migration</span>

								<span class="percent">10%</span>

								</span>

                                    <span class="progress progress-warning progress-striped">

								<span style="width: 10%;" class="bar"></span>

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

								<span class="task">

								<span class="desc">Web server upgrade</span>

								<span class="percent">58%</span>

								</span>

                                    <span class="progress progress-info">

								<span style="width: 58%;" class="bar"></span>

								</span>

                                </a>

                            </li>

                            <li>

                                <a href="#">

								<span class="task">

								<span class="desc">Mobile development</span>

								<span class="percent">85%</span>

								</span>

                                    <span class="progress progress-success">

								<span style="width: 85%;" class="bar"></span>

								</span>

                                </a>

                            </li>

                            <li class="external">

                                <a href="#">See all tasks <i class="m-icon-swapright"></i></a>

                            </li>

                        </ul>

                    </li>

                    <!-- END TODO DROPDOWN -->

                    <!-- BEGIN USER LOGIN DROPDOWN -->

                    <li class="dropdown user">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                            <img alt="" src="/image/avatar1_small.jpg" />

                            <span class="username">管理员选项</span>

                            <i class="icon-angle-down"></i>

                        </a>

                        <ul class="dropdown-menu">

                            <li><a href="/user/updatePasswordView"><i class="icon-calendar"></i>修改密码</a></li>

                            <li><a href="#"><i class="icon-key"></i> 退出</a></li>

                            <li><a href="#"><i class="icon-user"></i>  My Calendar</a></li>

                            <li><a href="#"><i class="icon-envelope"></i> My Inbox(3)</a></li>

                            <li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>

                            <li class="divider"></li>

                            <li><a href="#"><i class="icon-lock"></i> Lock Screen</a></li>



                        </ul>

                    </li>

                    <!-- END USER LOGIN DROPDOWN -->

                </ul>

                <!-- END TOP NAVIGATION MENU -->

            </div>

        </div>

        <!-- END TOP NAVIGATION BAR -->

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

                    <form class="sidebar-search">

                        <div class="input-box">

                            <a href="javascript:;" class="remove"></a>

                            <input type="text" placeholder="Search..." />

                            <input type="button" class="submit" value=" " />

                        </div>

                    </form>

                    <!-- 搜索框结束 -->
                </li>
            <#--客户管理-->
                <li>
                    <a class="active" href="javascript:;">

                        <i class="icon-sitemap"></i>

                        <span class="title">客户管理</span>

                        <span class="arrow "></span>

                    </a>

                    <ul class="sub-menu">

                        <li>
                            <a href="/customer/addCustomerView">
                                新增客户
                            </a>
                        </li>

                        <li>
                            <a href="/customer/findAllCustomer">
                                客户信息
                            </a>
                        </li>

                    </ul>
                </li>
            <#--财务管理-->
                <li>
                    <a class="active" href="javascript:;">

                        <i class="icon-sitemap"></i>

                        <span class="title">财务管理</span>

                        <span class="arrow "></span>

                    </a>

                    <ul class="sub-menu">

                        <li>
                            <a href="/customerBalance/customerBalanceChangeView">
                                客户余额变更
                            </a>
                        </li>

                    </ul>

                </li>
            <#--用户管理-->
                <li>
                    <a class="active" href="javascript:;">

                        <i class="icon-sitemap"></i>

                        <span class="title">用户管理</span>

                        <span class="arrow "></span>

                    </a>

                    <ul class="sub-menu">

                        <li>
                            <a href="/user/addUserView">
                                新增用户
                            </a>
                        </li>
                        <li>
                            <a href="/user/findAllUser">
                                用户信息
                            </a>
                        </li>

                    </ul>

                </li>
            <#--部门管理-->
                <li>
                    <a class="active" href="javascript:;">

                        <i class="icon-sitemap"></i>

                        <span class="title">部门管理</span>

                        <span class="arrow "></span>

                    </a>

                    <ul class="sub-menu">

                        <li>
                            <a href="#">
                                新增部门
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                部门信息
                            </a>
                        </li>

                    </ul>

                </li>

            </ul>

        </div>

    </div>

</div>

<div id="content"><#nested "content" />

</div>

<div id="footer"><#nested "footer" />

    <div class="footer">

        <div class="footer-inner">

            2016 &copy; 北京千眼数合科技有限公司
        </div>

        <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

        </div>

    </div>

</div>

<script src="/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="/js/bootstrap.min.js" type="text/javascript" ></script>

<script src="/js/bootstrapValidator.min.js" type="text/javascript" ></script>

<script src="/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<script src="/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="/js/bootstrap.min.js" type="text/javascript"></script>

<script src="/js/excanvas.min.js"></script>

<script src="/js/respond.min.js"></script>

<script src="/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="/js/jquery.uniform.min.js" type="text/javascript" ></script>

<script type="text/javascript" src="/js/select2.min.js"></script>

<script type="text/javascript" src="/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="/js/DT_bootstrap.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="/js/app.js"></script>

<script src="/js/table-managed.js"></script>

<script>

    jQuery(document).ready(function() {
        App.init();
    });
</script>

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();
</script>
<script>
    $(document).ready(function(){

        $("#authId").blur(function(){
            $("#authIdMsg").load("/customer/findCustomerByAuthId/"+$("#authId").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#authIdMsg").html("");
                        if(responseTxt=="no")
                            $("#authIdMsg").html("该账户不存在！");
                    });
        });

        $("#authIdAdd").blur(function(){
            $("#authIdAddMsg").load("/customer/findCustomerByAuthId/"+$("#authIdAdd").val(),
                    function(responseTxt){
                        if(responseTxt=="yes")
                            $("#authIdAddMsg").html("该账户已存在！");
                        if(responseTxt=="no")
                            $("#authIdAddMsg").html("");
                    });
        });

    });

</script>

<script>

    $(document).ready(function(){


        $('#save').on("click",function(){

            //jquery获取复选框值
            var deptNo_no =[];//定义一个数组

            $('input[name="deptNo"]:checked').each(function(){
                deptNo_no.push($.trim($(this).val()));
            });
            var userId_id = $('input[name="userId"]').val();

            var indata = {"userId":userId_id, "deptNo":deptNo_no};
            alert(userId_id);
            $.ajax({
                type:'post',
                url:"/dept/allotDeptAction",
                data:indata,
                dataType:'json',
                success:function(data){
                        alert("设置成功");
                }
            })

        });
    });
</script>
</body>
</html>
</#macro>