<#import "common.ftl" as c>

<@c.page>
    <div class="make-news">
        <div class="header">
        </div>
        <form method="post" action="/MakeNotice">
            <input type="hidden" name="action" value="makeNotice">
            <textarea id="message" name="message">
            </textarea>

            <div class="button-field">
                <input type="submit" value="MakeNotice">
            </div>
        </form>
    </div>
</@c.page>