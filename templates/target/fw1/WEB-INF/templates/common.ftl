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
</head>
<body>
<header>
    <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
        <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
    </div>
    <div class="enter-or-register-box">
        <#if userName??>
            <a href="#">${userName}</a>
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
            <li><a href="/top">Top</a></li>
            <li><a href="/contests">Contests</a></li>
            <li><a href="/gym">Gym</a></li>
            <li><a href="/problemset">Problemset</a></li>
            <li><a href="/groups">Groups</a></li>
            <li><a href="/rating">Rating</a></li>
            <li><a href="/GetNews">News</a></li>
        </ul>
    </nav>
</header>
<div class="middle">
    <aside>
        <#list curNews as neww>
            <section>
                <div class="header">
                    ${neww.getUserName()}
                </div>
                <div class="body">
                    ${neww.message}
                </div>
                <div class="footer">
                    <a href="#">View all</a>
                </div>
            </section>
        </#list>
    </aside>
    <main>
        <#if userName??>
            <a href="/MakeNotice">Make notice</a>
        </#if>
        <#nested/>
    </main>
</div>
<footer>
    <p>Registered users: ${userCount!}</p>
    <a href="#">Codeforces</a> &copy; 2010-2018 by Mike Mirzayanov ${text!}
</footer>
</body>
</html>
</#macro>
