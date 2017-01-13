<html>
<head>
    <title>用户信息</title>
    <meta charset="utf-8">
</head>
<body>
<table style="width: 400px; border: 1px solid red">
    <thead>
    <th>ID</th>
    <th>姓名</th>
    <th>用户名</th>
    <th>电话</th>
    </thead>
    <tbody>
    <#if userList??>
        <#list userList as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.username}</td>
            <td>${user.tel}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<form action="/test-controller/user">
    <input type="text" value="true" name="export">
    <button type="submit">导出Excel</button>
</form>
</body>
</html>