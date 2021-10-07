<#import "common.ftl" as c>
<@c.page>
    <#list curNews as neww>
        <br>
        ${neww.userID} : ${neww.message}
    </#list>
</@c.page>