<#import "common.ftl" as c>
<@c.page>
    <form method="post" action="/talks">
        <input type="hidden" name="action" value="send">
        <div class="field">
                <div class="name">
                    <label for="recipient">Name of Recipient</label>
                </div>
                <div class="value">
                    <input id="recipient" type="text" name="recipient" value="${recipient!}"/>
                </div>
        </div>
        <textarea id="message" name="message">
        </textarea>
        <div class="button-field">
            <input type="submit" value="Send message">
        </div>
    </form>

    <div class="mail">
        <p>Your messages</p>
        <#list trg as curmsg>
            ${curmsg.name} : ${curmsg.message} <br>
        </#list>
    </div>
</@c.page>