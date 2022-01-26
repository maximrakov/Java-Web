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
                <li> <a href="/writePost">Write Post</a></li>
                <li> <a href="/MyPosts">My posts</a></li>
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

<#macro post post>
    <article>
        <div class="title">${post.title}</div>
        <div class="information">By ${post.person.login},  ${post.creationTime} </div>
        <div class="tags">
            <#list post.tags as tag>
                ${"#"}${tag.name}${" "}
            </#list>
        </div>
        <div class="body">
            ${post.text}
        </div>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                ${post.creationTime}
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>

<#macro comment comment>
    <article>
        <div class="title">${comment.person.login}</div>
        <div class="body">
            ${comment.text}
        </div>
    </article>
</#macro>
