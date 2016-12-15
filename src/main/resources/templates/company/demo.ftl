<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>Metronic | Form Stuff - Form Validation</title>

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

    <link rel="stylesheet" type="text/css" href="/css/chosen.css" />

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="/image/favicon.ico" />

</head>

<body class="page-header-fixed">

<div class="header navbar navbar-inverse navbar-fixed-top">

</div>

<div class="page-container row-fluid">

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        新增客户

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="index.html">首页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="#">客户管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="#">新增客户</a></li>

                    </ul>

                </div>

            </div>

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN VALIDATION STATES-->

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-reorder"></i>Basic Validation</div>

                            <div class="tools">

                                <a href="javascript:;" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:;" class="reload"></a>

                                <a href="javascript:;" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <!-- BEGIN FORM-->

                            <form action="#" id="form_sample_1" class="form-horizontal">

                                <div class="alert alert-error hide">

                                    <button class="close" data-dismiss="alert"></button>

                                    对不起，操作失败，请检查你的输入！

                                </div>

                                <div class="alert alert-success hide">

                                    <button class="close" data-dismiss="alert"></button>

                                    操作成功！

                                </div>

                                <div class="control-group">

                                    <label class="control-label">Name<span class="required">*</span></label>

                                    <div class="controls">

                                        <input type="text" name="name" data-required="1" class="span6 m-wrap"/>

                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">Category<span class="required">*</span></label>

                                    <div class="controls">

                                        <select class="span6 m-wrap" name="category">

                                            <option value="">Select...</option>

                                            <option value="Category 1">Category 1</option>

                                            <option value="Category 2">Category 2</option>

                                            <option value="Category 3">Category 5</option>

                                            <option value="Category 4">Category 4</option>

                                        </select>

                                    </div>

                                </div>

                                <div class="form-actions">

                                    <button type="submit" class="btn blue">Validate</button>

                                    <button type="button" class="btn">Cancel</button>

                                </div>

                            </form>

                            <!-- END FORM-->

                        </div>

                    </div>

                    <!-- END VALIDATION STATES-->

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

<script type="text/javascript" src="/js/jquery.validate.min.js"></script>

<script type="text/javascript" src="/js/additional-methods.min.js"></script>

<script type="text/javascript" src="/js/select2.min.js"></script>

<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL STYLES -->

<script src="/js/app.js"></script>

<script src="/js/form-validation.js"></script>

<!-- END PAGE LEVEL STYLES -->

<script>

    jQuery(document).ready(function() {

        // initiate layout and plugins

        App.init();

        FormValidation.init();

    });

</script>

<!-- END JAVASCRIPTS -->

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->

</html>