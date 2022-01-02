<#import "common.ftlh" as c>

<@c.page>
    <#if person??>
        Id: ${person.id} <br>
        Login ${person.login} <br>
        CreationTime: ${person.creationTime}
    <#else>
        No such user
    </#if>

</@c.page>