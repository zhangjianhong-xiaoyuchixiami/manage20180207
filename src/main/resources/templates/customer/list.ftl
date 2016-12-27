<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>Metronic | Data Tables - Managed Tables</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="" name="description" />

    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link rel="stylesheet" type="text/css" href="/css/select2_metro.css" />

    <link rel="stylesheet" href="/css/DT_bootstrap.css" />

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">


<div class="page-container row-fluid">

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <div class="portlet box light-grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-globe"></i>Managed Table</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body">

                            <table class="table table-striped table-bordered table-hover" id="sample_1">

                                <thead>

                                <tr>

                                    <th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" /></th>

                                    <th>Username</th>

                                    <th class="hidden-480">Email</th>

                                    <th class="hidden-480">Points</th>

                                    <th class="hidden-480">Joined</th>

                                    <th ></th>

                                </tr>

                                </thead>

                                <tbody>

                                <#list userList as user>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>${user.id}</td>

                                    <td class="hidden-480"><a href="mailto:shuxer@gmail.com"></a>${user.name}</td>

                                    <td class="hidden-480">${user.username}</td>

                                    <td class="center hidden-480">${user.tel}</td>

                                    <td >Suspended</td>

                                </tr>

                                </#list>

                                </tbody>

                            </table>

                        </div>

                    </div>

                    <!-- END EXAMPLE TABLE PORTLET-->

                </div>

            </div>

        </div>

    </div>

</div>

<div class="footer">

    <div class="footer-inner">

        2013 &copy; Metronic by keenthemes.

    </div>

    <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

    </div>

</div>

<script src="/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="/js/excanvas.min.js"></script>

<script src="/js/respond.min.js"></script>

<![endif]-->

<script src="/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="/js/jquery.uniform.min.js" type="text/javascript" ></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

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

        TableManaged.init();

    });

</script>

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->

</html>