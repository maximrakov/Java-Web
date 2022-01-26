<#-- @ftlvariable name="message" type="java.lang.String" -->

<#import "common.ftlh" as c>

<@c.page>
    <#list posts as post>
        <@c.post post/>
    </#list>
</@c.page>