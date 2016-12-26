<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>Metronic | Data Tables - Editable Tables</title>

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

<body class="page-header-fixed">

<div class="page-container row-fluid">

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box blue">

                        <div class="portlet-body">

                            <table class="table table-striped table-hover table-bordered" id="sample_editable_1">

                                <thead>

                                <tr>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample_editable_1" rowspan="1" colspan="1" aria-label="Username: activate to sort column ascending" style="width: 289px;">Username</th>

                                    <th>Full Name</th>

                                    <th>Points</th>

                                    <th>Notes</th>

                                </tr>

                                </thead>

                                <tbody>

                                <tr class="">

                                    <td>alex</td>

                                    <td>Alex Nilson</td>

                                    <td>1234</td>

                                    <td class="center">power user</td>

                                </tr>

                                <tr class="">

                                    <td>lisa</td>

                                    <td>Lisa Wong</td>

                                    <td>434</td>

                                    <td class="center">new user</td>

                                </tr>

                                <tr class="">

                                    <td>nick12</td>

                                    <td>Nick Roberts</td>

                                    <td>232</td>

                                    <td class="center">power user</td>

                                </tr>

                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

                <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample_editable_1" rowspan="1" colspan="1" aria-label="Username: activate to sort column ascending" style="width: 289px;">Username</th>

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

                <script src="/js/table-editable.js"></script>

                <script>

                    jQuery(document).ready(function() {

                        App.init();

                        TableEditable.init();

                    });

                </script>

                <script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->

</html>