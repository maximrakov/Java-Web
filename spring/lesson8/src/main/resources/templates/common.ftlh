<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/form.css">
    <link rel="stylesheet" type="text/css" href="/css/datatable.css">
    <script src="/js/jquery-3.6.0.js"></script>
</head>
<body>
<header>
    <div class="enter-or-register-box">
        <#if person??>
            ${person.login}
            |
            <a href="/logout">Logout</a>
        <#else>
            <a href="/enter">Enter</a>
            |
            <a href="/register">Register</a>
        </#if>
    </div>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <#if person??>
                <li><a href="/users">Users</a></li>
                <li><a href="/article">Articles</a></li>
            </#if>
        </ul>
    </nav>
</header>
<div class="middle">
    <aside>

        <#list notes as u>
            <section>
                <div class="header">
                    Pay attention
                </div>
                <div class="body">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.content}</td>
                        </tr>
                </div>
                <div class="footer">
                    <a href="#">View all</a>
                </div>
            </section>
        </#list>
    </aside>
    <main>
        <#nested/>
    </main>
</div>
<footer>
</footer>
<script>
    $(function (){
        <#if message??>
            notify("${message?js_string}");
        </#if>
    })
</script>
</body>
</html>
</#macro>
