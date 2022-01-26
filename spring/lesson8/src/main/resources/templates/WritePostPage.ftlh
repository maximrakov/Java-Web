<#import "common.ftlh" as c>
<#import "/spring.ftl" as spring>

<#macro error field>
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>

<@c.page>
<div class="writePost form">
    <div class="header">Write Post</div>
    <div class="body">
        <form method="post" action="">
            <div class="field">
                <div class="name">
                    <label for="title">Title</label>
                </div>
                <div class="value">
                    <input id="title" name="title" value="${post.title!}"/>
                </div>
<#--                <@error "post.title"/>-->
            </div>

            <div class="field">
                <div class="name">
                    <label for="tagsAsString">Tags</label>
                </div>
                <div class="value">
                    <input id="tagsAsString" name="tagsAsString" value="${post.tagsAsString!}"/>
                </div>
                <#--                <@error "post.title"/>-->
            </div>

            <div class="field">
                <div class="name">
                    <label for="text">Text</label>
                </div>
                <div class="value">
                    <textarea id="text" name="text">${post.text!}</textarea>
                </div>
<#--                <@error "post.text"/>-->
            </div>
            <div class="button-field">
                <input type="submit" value="Publish">
            </div>
            <div class="error">
            </div>
        </form>
    </div>
</div>
</@c.page>