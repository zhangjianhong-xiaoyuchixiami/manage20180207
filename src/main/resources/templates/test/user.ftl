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


    <a href="/user?export=true">导出Excel</a>

</body>
</html>