<html>
<head>
    <title>用户信息</title>
    <meta charset="utf-8">
</head>
<body>
<table style="width: 100%; border: 1px solid red" cellpadding="5px" cellspacing="0px">
    <thead>
    <th>id</th>
    <th>apiTypeId</th>
    <th>stid</th>
    <th>customerId</th>
    </thead>
    <tbody>
    <#if customerRequestLogList??>
        <#list customerRequestLogList as customerRequestLog>
        <tr>
            <td>${customerRequestLog.id!'null'}</td>
            <td>${customerRequestLog.apiTypeId!'null'}</td>
            <td>${customerRequestLog.stid!'null'}</td>
            <td>${customerRequestLog.customerId!'null'}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<a href="/user/href?export=true">导出Excel</a>
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