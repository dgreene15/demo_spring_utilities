<!DOCTYPE html>
<html>
<head>
    <title>Greeting</title>
</head>
<body>
    <h1>Hello, ${user}!</h1>
    <p>Your favorite colors are:</p>
    <ul>
    <#list colors as color>
        <li>${color}</li>
    </#list>
    </ul>
    <#if isAdmin>
        <p>You are an administrator.</p>
    </#if>
</body>
</html>