<html>
<head>
    <title>用户信息</title>
    <meta charset="utf-8">

    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

    <script src="/bootstrap/js/bootstrap.js"></script>
    <script src="/bootstrap/js/npm.js"></script>


</head>
<body>

<div>

    <form action="" method="get">
        <span>新增</span>
        邮箱<input type="text" />
        类型<input type="text" />
        <input type="submit" value="提交"/>
    </form>

</div>
<table class="table table-striped table-bordered table-condensed">
    <thead>
    <th>id</th>
    <th>apiTypeId</th>
    <th>stid</th>
    <th>customerId</th>
    <th>operator</th>
    </thead>
    <tbody>
    <#if customerRequestLogList??>
        <#list customerRequestLogList as customerRequestLog>
        <tr>
            <td>${customerRequestLog.id!'null'}</td>
            <td>${customerRequestLog.apiTypeId!'null'}</td>
            <td>${customerRequestLog.stid!'null'}</td>
            <td>${customerRequestLog.customerId!'null'}</td>
            <td><a>删除</a>|<a>修改</a></td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</div>

<a href="/user?export=true">导出Excel</a>
</body>

<script src="/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script>

    $(document).ready(function () {
        $(function () {
            var pageSize = "${Request.pageSize!''}";
            console.log(pageSize);
            var lineSize = "${Request.lineSize!''}";
            console.log(lineSize);

        });
    });

    /* function GetQueryString(name)
     {
         var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
         var r = window.location.search.substr(1).match(reg);
         if(r!=null)return unescape(r[2]); return null;
     }

     var myurl=GetQueryString("cid");
     if(myurl !=null && myurl.toString().length>1)
     {
         console.log(GetQueryString("cid"));
     }*/



</script>

<script type="text/javascript">

    (function($){
        $.getUrlParam = function(name)
        {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r!=null) return unescape(r[2]); return '';
        }
    })(jQuery);

    $(function(){
        $.each(function () {
            console.log($.getUrlParam('aaa'));
        });
        console.log("***"+$.getUrlParam('aaa'));
    });

    $('a').each(function(){
        var href = $(this).attr('href');
        if(href) {
            href += (href.match(/\?/) ? '&' : '?') + 'aaa=' + $.getUrlParam('aaa') +
                    (href.match(/\?/) ? '&' : '?') + 'bbb=' + $.getUrlParam('bbb');
            $(this).attr('href', href);
        }
    });
</script>

</html>