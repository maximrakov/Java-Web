<#-- @ftlvariable name="message" type="java.lang.String" -->

<#import "common.ftlh" as c>

<@c.page>
    <#if message??>
        ${message}
    </#if>

</@c.page>