<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>平台管理后台</title>

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

    <link href="/css/login.css" rel="stylesheet" type="text/css"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

<!-- BEGIN LOGO -->

<div class="logo">



</div>

<!-- END LOGO -->

<!-- BEGIN LOGIN -->

<div class="content">

    <!-- BEGIN LOGIN FORM -->

    <form action="/view/login-action" class="login_form" method="post" >

        <h3 class="form-title">欢迎登录</h3>


        <div class="control-group">

        <#if msg??>
            <div class="alert alert-error show">

                <button class="close" data-dismiss="alert"></button>

                <span>${msg}</span>

            </div>
        </#if>

        </div>

        <div class="control-group">

            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

            <label class="control-label visible-ie8 visible-ie9">Username</label>

            <div class="controls">

                <div class="input-icon left">

                    <i class="icon-user"></i>

                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" id="username" name="username"/>

                </div>

            </div>

        </div>

        <div class="control-group">

            <label class="control-label visible-ie8 visible-ie9">Password</label>

            <div class="controls">

                <div class="input-icon left">

                    <i class="icon-lock"></i>

                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="Password" id="password" name="password"/>

                </div>

            </div>

        </div>

        <div class="form-actions">

            <#--<label class="checkbox">-->

                <#--<input type="checkbox" name="remember" value="1"/> 记住我-->

            <#--</label>-->

            <button type="submit" id="subButton" class="btn green pull-right">

                登录 <i class="m-icon-swapright m-icon-white"></i>

            </button>

        </div>

        <div class="forget-password">

            <p align="right">

                <#--<a href="javascript:;" class="" id="forget-password">忘记密码？</a>-->

                <#--<a href="javascript:;" class="" id="forget-password">申请账号</a>-->

            </p>

        </div>

    </form>

    <!-- END LOGIN FORM -->

</div>



<div class="copyright">

    <#--2016 &copy; 北京千眼数合科技有限公司-->

</div>

<!-- END COPYRIGHT -->

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

<script src="/js/jquery.validate.min.js" type="text/javascript"></script>

<script src="/js/app.js" type="text/javascript"></script>

<script src="/js/login.js" type="text/javascript"></script>

<script>
    $(document).keypress(function(e) {
        if (e.which == 13)
            $(".login_form").submit();
    });
</script>

<script>

    jQuery(document).ready(function() {

        App.init();

        Login.init();

    });

</script>

<!-- END JAVASCRIPTS -->

<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

<!-- END BODY -->

</html>