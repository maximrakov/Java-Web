<#import "common.ftlh" as c>

<@c.page>
    <#list person.posts as post>
        <@c.post post/>
    </#list>
</@c.page>