
<#macro publicJs>

    <#assign ver='1.0.0.22'/>

<script src="/assect/jquery-1.10.1.min.js?v=${ver}" type="text/javascript"></script>

<script src="/assect/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="/assect/bootstrap.min.js" type="text/javascript"></script>

<script src="/assect/excanvas.min.js"></script>

<script src="/assect/respond.min.js"></script>

<script src="/assect/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="/assect/jquery.blockui.min.js" type="text/javascript"></script>

<script src="/assect/jquery.cookie.min.js" type="text/javascript"></script>

<script src="/assect/jquery.uniform.min.js" type="text/javascript" ></script>

<#--表单验证插件开始-->

<script type="text/javascript" src="/assect/jquery.validate.min.js"></script>

<script type="text/javascript" src="/assect/additional-methods.min.js"></script>

<#--表单验证插件结束-->

<#--yyyy/MM/dd日期插件开始-->

<script type="text/javascript" src="/js/former/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="/js/locales/bootstrap-datepicker.zh-CN.js"></script>

<script type="text/javascript" src="/js/locales/set-datepicker-zh-CN.js"></script>

<#--yyyy/MM/dd日期插件结束-->

<#--<script type="text/javascript" src="/assect/jquery.fancybox.pack.js"></script>-->

<#--bootstrap-chosen下拉框插件开始-->

<script type="text/javascript" src="/assect/chosen.jquery.min.js"></script>

<#--bootstrap-chosen下拉框插件结束-->

<#--select2下拉框插件开始-->

<script src="/js/former/select2/select2.min.js"></script>

<script src="/js/former/select2/i18n/zh-CN.js"></script>

<#--select2下拉框插件结束-->

<#--dataTable插件开始-->

<script type="text/javascript" src="/js/former/jquery.dataTables.js?v=${ver}"></script>

<script type="text/javascript" src="/js/former/DT_bootstrap.js?v=${ver}"></script>

<script src="/js/locales/dataTables-sort-plungin.js"></script>

<#--dataTable插件结束-->

<#--sweetalert弹框插件开始-->

<script src="/js/sweetalert/sweetalert2.min.js?v=${ver}"></script>

<script src="/js/sweetalert/core.js?v=${ver}"></script>

<#--sweetalert弹框插件结束-->

<script type="text/javascript" src="/assect/jquery.bootstrap.wizard.min.js"></script>

<script type="text/javascript" src="/assect/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript" src="/assect/jquery.input-ip-address-control-1.0.min.js"></script>

<script type="text/javascript" src="/assect/app.js"></script>

<script src="/js/multi/shade.js"></script>

<script src="/js/multi/ajax-session-timeout.js"></script>

<script src="/js/multi/tip.js"></script>

<script>

    jQuery(document).ready(function() {

        App.init();

    });
</script>

<#--<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-37564768-1']);
    _gaq.push(['_setDomainName', 'keenthemes.com']);
    _gaq.push(['_setAllowLinker', true]);
    _gaq.push(['_trackPageview']);
    (function() {    var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();
</script>-->



</#macro>