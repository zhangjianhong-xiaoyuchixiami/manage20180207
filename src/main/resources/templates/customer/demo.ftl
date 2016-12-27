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

                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

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

                            <div class="clearfix">

                                <div class="btn-group">

                                    <button id="sample_editable_1_new" class="btn green">

                                        Add New <i class="icon-plus"></i>

                                    </button>

                                </div>

                                <div class="btn-group pull-right">

                                    <button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>

                                    </button>

                                    <ul class="dropdown-menu pull-right">

                                        <li><a href="#">Print</a></li>

                                        <li><a href="#">Save as PDF</a></li>

                                        <li><a href="#">Export to Excel</a></li>

                                    </ul>

                                </div>

                            </div>

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

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>shuxer</td>

                                    <td class="hidden-480"><a href="mailto:shuxer@gmail.com">shuxer@gmail.com</a></td>

                                    <td class="hidden-480">120</td>

                                    <td class="center hidden-480">12 Jan 2012</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>looper</td>

                                    <td class="hidden-480"><a href="mailto:looper90@gmail.com">looper90@gmail.com</a></td>

                                    <td class="hidden-480">120</td>

                                    <td class="center hidden-480">12.12.2011</td>

                                    <td ><span class="label label-warning">Suspended</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>userwow</td>

                                    <td class="hidden-480"><a href="mailto:userwow@yahoo.com">userwow@yahoo.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>user1wow</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td ><span class="label label-inverse">Blocked</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>restest</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>foopl</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>weep</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>coop</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>pppol</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>test</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>userwow</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td ><span class="label label-inverse">Blocked</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>test</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>goop</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>weep</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">15.11.2011</td>

                                    <td ><span class="label label-inverse">Blocked</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>toopl</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">16.11.2010</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>userwow</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">9.12.2012</td>

                                    <td ><span class="label label-inverse">Blocked</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>tes21t</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">14.12.2012</td>

                                    <td ><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>fop</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">13.11.2010</td>

                                    <td ><span class="label label-warning">Suspended</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>kop</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">17.11.2010</td>

                                    <td><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>vopl</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.11.2010</td>

                                    <td><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>userwow</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">userwow@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td><span class="label label-inverse">Blocked</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>wap</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">test@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">12.12.2012</td>

                                    <td><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>test</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">19.12.2010</td>

                                    <td><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>toop</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">17.12.2010</td>

                                    <td><span class="label label-success">Approved</span></td>

                                </tr>

                                <tr class="odd gradeX">

                                    <td><input type="checkbox" class="checkboxes" value="1" /></td>

                                    <td>weep</td>

                                    <td class="hidden-480"><a href="mailto:userwow@gmail.com">good@gmail.com</a></td>

                                    <td class="hidden-480">20</td>

                                    <td class="center hidden-480">15.11.2011</td>

                                    <td><span class="label label-success">Approved</span></td>

                                </tr>

                                </tbody>

                            </table>

                        </div>

                    </div>

                    <!-- END EXAMPLE TABLE PORTLET-->

                </div>

            </div>


        </div>

        <!-- END PAGE CONTAINER-->

    </div>

    <!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

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

<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS -->

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